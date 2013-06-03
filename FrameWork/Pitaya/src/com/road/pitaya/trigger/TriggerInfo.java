package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/


/**
 * @author : cookie
 * @date 2012-11-27
 * @version 
 * 单个触发器配置
 */
public class TriggerInfo
{
    private int tirggerID;
    
    private AiType type;
    
    private ConditionInfo conditionInfo;
    
    private ActionInfo yesActionInfo;

    private ActionInfo noActionInfo;
    /**
     * @return the tirggerID
     */
    public int getTirggerID()
    {
        return tirggerID;
    }

    /**
     * @param tirggerID the tirggerID to set
     */
    public void setTirggerID(int tirggerID)
    {
        this.tirggerID = tirggerID;
    }

    /**
     * @return the type
     */
    public AiType getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AiType type)
    {
        this.type = type;
    }

    /**
     * @return the conditionInfo
     */
    public ConditionInfo getConditionInfo()
    {
        return conditionInfo;
    }

    /**
     * @param conditionInfo the conditionInfo to set
     */
    public void setConditionInfo(ConditionInfo conditionInfo)
    {
        this.conditionInfo = conditionInfo;
    }

    /**
     * @return the actionInfo
     */
    public ActionInfo getYesActionInfo()
    {
        return yesActionInfo;
    }

    /**
     * @param actionInfo the actionInfo to set
     */
    public void setYesActionInfo(ActionInfo actionInfo)
    {
        this.yesActionInfo = actionInfo;
    }

    /**
     * @param noActionInfo the noActionInfo to set
     */
    public void setNoActionInfo(ActionInfo noActionInfo)
    {
        this.noActionInfo = noActionInfo;
    }

    /**
     * @return the noActionInfo
     */
    public ActionInfo getNoActionInfo()
    {
        return noActionInfo;
    }
    
    
}
