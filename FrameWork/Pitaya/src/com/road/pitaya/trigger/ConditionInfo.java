package com.road.pitaya.trigger;
/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/

import java.util.ArrayList;


/**
 * @author : cookie
 * @date 2012-11-26
 * @version 
 * 条件的配置信息
 */
public class ConditionInfo
{
    private short conditionID;
    
    private LogicType logicType;
    
    private boolean needNew;
    
    private String params;
    
    private String errMsg;
    
    private ArrayList<ConditionInfo> sonInfo;

    /**
     * @return the conditionID
     */
    public short getConditionID()
    {
        return conditionID;
    }

    /**
     * @param conditionID the conditionID to set
     */
    public void setConditionID(String conditionID)
    {
        if (conditionID == null || conditionID.isEmpty())
        {
            this.conditionID = 0;
            return;
        }
        this.conditionID = Short.parseShort(conditionID);
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
        //如果没有设置，则认为是and
        if (logicType == null || logicType.isEmpty())
        {
            this.logicType = LogicType.AND;
            return;
        }
        if (logicType.equals("or"))
        {
            this.logicType = LogicType.OR;
        }
        else
        {
            this.logicType = LogicType.AND;
        }
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
        }
        this.needNew = Boolean.parseBoolean(needNew);
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
     * @param errMsg the errMsg to set
     */
    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    /**
     * @return the errMsg
     */
    public String getErrMsg()
    {
        return errMsg;
    }

    /**
     * @param sonInfo the sonInfo to set
     */
    public void setSonInfo(ArrayList<ConditionInfo> sonInfo)
    {
        this.sonInfo = sonInfo;
    }

    /**
     * @return the sonInfo
     */
    public ArrayList<ConditionInfo> getSonInfo()
    {
        return sonInfo;
    }
    
    
}
