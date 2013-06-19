package com.road.pitaya.net.mina;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.net.CommonMessage;

public class CommonMessageDecoder extends CumulativeProtocolDecoder
{
    private static Logger LOGGER = LoggerFactory.getLogger(CommonMessageEncoder.class);

    public CommonMessageDecoder()
    {

    }

    /**
     * 把二进制流解码为服务器使用的数据包格式
     */
    @Override
    public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception
    {
        if (in.remaining() < CommonMessage.HDR_SIZE)
        {
            return false;
        }
        // IoBuffer newBuf = in.slice();
        ByteBuffer newBuf = in.slice().buf();
        short headerFlag = newBuf.getShort();
        if (CommonMessage.HEADER != headerFlag)
        {
            in.getShort();
            LOGGER.debug("Illegal client request,can not match header flag. drop a packet,close connection.");
            // session.close();
            return false;
        }

        // 长度
        int lenght = newBuf.getShort();
        if (lenght <= 0 || lenght >= Short.MAX_VALUE)
        {
            // 非法的数据长度
            LOGGER.debug("Message Length Invalid Length = " + lenght + ", drop this Message.");
            return true;
        }

        if (lenght > in.remaining())
        {
            // 数据还不够读取,等待下一次读取
            // Log.warn("Data not integrity. there is a lack of " + (lenght - newBuf.remaining()) + " bytes.");
            return false;
        }

        CommonMessage packet = CommonMessage.buildCommonMessage();
        packet.readHeader(in.buf());

        // BODY
        int bodyLen = lenght - CommonMessage.HDR_SIZE;
        if (bodyLen > 0)
        {
            byte[] bytes = new byte[bodyLen];
            in.get(bytes, 0, bodyLen);
            packet.setBytes(bytes);
        }

        out.write(packet);
        return true;
    }
}
