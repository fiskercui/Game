package com.road.pitaya.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.object.inf.IGamePlayer;
import com.road.pitaya.command.GamePlayerMgr;

/**
 * 聊天用户管理器中的用户基本信息
 * 
 * @author weihua.cui
 */

public class ChannelUserInfo
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChannelUserInfo.class);
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 世界频道Id
     */
    private int worldChannelId = 0;
    /**
     * 帮派频道Id
     */
    private int clawChannelId = 0;
    /**
     * 附近频道id
     */
    private int nearChannelId = 0;

    private boolean initSuccess  = false;
    public ChannelUserInfo(int userId)
    {
        refreshUserInfo(userId);
    }

    public void refreshUserInfo(int  playerId)
    {
        IGamePlayer player = GamePlayerMgr.getInstance().getGamePlayer(playerId);
        if (player == null)
        {
            LOGGER.error("cannot find player");
            return;
        }
        this.initSuccess = true;
        this.userId = player.getUserID();
        this.worldChannelId = player.getChannelID(ChannelType.CHANNEL_WORLD);
        this.clawChannelId = player.getChannelID(ChannelType.CHANNEL_CLAW);
        this.nearChannelId = player.getChannelID(ChannelType.CHANNEL_NEAR);
    }

    public int getWorldChannelId()
    {
        return worldChannelId;
    }

    public int getUserId()
    {
        return userId;
    }

    public int getClawChannelId()
    {
        return clawChannelId;
    }

    public int getNearChannelId()
    {
        return nearChannelId;
    }

    public boolean isInitSuccess()
    {
        return initSuccess;
    }



}
