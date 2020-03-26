/*
 * Copyright (c) 2020, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ballerinalang.observe.trace.extension.choreo.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.ballerinalang.observe.trace.extension.choreo.gen.NegotiatorGrpc;
import org.ballerinalang.observe.trace.extension.choreo.gen.NegotiatorOuterClass.HandshakeRequest;
import org.ballerinalang.observe.trace.extension.choreo.gen.NegotiatorOuterClass.HandshakeResponse;
import org.ballerinalang.observe.trace.extension.choreo.gen.NegotiatorOuterClass.PublishProgramRequest;
import org.ballerinalang.observe.trace.extension.choreo.gen.TelemetryGrpc;
import org.ballerinalang.observe.trace.extension.choreo.gen.TelemetryOuterClass;
import org.ballerinalang.observe.trace.extension.choreo.model.ChoreoMetric;
import org.ballerinalang.observe.trace.extension.choreo.model.ChoreoTraceSpan;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Manages the communication with Choreo cloud.
 */
public class ChoreoClient implements AutoCloseable {
    private String id;      // ID received from the handshake
    private String instanceId;
    private String appId;
    private ManagedChannel channel;
    private NegotiatorGrpc.NegotiatorBlockingStub negotiator;
    private TelemetryGrpc.TelemetryBlockingStub telemetryClient;
    private Thread uploadingThread;

    public ChoreoClient(String hostname, int port, boolean useSSL) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(hostname, port);
        if (!useSSL) {
            channelBuilder.usePlaintext();
        }
        channel = channelBuilder.build();
        negotiator = NegotiatorGrpc.newBlockingStub(channel);
        telemetryClient = TelemetryGrpc.newBlockingStub(channel);
    }

    public String register(final MetadataReader metadataReader, String instanceId, String appId) {
        HandshakeRequest handshakeRequest = HandshakeRequest.newBuilder()
                .setProgramHash(metadataReader.getAstHash())
                .setUserId(instanceId)
                .setApplicationId(appId)
                .build();
        HandshakeResponse handshakeResponse = negotiator.handshake(handshakeRequest);
        this.id = handshakeResponse.getObservabilityId();
        this.appId = appId;
        boolean sendProgramJson = handshakeResponse.getSendProgramJson();

        if (sendProgramJson) {
            uploadingThread = new Thread(() -> {
                PublishProgramRequest programRequest = PublishProgramRequest.newBuilder()
                        .setProgramJson(metadataReader.getAstData())
                        .setObservabilityId(id)
                        .build();
                negotiator.withCompression("gzip").publishProgram(programRequest);
                // TODO add debug log to indicate success
            }, "AST Uploading Thread");
            uploadingThread.start();
        }

        this.instanceId = instanceId;
        return handshakeResponse.getUrl();
    }

    public void publishMetrics(ChoreoMetric[] metrics) {
        TelemetryOuterClass.MetricsPublishRequest.Builder requestBuilder =
                TelemetryOuterClass.MetricsPublishRequest.newBuilder();
        for (ChoreoMetric metric : metrics) {
            requestBuilder.addMetrics(TelemetryOuterClass.Metric.newBuilder()
                    .setTimestamp(metric.getTimestamp())
                    .setName(metric.getName())
                    .setValue(metric.getValue())
                    .putAllTags(metric.getTags())
                    .build());
        }
        telemetryClient.publishMetrics(requestBuilder.setObservabilityId(id)
                .setInstanceId(instanceId)
                .setAppId(appId)
                .build());
    }

    public void publishTraceSpans(ChoreoTraceSpan[] traceSpans) {
        TelemetryOuterClass.TracesPublishRequest.Builder requestBuilder =
                TelemetryOuterClass.TracesPublishRequest.newBuilder();
        for (ChoreoTraceSpan traceSpan : traceSpans) {
            TelemetryOuterClass.TraceSpan.Builder traceSpanBuilder = TelemetryOuterClass.TraceSpan.newBuilder()
                    .setTraceId(traceSpan.getTraceId())
                    .setSpanId(traceSpan.getSpanId())
                    .setServiceName(traceSpan.getServiceName())
                    .setOperationName(traceSpan.getOperationName())
                    .setTimestamp(traceSpan.getTimestamp())
                    .setDuration(traceSpan.getDuration())
                    .putAllTags(traceSpan.getTags());
            for (ChoreoTraceSpan.Reference reference : traceSpan.getReferences()) {
                traceSpanBuilder.addReferences(TelemetryOuterClass.TraceSpanReference.newBuilder()
                        .setTraceId(reference.getTraceId())
                        .setSpanId(reference.getSpanId())
                        .setRefType(reference.getRefType() == ChoreoTraceSpan.Reference.Type.CHILD_OF
                                ? TelemetryOuterClass.TraceReferenceType.CHILD_OF
                                : TelemetryOuterClass.TraceReferenceType.FOLLOWS_FROM));
            }
            requestBuilder.addSpans(traceSpanBuilder.build());
        }
        telemetryClient.publishTraces(requestBuilder.setObservabilityId(id)
                .setInstanceId(instanceId)
                .setAppId(appId)
                .build());
    }

    @Override
    public void close() throws Exception {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        if (Objects.nonNull(uploadingThread)) {
            uploadingThread.join(5000);
        }
    }
}
