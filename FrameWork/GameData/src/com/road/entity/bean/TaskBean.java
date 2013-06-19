/**
 * Date: 2013-5-27
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.entity.bean;

import java.util.Date;

/**
 * 任务模板类
 * @author yutao.chen
 */
public class TaskBean
{
    /**
     * 任务ID
     */
    private int taskID;
    
    /**
     * 任务标题
     */
    private String title;
    
    /**
     * 任务描述
     */
    private String detail;
    
    /**
     * 任务类型
     */
    private byte taskType;
    
    /**
     * 任务子类型 收集类，杀怪类，通关类……
     */
    private byte subType;
    /**
     * 任务开始时间
     */
    private Date beginDate;
    
    /**
     * 任务结束时间
     */
    private Date endDate;
    
    /**
     *接受该任务的最小等级，玩家等级小于此等级则无法接受该任务
     */
    private int needMinLevel;
    
    /**
     * 接受该任务的最大等级，玩家等级大于此等级则无法接受该任务
     */
    private int needMaxLevel;
    
    /**
     * 该任务的前置任务，可以有复数个("|"分割)，接到该任务需要完成所有前置任务
     */
    private String preTaskID;
    
    /**
     * 任务是否可重复
     */
    private boolean canRepeat;
    
    /**
     * 一定时间间隔内可重复的次数
     */
    private short repeatMax;
    
    /**
     * 重复间隔
     */
    private int repeatInterval;

    /**
     * 奖励金币
     */
    private int rewardGold;
    
    /**
     * 奖励经验
     */
    private int rewardExp;
    
    /**
     * @return the taskID
     */
    public int getTaskID()
    {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID)
    {
        this.taskID = taskID;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the detail
     */
    public String getDetail()
    {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    /**
     * @return the taskType
     */
    public byte getTaskType()
    {
        return taskType;
    }

    /**
     * @param taskType the taskType to set
     */
    public void setTaskType(byte taskType)
    {
        this.taskType = taskType;
    }

    /**
     * @return the beginDate
     */
    public Date getBeginDate()
    {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * @return the needMinLevel
     */
    public int getNeedMinLevel()
    {
        return needMinLevel;
    }

    /**
     * @param needMinLevel the needMinLevel to set
     */
    public void setNeedMinLevel(int needMinLevel)
    {
        this.needMinLevel = needMinLevel;
    }

    /**
     * @return the needMaxLevel
     */
    public int getNeedMaxLevel()
    {
        return needMaxLevel;
    }

    /**
     * @param needMaxLevel the needMaxLevel to set
     */
    public void setNeedMaxLevel(int needMaxLevel)
    {
        this.needMaxLevel = needMaxLevel;
    }

    /**
     * @return the preTaskID
     */
    public String getPreTaskID()
    {
        return preTaskID;
    }

    /**
     * @param preTaskID the preTaskID to set
     */
    public void setPreTaskID(String preTaskID)
    {
        this.preTaskID = preTaskID;
    }

    /**
     * @return the canRepeat
     */
    public boolean isCanRepeat()
    {
        return canRepeat;
    }

    /**
     * @param canRepeat the canRepeat to set
     */
    public void setCanRepeat(boolean canRepeat)
    {
        this.canRepeat = canRepeat;
    }

    /**
     * @return the repeatMax
     */
    public short getRepeatMax()
    {
        return repeatMax;
    }

    /**
     * @param repeatMax the repeatMax to set
     */
    public void setRepeatMax(short repeatMax)
    {
        this.repeatMax = repeatMax;
    }

    /**
     * @return the repeatInterval
     */
    public int getRepeatInterval()
    {
        return repeatInterval;
    }

    /**
     * @param repeatInterval the repeatInterval to set
     */
    public void setRepeatInterval(int repeatInterval)
    {
        this.repeatInterval = repeatInterval;
    }

    /**
     * @return the subType
     */
    public byte getSubType()
    {
        return subType;
    }

    /**
     * @param subType the subType to set
     */
    public void setSubType(byte subType)
    {
        this.subType = subType;
    }

    /**
     * @return the rewardExp
     */
    public int getRewardExp()
    {
        return rewardExp;
    }

    /**
     * @param rewardExp the rewardExp to set
     */
    public void setRewardExp(int rewardExp)
    {
        this.rewardExp = rewardExp;
    }

    /**
     * @return the rewardGold
     */
    public int getRewardGold()
    {
        return rewardGold;
    }

    /**
     * @param rewardGold the rewardGold to set
     */
    public void setRewardGold(int rewardGold)
    {
        this.rewardGold = rewardGold;
    }
    
}
