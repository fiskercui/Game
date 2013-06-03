// CodeGenerate for java

package com.road.pb.wrap;

import com.road.pb.PrivateChatMsgProto.PrivateChatMsg;
import com.google.protobuf.InvalidProtocolBufferException;

public class PrivateChatMsgWrap
{
    private PrivateChatMsg msg = null;

    private PrivateChatMsgWrap()
    {
    }

    private PrivateChatMsgWrap(byte[] objBytes) throws InvalidProtocolBufferException
    {
        this.msg = PrivateChatMsg.parseFrom(objBytes);
    }

    public static PrivateChatMsgWrap readData(byte[] objBytes) throws InvalidProtocolBufferException
    {
        PrivateChatMsgWrap wrap = new PrivateChatMsgWrap(objBytes);
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

    public int getSendId()
    {
        return msg.getSendId();
    }

    public int getRecvId()
    {
        return msg.getRecvId();
    }

    public String getMessage()
    {
        return msg.getMessage();
    }

    public static final class Builder
    {
        private PrivateChatMsg.Builder builder = PrivateChatMsg.newBuilder();

        private PrivateChatMsg build()
        {
            return builder.build();
        }

        public void setSendId(int sendId)
        {
            builder.setSendId(sendId);
        }

        public void setRecvId(int recvId)
        {
            builder.setRecvId(recvId);
        }

        public void setMessage(String message)
        {
            builder.setMessage(message);
        }

    }

}