/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.module;

import com.road.dota.module.inf.IModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.type.ModuleType;

/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 人物模块的基类
 */
public abstract class PlayerModule implements IModule
{
    protected IGamePlayer player;
    
    private ModuleType moduleType;
    
    public PlayerModule(IGamePlayer player, ModuleType moduleType)
    {
        this.setPlayer(player);
        this.moduleType = moduleType;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(IGamePlayer player)
    {
        this.player = player;
    }

    /**
     * @return the player
     */
    public IGamePlayer getPlayer()
    {
        return player;
    }

    /**
     * @return the moduleType
     */
    public ModuleType getType()
    {
        return moduleType;
    }

    
}
