package com.road.pitaya.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.util.ConfigWrapper;

/**
 * 聊天频道管理器
 * 
 * @author weihua.cui
 */

public class ChannelManager
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChannelManager.class);
    /**
     * 是否有网关
     */
    private final boolean existGameWay = ConfigWrapper.getValue("Gateway").equals("1");
    /**
     * Key:频道类型 value：ChannelService
     */
    private Map<Byte, ChannelService> mapChannelService = new HashMap<Byte, ChannelService>();

    private ChannelManager()
    {
        mapChannelService.put(ChannelType.CHANNEL_WORLD, new ChannelService());
        mapChannelService.put(ChannelType.CHANNEL_CLAW, new ChannelService());
        mapChannelService.put(ChannelType.CHANNEL_NEAR, new ChannelService());
    }

    private static class LazyHolder
    {
        private static ChannelManager INSTANCE = new ChannelManager();
    }

    public static ChannelManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    /**
     * 是否为1vn
     * 
     * @param channel
     * @return
     */
    public boolean isPublicChannel(final byte channel)
    {
        if (channel < ChannelType.CHANNEL_PUBLIC_START
                || channel > ChannelType.CHANNEL_PUBLIC_END)
            return false;
        return true;
    }
    
    /**
     * 是否为1v1
     * @param channel
     * @return
     */
    public boolean isPrivateChannel(final byte channel)
    {
        return channel == ChannelType.CHANNEL_PRIVATE;
    }
    /**
     * 没有注册的频道， 需要动态获取接受者列表
     * 
     * @param channel
     * @return
     */
    public boolean isDynamicChannel(final byte channel)
    {
        if (channel == ChannelType.CHANNEL_NEAR_DYNAMIC)
            return true;
        return false;
    }

    /**
     * 频道注册用户
     * 
     * @param channelType
     *            频道类型
     * @param channelId
     *            频道id
     * @param Userid
     *            用户id
     * @return
     */e
    public boolean addUser(byte channelType, int channelId, int userId)
    {
        ChannelService channelService = mapChannelService.get(channelType);
        if (channelService != null)
        {
            return channelService.addUser(channelId, userId);
        }
        else
        {
            LOGGER.error("Channel type not found:" + channelType);
            return false;
        }
    }

    /**
     * 从频道删除用户
     * 
     * @param channelType
     *            频道类型
     * @param channelId
     *            频道Id
     * @param Userid
     *            用户Id
     * @return
     */
    public boolean delUser(byte channelType, int channelId, int userId)
    {
        ChannelService channelService = mapChannelService.get(channelType);
        if (channelService != null)
        {
            return channelService.delUser(channelId, userId);
        }
        else
        {
            LOGGER.error("Channel type not found:" + channelType);
            return true;
        }
    }

    /**
     * 1vn分发消息
     * 
     * @param sendId
     *            发送者ID
     * @param channelType
     *            聊天频道
     * @param channelId
     *            聊天频道ID
     * @param message
     *            消息包
     * @return  返回用户列表
     */
    public List<Integer> getChannelUserList(int sendId, byte channelType,int channelId)
    {
        if (isDynamicChannel(channelType))
        {
            // TODO
            return ChannelMsgHandler.getInstance().getDynamicChannelUserList(sendId, channelType);
        }
        ChannelService service = mapChannelService.get(channelType);
        if (service == null)
        {
            return null;
        }
        /** 得到具体频道的用户列表*/
        return service.getChannelUserList(channelId);
    }
    
    public void onDispatchMessage(int sendID, List<Integer> userList, Object msg)
    {
        if(isExistGameway())
        {
            ChannelMsgHandler.getInstance().onGatewayMessage(sendID, msg);
        }
        else
        {
            ChannelMsgHandler.getInstance().onClientMessage(sendID, userList, msg);    
        }
    }
    
    public void onDispatchMessage(int sendID, int recvId, Object msg)
    {
        ChannelMsgHandler.getInstance().onClientMessage(sendID, recvId, msg);
    }
    
    public boolean isExistGameway()
    {
        return existGameWay;
    }

}
