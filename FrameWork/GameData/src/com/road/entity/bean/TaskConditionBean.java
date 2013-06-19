/**
 * Date: 2013-5-31
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.entity.bean;

/**
 * 任务条件表
 * @author yutao.chen
 */
public class TaskConditionBean
{
    //条件ID
    private int conditionID;
    
    //任务ID
    private int taskID;
    
    //条件标题
    private String condictionTitle;
    
    //条件类型
    private int  conditionType;
    
    //条件参数字段
    private int param1;
    
    private int param2;

    /**
     * @return the conditionID
     */
    public int getConditionID()
    {
        return conditionID;
    }

    /**
     * @param conditionID the conditionID to set
     */
    public void setConditionID(int conditionID)
    {
        this.conditionID = conditionID;
    }

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
     * @return the condictionTitle
     */
    public String getCondictionTitle()
    {
        return condictionTitle;
    }

    /**
     * @param condictionTitle the condictionTitle to set
     */
    public void setCondictionTitle(String condictionTitle)
    {
        this.condictionTitle = condictionTitle;
    }

    /**
     * @return the conditionType
     */
    public int getConditionType()
    {
        return conditionType;
    }

    /**
     * @param conditionType the conditionType to set
     */
    public void setConditionType(int conditionType)
    {
        this.conditionType = conditionType;
    }

    /**
     * @return the param1
     */
    public int getParam1()
    {
        return param1;
    }

    /**
     * @param param1 the param1 to set
     */
    public void setParam1(int param1)
    {
        this.param1 = param1;
    }

    /**
     * @return the param2
     */
    public int getParam2()
    {
        return param2;
    }

    /**
     * @param param2 the param2 to set
     */
    public void setParam2(int param2)
    {
        this.param2 = param2;
    }
    
    
}
