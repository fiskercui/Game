package com.road.pitaya.chat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 频道所有类型的聊天集合
 * @author weihua.cui
 */

public class ChannelService
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChannelService.class);
    /**
     * Integer具体的聊天频道ID 
     * Channel 聊天频道
     */
    private Map<Integer, Channel> mapChannel = new ConcurrentHashMap<Integer, Channel>();

    /**
     * 注册用户
     * @param channelId
     * @param userId
     * @return
     */
    public boolean addUser(Integer channelId, Integer userId)
    {
        Channel channel = mapChannel.get(channelId);
        if (channel == null)
        {
            channel = new Channel();
            channel.addUser(userId);
            mapChannel.put(channelId, channel);
        }
        else
        {
            channel.addUser(userId);
            mapChannel.put(channelId, channel);
        }
        return true;
    }

    /**
     * 删除用户
     * @param channelId
     * @param userId
     * @return
     */
    public boolean delUser(Integer channelId, Integer userId)
    {
        Channel channel = mapChannel.get(channelId);
        if (channel != null)
        {
            channel.delUser(userId);
        }
        return true;
    }

    /**
     * 向具体频道下发消息
     * @param sendId
     * @param channelId
     * @param message
     * @return
     */
    public List<Integer> getChannelUserList(int channelId)
    {
        Channel service = mapChannel.get(channelId);
        if (service == null)
        {
            LOGGER.error("channel id not found " + channelId);
            return null;
        }
        return service.getChannelUserList();
    }

}
