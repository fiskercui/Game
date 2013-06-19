// CodeGenerate for java

package com.road.pb.wrap;

import com.road.pb.UserMsgProto.UserMsg;
import com.google.protobuf.InvalidProtocolBufferException;

public class UserMsgProtoWrap
{
    private UserMsg msg = null;

    private UserMsgProtoWrap()
    {
    }

    private UserMsgProtoWrap(byte[] objBytes) throws InvalidProtocolBufferException
    {
        this.msg = UserMsg.parseFrom(objBytes);
    }

    public static UserMsgProtoWrap readData(byte[] objBytes) throws InvalidProtocolBufferException
    {
        UserMsgProtoWrap wrap = new UserMsgProtoWrap(objBytes);
        return wrap;
    }

    public static byte[] writeData(Builder builder)
    {
        return builder.build().toByteArray();
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public int getUserId()
    {
        return msg.getUserId();
    }

    public String getName()
    {
        return msg.getName();
    }

    public static final class Builder
    {
        private UserMsg.Builder builder = UserMsg.newBuilder();

        private UserMsg build()
        {
            return builder.build();
        }

        public void setUserId(int userId)
        {
            builder.setUserId(userId);
        }

        public void setName(String name)
        {
            builder.setName(name);
        }

    }

}