package com.road.pitaya.net;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 服务器和客户端,服务器和服务器直接数据传输的对象
 **/
public class CommonMessage implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final short HDR_SIZE = 20;
    public static final short HEADER = 0x71ab;

    private short header = HEADER; // 包头
    private short len; // 数据包长度
    private short checksum; // 校验和
    private short code; // 协议号
    private int playerId; // 玩家ID
    private int empty1; // 客服端标识
    private int empty2; // 预留字段2

    private byte[] bytes; // 数据体

    private CommonMessage()
    {
    }

    public CommonMessage(short code)
    {
        this(code, -1);
    }

    public CommonMessage(short code, int playerId)
    {
        this.code = code;
        this.playerId = playerId;
    }

    public short getHeader()
    {
        return header;
    }

    public short getLen()
    {
        return len;
    }

    public void setLen(short len)
    {
        this.len = len;
    }

    public short getChecksum()
    {
        return checksum;
    }

    public void setChecksum(short checksum)
    {
        this.checksum = checksum;
    }

    public short getCode()
    {
        return code;
    }

    public void setCode(short code)
    {
        this.code = code;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public int getEmpty1()
    {
        return empty1;
    }

    public void setEmpty1(int empty1)
    {
        this.empty1 = empty1;
    }

    public int getEmpty2()
    {
        return empty2;
    }

    public void setEmpty2(int empty2)
    {
        this.empty2 = empty2;
    }

    public byte[] getBytes()
    {
        return bytes;
    }

    public void setBytes(byte[] bytes)
    {
        this.bytes = bytes;
    }

    public void setBytes(ByteBuffer bytes)
    {

    }

    public void readHeader(ByteBuffer in)
    {
        in.getShort();
        len = in.getShort();
        checksum = in.getShort();
        code = in.getShort();
        playerId = in.getInt();
        empty1 = in.getInt();
        empty2 = in.getInt();
    }

    public void writeHeader(int len, IoBuffer out)
    {
        out.putShort(CommonMessage.HEADER);
        out.putShort((short) len);
        out.putShort(checksum);
        out.putShort(code);
        out.putInt(playerId);
        out.putInt(empty1);
        out.putInt(empty2);
    }

    /**
     * 只设置数据体
     * 
     * @param bodyBytes
     */
    public void writeBodyBytes(byte[] bodyBytes, int len)
    {
        IoBuffer out = IoBuffer.allocate(len + HDR_SIZE);
        writeHeader(len + HDR_SIZE, out);
        out.put(bodyBytes, 0, len);
        out.flip();
        bytes = out.array();
    }

    /**
     * 转换为字节数组
     * 
     * @return
     */
    public byte[] toByteArray()
    {
        return bytes;
    }

    /**
     * 创建空消息(避免外部实例化)
     * 
     * @return
     */
    public static CommonMessage buildCommonMessage()
    {
        return new CommonMessage();
    }

    public short calcChecksum(byte[] data)
    {
        int val = 0x77;
        int i = 6;
        int size = data.length;
        while (i < size)
        {
            val += (data[i++] & 0xFF);
        }
        return (short) (val & 0x7F7F);
    }

    public void clearCheckSum()
    {
        checksum = 0;
    }

    public String headerToStr()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("playerId : ").append(playerId);
        sb.append(", code : ").append(Integer.toHexString(code));
        sb.append(", len : ").append(len);
        sb.append(", empty1 : ").append(empty1);
        sb.append(", empty2 : ").append(empty2);
        sb.append(", checksum : ").append(checksum);
        return sb.toString();
    }

    public String detailToStr()
    {
        String str = "";
        if (bytes != null)
        {
            try
            {
                str = new String(bytes, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
            }
        }
        return headerToStr() + ", content : " + str;
    }
}
