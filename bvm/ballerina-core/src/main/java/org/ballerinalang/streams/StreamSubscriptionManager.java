/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.streams;

import org.ballerinalang.model.values.BFunctionPointer;
import org.ballerinalang.model.values.BStream;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.util.exceptions.BallerinaException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * The {@link StreamSubscriptionManager} manages the streams subscriptions. It is responsible for registering
 * subscriptions for streams and sending events to correct stream through the subscription.
 *
 * @since 0.995.0
 */
public class StreamSubscriptionManager implements Observer {

    private Map<String, List<StreamSubscription>> processors = new HashMap<>();

    private static StreamSubscriptionManager streamSubscriptionManager = new StreamSubscriptionManager();

    private StreamSubscriptionManager() {

    }

    public static StreamSubscriptionManager getInstance() {
        return streamSubscriptionManager;
    }

    public void registerMessageProcessor(BStream stream,  BFunctionPointer functionPointer) {
        synchronized (this) {
            processors.computeIfAbsent(stream.topicName, key -> new ArrayList<>())
                    .add(new DefaultStreamSubscription(stream, functionPointer, this));
        }
    }

    public void sendMessage(BStream stream, BValue value) {
        List<StreamSubscription> msgProcessors = processors.get(stream.topicName);
        if (msgProcessors != null) {
            msgProcessors.forEach(processor -> processor.send(value));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof StreamSubscription)) {
            throw new BallerinaException("Invalid subscription. Expected a subscription to a stream");
        }
        StreamSubscription msgProcessor = (StreamSubscription) o;
        BStream stream = msgProcessor.getStream();
        if (!(arg instanceof BValue)) {
            throw new BallerinaException("Data received to stream: " + stream.getStreamId() + "is not supported");
        }
        msgProcessor.execute((BValue) arg);
    }
}