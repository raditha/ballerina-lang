/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ballerinalang.test.types.future;

import org.ballerinalang.test.util.BCompileUtil;
import org.ballerinalang.test.util.BRunUtil;
import org.ballerinalang.test.util.CompileResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This class contains future type related test cases.
 */
public class FutureTests {

    private CompileResult result;

    @BeforeClass
    public void setup() {
        result = BCompileUtil.compile("test-src/types/future/future_positive.bal");
    }

    @Test
    public void testBasicTypes() {
        BRunUtil.invoke(result, "testBasicTypes");
    }

    @Test
    public void testBasicTypesWithoutFutureConstraint() {
        BRunUtil.invoke(result, "testBasicTypesWithoutFutureConstraint");
    }

    @Test
    public void testRefTypes() {
        BRunUtil.invoke(result, "testRefTypes");
    }

    @Test
    public void testRefTypesWithoutFutureConstraint() {
        BRunUtil.invoke(result, "testRefTypesWithoutFutureConstraint");
    }

    @Test
    public void testArrayTypes() {
        BRunUtil.invoke(result, "testArrayTypes");
    }

    @Test
    public void testArrayTypesWithoutFutureConstraint() {
        BRunUtil.invoke(result, "testArrayTypesWithoutFutureConstraint");
    }

    @Test
    public void testRecordTypes() {
        BRunUtil.invoke(result, "testRecordTypes");
    }

    @Test
    public void testObjectTypes() {
        BRunUtil.invoke(result, "testObjectTypes");
    }

    @Test
    public void testObjectTypesWithoutFutureConstraint() {
        BRunUtil.invoke(result, "testObjectTypesWithoutFutureConstraint");
    }

    @Test
    public void testCustomErrorFuture() {
        BRunUtil.invoke(result, "testCustomErrorFuture");
    }

    @Test
    public void testCustomErrorFutureWithoutConstraint() {
        BRunUtil.invoke(result, "testCustomErrorFutureWithoutConstraint");
    }
}
