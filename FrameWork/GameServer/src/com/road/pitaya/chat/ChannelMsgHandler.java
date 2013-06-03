package com.road.pitaya.chat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.object.inf.IGamePlayer;
import com.road.pitaya.command.GamePlayerMgr;
import com.road.pitaya.net.CommonMessage;

/**
 * 聊天消息的分发
 * 
 * @author weihua.cui
 */

public class ChannelMsgHandler
{
    private  final Logger LOGGER = LoggerFactory
            .getLogger(ChannelMsgHandler.class);
    
    private static class LazyHolder
    {
        private static ChannelMsgHandler INSTANCE = new ChannelMsgHandler();
    }

    public static ChannelMsgHandler getInstance()
    {
        return LazyHolder.INSTANCE;
    }
    /**
     * 聊天信息的分发
     * @param sendId 发送者ID
     * @param recvId 接受者ID
     * @param msg 消息对象
     */
    public void onClientMessage(int sendId, int recvId, Object msg)
    {
        if (sendId == recvId)
        {
            LOGGER.info("player cannot chat to self");
            return;
        }
        IGamePlayer player = GamePlayerMgr.getInstance().getGamePlayer(recvId);
        if (player == null)
        {
            LOGGER.info("player not online");
            return;
        }
        player.getClientConnection().send((CommonMessage) msg);
    }
    
    /**
     * 聊天信息的分发 直接下发用户
     * @param sendId
     * @param recvIdList
     * @param msg
     */
    public void onClientMessage(int sendId, List<Integer> recvIdList, Object msg)
    {
        for(Integer i: recvIdList)
        {
            onClientMessage(sendId, i, msg);
        }
    }
    /**
     * 聊天信息的分发 打包转发网关
     * @param sendId
     * @param recvIdList
     * @param msg
     */
    public void onGatewayMessage(int sendId, Object msg)
    {
        IGamePlayer player = GamePlayerMgr.getInstance().getGamePlayer(sendId);
        if (player == null)
        {
            LOGGER.info("player not online");
            return;
        } 
        //TODO  发送给网关
        player.getClientConnection().send(msg);
    }
    
    /**
     * 根据玩家Id获取动态频道信息
     * @param sendId
     * @param channelType
     * @param msg
     */
    public List<Integer> getDynamicChannelUserList(int sendId, int channelType)
    {
        IGamePlayer player = GamePlayerMgr.getInstance().getGamePlayer(sendId);
        if (player == null)
        {
            LOGGER.info("player not online");
            return null;
        } 
        return player.getDynamicChannelID((byte) channelType);
    }
}
