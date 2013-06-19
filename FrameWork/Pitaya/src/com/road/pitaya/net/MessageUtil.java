package com.road.pitaya.net;

/**
 * 辅助类
 * 
 */
public class MessageUtil {

    /**
     * 消息创建
     * 
     * @param code
     * @param messageBuilder
     * @return
     */
    public static CommonMessage buildMessage(short code,  byte[] bytes)
    {
        CommonMessage message = new CommonMessage(code, -1);
        message.setBytes(bytes);
        return message;      
    }
    
    public static CommonMessage buildMessage(short code, int client, byte[] bytes)
    {
        CommonMessage message = new CommonMessage(code, client);
        message.setBytes(bytes);
        return message;      
    }
    
    
}
