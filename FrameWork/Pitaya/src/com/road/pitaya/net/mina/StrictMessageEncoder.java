package com.road.pitaya.net.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.net.CommonMessage;



public class StrictMessageEncoder extends ProtocolEncoderAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StrictMessageEncoder.class);
   
    private boolean isRecord = false;
    
    private final boolean isStrict = LOGGER.isDebugEnabled();
 
    public StrictMessageEncoder()
    {

    }

    public void setIsRecord(boolean isRecord)
    {
        this.isRecord = isRecord;
    }


    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception
    {
        try
        {
            // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
            CommonMessage msg = (CommonMessage) message;

            // 调试打印IP和包头
            String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().toString();
            LOGGER.info("send: " + "IP:" + ip + "Illegal Packet. packet header : " + msg.headerToStr());// 调试

            IoBuffer buffer = null;

            buffer = encodeByte(msg);


            if (buffer == null)
            {// 丢弃这个数据包
                LOGGER.error("code : " + msg.getCode() + ",over max length,UserId = " + msg.getPlayerId());
                return;
            }

            if (isStrict)
            {
                buffer.flip();
                int lastCipherByte = 0;

                int[] encryptKey = getContext(session);
                byte[] plainText = buffer.array();
                short checkSum = msg.calcChecksum(plainText);
                plainText[4] = (byte) (checkSum >> 8);
                plainText[5] = (byte) checkSum;

                int length = plainText.length;
                IoBuffer cipherBuffer = IoBuffer.allocate(length);

                StringBuilder str = null;
                if (isRecord)
                {
                    str = new StringBuilder();
                    str.append("packet record ==");
                    str.append("userId : ").append(msg.getPlayerId()).append(" , ");
                    str.append("code : ").append(Integer.toHexString(msg.getCode())).append(" , ");
                    str.append("pblen : ").append(msg.getLen()).append(" , ");
                    str.append("length : ").append(length).append(" , \n");
                    str.append(toHexDump("Old key:", encryptKey, 0, encryptKey.length));
                    str.append(toHexDump("Old Bytes:", plainText, 0, plainText.length));
                }

                // 加密首字节
                lastCipherByte = (byte) ((plainText[0] ^ encryptKey[0]) & 0xff);
                cipherBuffer.put((byte) lastCipherByte);

                // 循环加密
                int keyIndex = 0;
                for (int i = 1; i < length; i++)
                {
                    keyIndex = i & 0x7;
                    encryptKey[keyIndex] = ((encryptKey[keyIndex] + lastCipherByte) ^ i) & 0xff;
                    lastCipherByte = (((plainText[i] ^ encryptKey[keyIndex]) & 0xff) + lastCipherByte) & 0xff;
                    cipherBuffer.put((byte) lastCipherByte);
                }

                if (isRecord)
                {
                    str.append("\n");
                    str.append(toHexDump("Encrpted Bytes:", cipherBuffer.array(), 0, cipherBuffer.position()));
                    str.append(toHexDump("Encrpted Keys", encryptKey, 0, encryptKey.length));
                    str.append("Encrpted Keys hasdcode:").append(encryptKey.hashCode());
                    LOGGER.error(str.toString());
                }

                out.write(cipherBuffer.flip());
            }
            else
            {
                short checkSum = msg.calcChecksum(buffer.array());
                byte byte1 = (byte) (checkSum >> 8);
                byte byte2 = (byte) checkSum;
                buffer.put(4, byte1);
                buffer.put(5, byte2);
                buffer.flip();
                out.write(buffer);
            }
        }
        catch (Exception ex)
        {
            LOGGER.error("catch error for encoding packet:", ex);
            throw ex;
        }
    }

    // 获取当前加密密钥
    private int[] getContext(IoSession session)
    {
        int[] keys = (int[]) session.getAttribute(StrictMessageEncoder.class);
        if (keys == null)
        {
            keys = new int[] { 0xae, 0xbf, 0x56, 0x78, 0xab, 0xcd, 0xef, 0xf1 };
            LOGGER.error("getContext keys is null, set default keys");
        }
        return keys;
    }

    public static void setKey(IoSession session, int[] key)
    {
        // Log.error(ToHexDump("Chang keys:", key, 0, key.length));
        // Log.error("error", new Exception());
        session.setAttribute(StrictMessageEncoder.class, key);
    }

   

    @SuppressWarnings("static-access")
    private IoBuffer encodeByte(CommonMessage CommonMessage) throws IOException
    {
        byte[] bytes = CommonMessage.getBytes();
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
        CommonMessage.writeHeader(size, buffer); // 协议头部
        if (bytes != null)
        {
            buffer.put(bytes);
        }

        return buffer;
    }

    public static String toHexDump(String description, int[] dump, int start, int count)
    {
        String hexDump = "";
        if (description != null)
        {
            hexDump += description;
            hexDump += "\n";
        }
        int end = start + count;
        for (int i = start; i < end; i += 16)
        {
            String text = "";
            String hex = "";

            for (int j = 0; j < 16; j++)
            {
                if (j + i < end)
                {
                    int val = dump[j + i];
                    if (val < 0)
                        val = (val + 256) & 0xFF;
                    if (val < 16)
                    {
                        hex += "0" + Integer.toHexString(val) + " ";
                    }
                    else
                    {
                        hex += Integer.toHexString(val) + " ";
                    }

                    if (val >= 32 && val <= 127)
                    {
                        text += (char) val;
                    }
                    else
                    {
                        text += ".";
                    }
                }
                else
                {
                    hex += "   ";
                    text += " ";
                }
            }
            hex += "  ";
            hex += text;
            hex += '\n';
            hexDump += hex;
        }
        return hexDump;
    }

    public static String toHexDump(String description, byte[] dump, int start, int count)
    {
        int[] temps = new int[dump.length];
        System.arraycopy(dump, 0, temps, 0, dump.length);
        return toHexDump(description, temps, start, count);
    }
}
