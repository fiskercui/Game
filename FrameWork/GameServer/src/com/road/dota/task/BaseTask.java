/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.task;

import java.util.List;

import com.road.dota.module.inf.ITaskModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.task.condition.BaseTaskCondition;
import com.road.dota.type.ModuleType;
import com.road.entity.bean.TaskBean;
import com.road.entity.info.DataObject.DataOption;
import com.road.entity.info.TaskDataInfo;
import com.road.util.TimeUtil;


/**
 * @author yutao.chen
 * @date 2013-5-23
 * @version 
 *任务基类
 */
public class BaseTask
{

    //玩家
    protected IGamePlayer gamePlayer=null;
    
    //用户任务信息
    protected TaskDataInfo userTaskDataInfo;
    
    //任务模板
    protected TaskBean taskTemplate;

    //条件列表
    protected List<BaseTaskCondition> conditionList;
 
    /**
     * 
     * @author yutao.chen
     * @date 2013-5-23
     * @version 
     *任务状态枚举类
     */
    protected enum TaskState
    {
        //进行中
        incompleted(0),
        //完成
        completed(1),
        //已提交
        submit(2);
        private int type;
        TaskState(int type)
        {
            this.type=type;
        }
        
        public byte getValue()
        {
            return (byte) type;
        }
    }
    
    public BaseTask(TaskDataInfo userTaskDataInfo,TaskBean taskTemplate)
    {
        this.userTaskDataInfo = userTaskDataInfo;
        this.taskTemplate = taskTemplate;
    }
    
    public boolean finish()
    {
        if (canComplete())
        {
            for (BaseTaskCondition cd : conditionList)
            {
                if (cd.finish(gamePlayer) == false)
                {
                    return false;
                }
            }
            removeTrigger(gamePlayer);
            userTaskDataInfo.setIsComplete(true);
            userTaskDataInfo.setRepeatCount(userTaskDataInfo.getRepeatCount() - 1);
//            oldFinishDate = userTaskDataInfo.getCompletedDate();
            userTaskDataInfo.setCompletedDate(TimeUtil.getSysteCurTime());
            return true;
        }
        return false;
        
    }
    
    public boolean canComplete()
    {
        if (userTaskDataInfo.getIsComplete())
            return false;

        for (BaseTaskCondition cd : conditionList)
        {
            if (!cd.isCompleted(gamePlayer))
                return false;
        }
        return true;
    }
    
    /**
     * @param gamePlayer
     */
    private void addTrigger(IGamePlayer gamePlayer)
    {
        for(BaseTaskCondition condition:conditionList)
        {
            condition.addListener(gamePlayer);
        }
    }
    
    private void removeTrigger(IGamePlayer player)
    {
        for (BaseTaskCondition condition : conditionList)
        {
            condition.removeListener(player);
        }
    }
    
    /**
     * 初始化任务:用户添加任务时调用
     * @param gamePlayer
     */
    public void reset()
    {
        userTaskDataInfo.setTaskID(taskTemplate.getTaskID());
        userTaskDataInfo.setUserID(gamePlayer.getUserID());
        userTaskDataInfo.setIsComplete(false);
        for (BaseTaskCondition cd : conditionList)
        {
            cd.reset(gamePlayer);
        }
        saveData();
    }
    
    /**
     * 
     */
    private void saveData()
    {
        int index = 0;
        for (BaseTaskCondition cd : conditionList)
        {
            userTaskDataInfo.saveConditionValue(index++, cd.getValue());
        }
    }

    public void update()
    {
        saveData();
        if (userTaskDataInfo.getOp() != DataOption.NONE && gamePlayer != null)
        {
            if (taskTemplate.isCanRepeat())
                userTaskDataInfo.setCompletedDate(TimeUtil.getSysteCurTime());
            
//            //判断是否完成任务
//            if(canFinish())
//                userTaskDataInfo.setQuestState(TaskState.completed.getValue());
            
           (( ITaskModule)gamePlayer.getModule(ModuleType.TASK)).update(this);
        }
    }

    /**
     * @param player
     */
    public void removeFromPlayer(IGamePlayer player)
    {
        if (!userTaskDataInfo.getIsComplete())
        {
            removeTrigger(player);
            if (taskTemplate.isCanRepeat())
            {
                userTaskDataInfo.setIsComplete(true);
            }
        }
        gamePlayer = null;        
    }
    
    public void addToPlayer(IGamePlayer gamePlayer)
    {
        this.gamePlayer=gamePlayer;
        
        //判断是否重置
            if (TimeUtil.dateCompare(userTaskDataInfo.getCompletedDate(),TimeUtil.getSysteCurTime()) >= taskTemplate.getRepeatInterval())
            {
                userTaskDataInfo.setRepeatCount(taskTemplate.getRepeatMax());
            }
        if(!userTaskDataInfo.getIsComplete())
        {
            //增加监听
            addTrigger(gamePlayer);
        }
    }
    
    /**
     * @return the gamePlayer
     */
    public IGamePlayer getGamePlayer()
    {
        return gamePlayer;
    }

    /**
     * @param gamePlayer the gamePlayer to set
     */
    public void setGamePlayer(IGamePlayer gamePlayer)
    {
        this.gamePlayer = gamePlayer;
    }

    /**
     * @return the userTaskDataInfo
     */
    public TaskDataInfo getUserTaskDataInfo()
    {
        return userTaskDataInfo;
    }

    /**
     * @param userTaskDataInfo the userTaskDataInfo to set
     */
    public void setUserTaskDataInfo(TaskDataInfo userTaskDataInfo)
    {
        this.userTaskDataInfo = userTaskDataInfo;
    }

    /**
     * @return the conditionList
     */
    public List<BaseTaskCondition> getConditionList()
    {
        return conditionList;
    }

    /**
     * @param conditionList the conditionList to set
     */
    public void setConditionList(List<BaseTaskCondition> conditionList)
    {
        this.conditionList = conditionList;
    }
    
    /**
     * @return the taskTemplate
     */
    public TaskBean getTaskTemplate()
    {
        return taskTemplate;
    }

    /**
     * @param taskTemplate the taskTemplate to set
     */
    public void setTaskTemplate(TaskBean taskTemplate)
    {
        this.taskTemplate = taskTemplate;
    }

}

