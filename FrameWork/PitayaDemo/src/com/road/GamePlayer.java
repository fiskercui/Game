package com.road;

import java.util.List;

import com.road.dota.module.inf.IModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.type.ModuleType;
import com.road.entity.info.PlayerInfo;
import com.road.pitaya.chat.ChannelType;
import com.road.pitaya.command.SelfDrivenTaskQueue;
import com.road.pitaya.command.UserCmdTask;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.net.IClientConnection;

public class GamePlayer implements IGamePlayer
{
    @SuppressWarnings("unused")
    private String name = null;
    private Integer id = 0;
    
    private IClientConnection clientConn = null;
    // 玩家数据的自驱动队列
    private SelfDrivenTaskQueue<UserCmdTask> cmdQueue = new SelfDrivenTaskQueue<UserCmdTask>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    public GamePlayer(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public void onDisconnect()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        cmdQueue.add((UserCmdTask) task);
    }

    @Override
    public void finishOneCommandTask()
    {
        cmdQueue.complete();
    }

    @Override
    public String getSequenceTaskName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IClientConnection getClientConnection()
    {
        return clientConn;
    }

    @Override
    public void setClientConnection(IClientConnection conn)
    {
        clientConn = conn;
    }

    @Override
    public IModule getModule(ModuleType type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean initModules()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean loadModuleData()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean saveModuleData()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getUserID()
    {
        // TODO Auto-generated method stub
        return this.id;
    }
    /**
     * 得到世界服聊天具体ID
     * @return
     */
    private int getWorldChannelId()
    {
        return 0;
        
    }
    /**
     * 得到帮派服聊天具体Id
     * @return
     */
    private int getClawChannelId()
    {
        return 0;
        
    }
    /**
     * 得到附件频道聊天具体Id
     * @return
     */
    private int getNearChannelId()
    {
        return 0;
    }

    @Override
    public int getChannelID(byte channelType)
    {
        switch(channelType)
        {
        case ChannelType.CHANNEL_WORLD:
            return getWorldChannelId();
        case ChannelType.CHANNEL_CLAW:
            return getClawChannelId();    
        case ChannelType.CHANNEL_NEAR:
            return getNearChannelId();      }
        return 0;
    }

    @Override
    public PlayerInfo getPlayerInfo()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<Integer> getDynamicChannelID(byte channelType)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int removeMoney(int value)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int addGold(int rewardGold)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int addExp(int exp)
    {
        // TODO Auto-generated method stub
        return 0;
    }


}
