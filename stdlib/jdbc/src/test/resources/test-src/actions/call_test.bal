// Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied. See the License for the
// specific language governing permissions and limitations
// under the License.

import ballerinax/java.jdbc;

string jdbcUserName = "SA";
string jdbcPassword = "";

type StringData record {
    string varchar_type;
    string charmax_type;
    string char_type;
    string charactermax_type;
    string character_type;
    string nvarcharmax_type;
    string longvarchar_type;
    string clob_type;
};

type StringDataSingle record {
    string varchar_type;
};

function testCallWithStringTypes(string jdbcURL) returns [string, string, string, string, string,
                    string, string, string, table<record {}>[]|()|error] {
    jdbc:Client testDB = new ({
        url: jdbcURL,
        username: jdbcUserName,
        password: jdbcPassword,
        poolOptions: {maximumPoolSize: 1}
    });

    var ret = testDB->call("{call InsertStringData(2,'test1', 'test2', 'c', 'test3', 'd', 'test4', 'test5', " +
    "'hello ballerina code')}", ());

    string varcharType = "";
    string charmaxType = "";
    string charType = "";
    string charactermaxType = "";
    string characterType = "";
    string nvarcharmaxType = "";
    string longvarcharType = "";
    string clobType = "";
    var dt = testDB->select("SELECT varchar_type, charmax_type, char_type, charactermax_type, character_type," +
    "nvarcharmax_type, longvarchar_type, clob_type from " +
    "StringTypes where id = 2", StringData);
    if (dt is table<StringData>) {
        foreach var x in dt {
            varcharType = <@untainted>x.varchar_type;
            charmaxType = <@untainted>x.charmax_type;
            charType = <@untainted>x.char_type;
            charactermaxType = <@untainted>x.charactermax_type;
            characterType = <@untainted>x.character_type;
            nvarcharmaxType = <@untainted>x.nvarcharmax_type;
            longvarcharType = <@untainted>x.longvarchar_type;
            clobType = <@untainted>x.clob_type;
        }
    }
    checkpanic testDB.stop();
    return [varcharType, charmaxType, charType, charactermaxType, characterType, nvarcharmaxType,
    longvarcharType, clobType, <@untainted>ret];
}

function testCallWithStringTypesReturnsData(string jdbcURL) returns [int, int, string, string, string, string, string,
                                    string, string, string] {
    jdbc:Client testDB = new ({
        url: jdbcURL,
        username: jdbcUserName,
        password: jdbcPassword,
        poolOptions: {maximumPoolSize: 1}
    });

    var ret = testDB->call("{call SelectStringData()}", [StringData]);

    string varcharType = "";
    string charmaxType = "";
    string charType = "";
    string charactermaxType = "";
    string characterType = "";
    string nvarcharmaxType = "";
    string longvarcharType = "";
    string clobType = "";

    int tableCount = 0;
    int rowCount = 0;
    if (ret is table<record {}>[]) {
        foreach var tbl in ret {
            tableCount = tableCount + 1;
            foreach var row in tbl {
                rowCount = rowCount + 1;
                StringData sd = <StringData>row;
                varcharType = <@untainted>sd.varchar_type;
                charmaxType = <@untainted>sd.charmax_type;
                charType = <@untainted>sd.char_type;
                charactermaxType = <@untainted>sd.charactermax_type;
                characterType = <@untainted>sd.character_type;
                nvarcharmaxType = <@untainted>sd.nvarcharmax_type;
                longvarcharType = <@untainted>sd.longvarchar_type;
                clobType = <@untainted>sd.clob_type;
            }
        }
    }
    checkpanic testDB.stop();
    return [tableCount, rowCount, varcharType, charmaxType, charType, charactermaxType, characterType, nvarcharmaxType,
    longvarcharType, clobType];
}

function testCallWithStringTypesReturnsDataMultiple(string jdbcURL) returns [string, string, string, string, string,
                        string, string, string, string] {
    jdbc:Client testDB = new ({
        url: jdbcURL,
        username: jdbcUserName,
        password: jdbcPassword,
        poolOptions: {maximumPoolSize: 1}
    });

    var ret = testDB->call("{call SelectStringDataMultiple()}", [StringData, StringDataSingle]);

    string varcharType = "";
    string charmaxType = "";
    string charType = "";
    string charactermaxType = "";
    string characterType = "";
    string nvarcharmaxType = "";
    string longvarcharType = "";
    string clobType = "";
    string varcharTypeSecond = "";

    if (ret is table<record {}>[]) {
        foreach var row in ret[0] {
            StringData sd = <StringData>row;
            varcharType = <@untainted>sd.varchar_type;
            charmaxType = <@untainted>sd.charmax_type;
            charType = <@untainted>sd.char_type;
            charactermaxType = <@untainted>sd.charactermax_type;
            characterType = <@untainted>sd.character_type;
            nvarcharmaxType = <@untainted>sd.nvarcharmax_type;
            longvarcharType = <@untainted>sd.longvarchar_type;
            clobType = <@untainted>sd.clob_type;
        }

        foreach var row in ret[1] {
            StringDataSingle sd = <StringDataSingle>row;
            varcharTypeSecond = <@untainted>sd.varchar_type;
        }
    }
    checkpanic testDB.stop();
    return [varcharType, charmaxType, charType, charactermaxType, characterType, nvarcharmaxType,
    longvarcharType, clobType, varcharTypeSecond];
}

function testCallWithApplicationError(string jdbcURL) returns @tainted table<record {}>[]|()|error {
    jdbc:Client testDB = new ({
        url: jdbcURL,
        username: jdbcUserName,
        password: jdbcPassword,
        poolOptions: {maximumPoolSize: 1}
    });

    var ret = testDB->call("{call SelectStringData()}", [StringData, StringData]);
    checkpanic testDB.stop();
    return ret;
}
