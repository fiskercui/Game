package com.road.pitaya.trigger;
/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
import java.util.List;


/**
 * @author : cookie
 * @date 2012-11-27
 * @version 
 * 动作基本配置
 */
public class ActionInfo
{
    //动作ID
    private short actionID;
    
    //子动作的逻辑关系
    private LogicType logicType;
    
    //子动作
    private List<ActionInfo> sonActions;
    
    //如果动作需要重新创建的话，则作为构造参数
    private String params;
    
    //是否需要重新创建
    private boolean needNew;
    
    /**
     * @return the actionID
     */
    public short getActionID()
    {
        return actionID;
    }

    /**
     * @param actionID the actionID to set
     */
    public void setActionID(String actionID)
    {
        if (actionID == null || actionID.isEmpty())
        {
            this.actionID = 0;
            return;
        }
        this.actionID = Short.parseShort(actionID);
    }

    /**
     * @return the logicType
     */
    public LogicType getLogicType()
    {
        return logicType;
    }

    /**
     * @param logicType the logicType to set
     */
    public void setLogicType(String logicType)
    {
        if (logicType == null || logicType.isEmpty())
        {
            this.logicType = LogicType.AND;
        }
        this.logicType = LogicType.parse(logicType);
    }

    /**
     * @return the sonActions
     */
    public List<ActionInfo> getSonActions()
    {
        return sonActions;
    }

    /**
     * @param sonActions the sonActions to set
     */
    public void setSonActions(List<ActionInfo> sonActions)
    {
        this.sonActions = sonActions;
    }

    /**
     * @return the params
     */
    public String getParams()
    {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(String params)
    {
        this.params = params;
    }

    /**
     * @return the needNew
     */
    public boolean isNeedNew()
    {
        return needNew;
    }

    /**
     * @param needNew the needNew to set
     */
    public void setNeedNew(String needNew)
    {
        if (needNew == null || needNew.isEmpty())
        {
            this.needNew = false;
            return;
        }
        this.needNew = Boolean.parseBoolean(needNew);
    }
}
