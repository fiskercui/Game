package com.road.pitaya.net.mina;

import java.io.IOException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.net.CommonMessage;

public class CommonMessageEncoder extends ProtocolEncoderAdapter
{
    private static Logger LOGGER = LoggerFactory.getLogger(CommonMessageEncoder.class);

    public CommonMessageEncoder()
    {
    }

    /**
     * 对数据包进行二进制流编码
     */
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception
    {
        CommonMessage rsp = (CommonMessage) message;
        IoBuffer buffer = null;
        buffer = encodeByte(rsp);

        if (buffer == null)
        {// 丢弃这个数据包
            LOGGER.error("code : " + rsp.getCode() + ",over max length,UserId = " + rsp.getPlayerId());
            return;
        }

        buffer.flip();
        out.write(buffer);
    }

    private IoBuffer encodeByte(CommonMessage cmMessage) throws IOException
    {
        byte[] bytes = cmMessage.getBytes();
        int size = CommonMessage.HDR_SIZE;
        if (bytes != null)
        {
            size = CommonMessage.HDR_SIZE + bytes.length;
        }
        if (size > Short.MAX_VALUE)
        {
            return null;
        }
        IoBuffer buffer = IoBuffer.allocate(size);
        cmMessage.writeHeader(size, buffer); // 协议头部
        if (bytes != null)
        {
            buffer.put(bytes);
        }
        return buffer;
    }
}
