import ballerina/io;

// TODO: move to DataChannel native impl
type ChannelReader object {
    io:ByteChannel channel;

    new(channel){
    }

    function readBoolean() returns boolean {
        var (boolByte, _mustBe1) = check channel.read(1);
        byte one = 1;
        return boolByte[0] == one;
    }

    function readInt8() returns int {
        var (byteValue, _mustBe1) = check channel.read(1);
        return <int>byteValue[0];
    }

    function readInt32() returns int {
        var (intBytes, _mustBe4) = check channel.read(4);
        return bytesToInt(intBytes);
    }

    function readInt64() returns int {
        return readInt32() << 32 | readInt32();
    }


    function readString() returns string {
        var stringLen = untaint readInt32();
        if (stringLen > 0){
            var (strBytes, strLen) = check channel.read(untaint stringLen);
            return internal:byteArrayToString(strBytes, "UTF-8");
        } else {
            return "";
        }
    }

    function readByteArray(int len) returns byte[] {
        var (arr, arrLen) = check channel.read(len);
        if(arrLen != len){
            error err = { message: "Unable to read "+len+" bytes" };
            throw err;
        }
        return arr;
    }
};

function bytesToInt(byte[] b) returns int {
    int ff = 255;
    int octave1 = 8;
    int octave2 = 16;
    int octave3 = 24;
    int b0 = <int>b[0];
    int b1 = <int>b[1];
    int b2 = <int>b[2];
    int b3 = <int>b[3];
    return b0 <<octave3|(b1 & ff) <<octave2|(b2 & ff) <<octave1|(b3 & ff);
}

