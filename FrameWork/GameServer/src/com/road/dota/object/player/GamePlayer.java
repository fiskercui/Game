/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.object.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.module.BagModule;
import com.road.dota.module.inf.IModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.type.ModuleType;
import com.road.entity.info.PlayerInfo;
import com.road.pitaya.chat.ChannelType;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.event.EventSource;
import com.road.pitaya.net.IClientConnection;


/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 人物对象
 */
public class GamePlayer extends EventSource implements IGamePlayer
{
    /*日志记录*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentManager.class);
    
    /**
     * 
     */
    private IClientConnection clientConn = null;
    
    /*模块缓存*/
    private Map<ModuleType, IModule> moduleMap;
    
    private PlayerInfo playerInfo;
    /**
     * 构造人物对象
     */
    public GamePlayer()
    {
        moduleMap = new HashMap<ModuleType, IModule>();
        moduleMap.put(ModuleType.BAG, new BagModule(this));
    }
    
    @Override
    public IModule getModule(ModuleType type)
    {
        return moduleMap.get(type);
    }

    @Override
    public boolean initModules()
    {
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.init())
                {
                    LOGGER.error(module.getType() + " module init error.");
                    return false;
                };
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module init error.", e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean loadModuleData()
    {
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.loadFromDB())
                {
                    LOGGER.error(module.getType() + " module load data from db error.");
                    return false;
                };
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module load data from db error:", e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveModuleData()
    {
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.saveIntoDB())
                {
                    LOGGER.error(module.getType() + " module save data from db error.");
                    return false;
                };
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module load save from db error:", e);
                return false;
            }
        }
        return true;
    }
    /* (non-Javadoc)
     * @see com.road.dota.object.inf.IGamePlayer#getUserID()
     */
    @Override
    public int getUserID()
    {
        return 0;
    }
    @Override
    public String getSequenceTaskName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void finishOneCommandTask()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDisconnect()
    {
        // TODO Auto-generated method stub
        
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

    /**
     * 得到世界服聊天具体ID
     * @return
     */
    private int getWorldChannelId()
    {
        //TODO
        return 0;
    }
    /**
     * 得到帮派服聊天具体Id
     * @return
     */
    private int getClawChannelId()
    {
        //TODO
        return 0;   
    }
    /**
     * 得到附件频道聊天具体Id
     * @return
     */
    private int getNearChannelId()
    {
        //TODO
        return 0;
    }

    /**
     * @return the playerInfo
     */
    public PlayerInfo getPlayerInfo()
    {
        return playerInfo;
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

    /* (non-Javadoc)
     * @see com.road.dota.object.inf.IGamePlayer#removeMoney(int)
     */
    @Override
    public int removeMoney(int value)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.inf.IGamePlayer#addGold(int)
     */
    @Override
    public int addGold(int rewardGold)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.inf.IGamePlayer#addExp(int)
     */
    @Override
    public int addExp(int exp)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Integer> getDynamicChannelID(byte channelType)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
