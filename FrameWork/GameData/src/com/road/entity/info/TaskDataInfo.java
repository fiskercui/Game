/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.entity.info;

import java.util.Date;

import com.road.entity.DataObject;


/**
 * @author yutao.chen
 * @date 2013-5-27
 * @version 
 *用户任务信息表
 */
public class TaskDataInfo extends DataObject
{
    /**
     * 任务ID
     */
    private int taskID;
    
    /**
     * userID
     */
    private int userID;
    
    /**
     * 是否完成
     */
    private boolean isComplete;
    
    /**
     * 完成日期
     */
    private Date completedDate;
    
    /**
     * 条件参数
     */
    private int condition1;
    
    private int condition2;
    
    private int condition3;
    
    private int condition4;
    
    /**
     * 是否存在
     */
    private boolean isExist;
    
    
    /**
     * 可重复次数
     */
    private int repeatCount;
    
    /**
     * 任务品质
     */
    private byte taskQuality = 0;
    
    public boolean getIsComplete()
    {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete)
    {
        if (this.isComplete != isComplete)
        {
            this.isComplete = isComplete;
            setOp(DataOption.UPDATE);
        }
    }
    
    public Date getCompletedDate()
    {
        return this.completedDate;
    }

    public boolean getExistInCurrent()
    {
        return !this.isComplete;
    }

    public void setCompletedDate(Date completedDate)
    {
        if (this.completedDate != completedDate)
        {
            this.completedDate = completedDate;
            setOp(DataOption.UPDATE);
        }
    }

    public int getCondition1()
    {
        return this.condition1;
    }

    public void setCondition1(int condition1)
    {
        if (this.condition1 != condition1)
        {
            this.condition1 = condition1;
            setOp(DataOption.UPDATE);
        }
    }

    public int getCondition2()
    {
        return this.condition2;
    }

    public void setCondition2(int condition2)
    {
        if (this.condition2 != condition2)
        {
            this.condition2 = condition2;
            setOp(DataOption.UPDATE);
        }
    }

    public int getCondition3()
    {
        return this.condition3;
    }

    public void setCondition3(int condition3)
    {
        if (this.condition3 != condition3)
        {
            this.condition3 = condition3;
            setOp(DataOption.UPDATE);
        }
    }

    public int getCondition4()
    {
        return this.condition4;
    }

    public void setCondition4(int condition4)
    {
        if (this.condition4 != condition4)
        {
            this.condition4 = condition4;
            setOp(DataOption.UPDATE);
        }
    }

    public boolean getIsExist()
    {
        return this.isExist;
    }

    public void setIsExist(boolean isExist)
    {
        if (this.isExist != isExist)
        {
            this.isExist = isExist;
            setOp(DataOption.UPDATE);
        }
    }

    public int getRepeatCount()
    {
        return this.repeatCount;
    }

    public void setRepeatCount(int repeatCount)
    {
        if (this.repeatCount != repeatCount)
        {
        this.repeatCount = repeatCount;
        setOp(DataOption.UPDATE);
        }
    }


    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setTaskID(int taskID)
    {
        this.taskID = taskID;
    }

    public int getTaskId()
    {
        return taskID;
    }

    public int getConditionValue(int index)
    {
        switch (index)
        {
            case 0:
                return condition1;
            case 1:
                return condition2;
            case 2:
                return condition3;
            case 3:
                return condition4;
            default:
                return -1;
        }
    }

    public void saveConditionValue(int index, int value)
    {
        switch (index)
        {
            case 0:
                setCondition1(value);
                break;
            case 1:
                setCondition2(value);
                break;
            case 2:
                setCondition3(value);
                break;
            case 3:
                setCondition4(value);
                break;
            default:
                break;
        }
    }


    /**
     * @return the taskQuality
     */
    public byte getTaskQuality()
    {
        return taskQuality;
    }

    /**
     * @param taskQuality the taskQuality to set
     */
    public void setTaskQuality(byte taskQuality)
    {
        this.taskQuality = taskQuality;
    }
    
}
