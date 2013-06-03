/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.task.condition;

import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.task.BaseTask;
import com.road.entity.bean.TaskConditionBean;
import com.road.pitaya.event.IEventListener;

/**
 * @author yutao.chen
 * @date 2013-5-23
 * @version 
 *任务条件基类
 */
public abstract class BaseTaskCondition implements IEventListener
{
    /**
     * 任务信息
     */
    private BaseTask baseTask;
    
    /**
     * 完成条件数据
     */
    private int value;
    
    private TaskConditionBean conditionBean;
    
    /**
     * 添加监听
     * @param player
     */
    public abstract void addListener(IGamePlayer player);

    /**
     * 移除监听
     * @param player
     */
    public abstract void removeListener(IGamePlayer player);
    
    /**
     * 完成任务
     * @param player
     * @return
     */
    public boolean finish(IGamePlayer player)
    {
        return true;
    }
    
    public boolean isCompleted(IGamePlayer player)
    {
        return getValue() <= 0;
    }

    /**
     * @return the value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value)
    {
        if (this.value != value)
        {
            this.value = value;
            baseTask.update();
        }
    }

    /**
     * @param gamePlayer
     */
    public void reset(IGamePlayer gamePlayer)
    {
        setValue(conditionBean.getParam2());
    }
}
