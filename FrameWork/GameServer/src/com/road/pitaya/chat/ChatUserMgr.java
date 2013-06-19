package com.road.pitaya.chat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.event.EventArg;
import com.road.pitaya.event.IEventListener;

/**
 * 聊天用户管理 用户登入登出的时候，需要清理对应的id
 * 
 * @author weihua.cui
 * 
 */
public class ChatUserMgr implements IEventListener
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChatUserMgr.class);
    /**
     * Key: PlayerId ChannelUserInfo： 用户基本信息
     */
    private Map<Integer, ChannelUserInfo> mapPlayer = new ConcurrentHashMap<Integer, ChannelUserInfo>();

    /**
     * 向聊天管理器中添加用户 用户有更新频道信息的时候需要调用此接口，比如说更换帮派（切记！！！）
     * 
     * @param id
     * @param player
     * @return
     */
    public boolean addUser(int id)
    {
        // 先删除原有信息
        delUser(id);

        ChannelUserInfo userInfo = new ChannelUserInfo(id);
        if(userInfo.isInitSuccess() == false)
            return false;
        mapPlayer.put(id, userInfo);
        ChannelManager.getInstance().addUser(ChannelType.CHANNEL_WORLD,
                userInfo.getWorldChannelId(), userInfo.getUserId());
        ChannelManager.getInstance().addUser(ChannelType.CHANNEL_CLAW,
                userInfo.getClawChannelId(), userInfo.getUserId());
        ChannelManager.getInstance().addUser(ChannelType.CHANNEL_NEAR,
                userInfo.getNearChannelId(), userInfo.getUserId());
        return true;
    }

    /**
     * 向聊天管理器中删除用户
     * 
     * @param id
     * @return
     */
    public boolean delUser(int id)
    {
        ChannelUserInfo userInfo = mapPlayer.get(id);
        if (userInfo == null)
        {
            LOGGER.error("userinfo  null. ID: " + id);
            return true;
        }
        ChannelManager.getInstance().delUser(ChannelType.CHANNEL_WORLD,
                userInfo.getWorldChannelId(), userInfo.getUserId());
        ChannelManager.getInstance().delUser(ChannelType.CHANNEL_CLAW,
                userInfo.getClawChannelId(), userInfo.getUserId());
        ChannelManager.getInstance().delUser(ChannelType.CHANNEL_NEAR,
                userInfo.getNearChannelId(), userInfo.getUserId());
        mapPlayer.remove(id);
        return true;
    }

    private static class LazyHolder
    {
        private static ChatUserMgr INSTANCE = new ChatUserMgr();
    }

    public static ChatUserMgr getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void onEvent(EventArg arg)
    {
        int userId = (int) arg.getData();
        addUser(userId);
    }
}
