/**
 * Date: 2013-5-28
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.register;

import com.google.gson.annotations.SerializedName;

/**
 * 注册消息反馈实体
 * 
 * @author yip
 */

public class VisualRegisterResultBean
{
    /**
     * 是否成功
     */
    @SerializedName("result")
    private Boolean result;

    /**
     * 注册信息
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 
     */
    @SerializedName("message")
    private String message;

    /**
     * @param result
     * @param type
     * @param message
     */
    public VisualRegisterResultBean(boolean result, int type, String message)
    {
        this.result = result;
        this.type = type;
        this.message = message;
    }

    public Boolean getResult()
    {
        return this.result;
    }

    public void setResult(Boolean result)
    {
        this.result = result;
    }

    public Integer getType()
    {
        return this.type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
