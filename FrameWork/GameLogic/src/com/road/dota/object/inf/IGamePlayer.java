/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.object.inf;

import java.util.List;

import com.road.dota.module.inf.IModule;
import com.road.dota.type.ModuleType;
import com.road.entity.info.PlayerInfo;
import com.road.pitaya.game.ISequenceTask;
import com.road.pitaya.net.IConnectionHolder;

/**
 * @author cookie.hu
 * @date 2013-5-21
 * @version 
 * 人物接口
 */
public interface IGamePlayer extends ISequenceTask , IConnectionHolder
{
    /**
     * 返回用户ID
     * @return
     */
    int getUserID();
    
    /**
     * @return用户信息
     */
    PlayerInfo getPlayerInfo();
    /**
     * 返回人物某一模块
     * @param type
     *          模块类型
     * @return
     *          模块接口
     */
    IModule getModule(ModuleType type);
    
    /**
     * 初始化模块
     * @return
     *          是否成功
     */
    boolean initModules();
    
    /**
     * 加载模块数据
     * @return
     *          是否成功
     */
    boolean loadModuleData();
    
    /**
     * 保存模块数据
     * @return
     *          是否成功
     */
    boolean saveModuleData();
    
    
    /**
     * 返回具体聊天频道ID
     * @param channelType 频道类型
     * @return
     */
    public int getChannelID(byte channelType);
    
    /**
     * 移除点券
     * @param value
     * @return value
     */
    public int removeMoney(int value);
    /**
     * 动态获取该频道的用户列表
     * @param channelType
     * @return
     */
    public List<Integer> getDynamicChannelID(byte channelType);
     

    /**
     * @param rewardGold
     * @param b
     * @return
     * 添加金币
     */
    int addGold(int rewardGold);

    /**
     * @param exp
     * @return
     */
    int addExp(int exp);
}
