/**
 * Date: 2013-5-28
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.road.entity.SimpleUser;

/**
 * 登录步骤一/结果回包
 * 
 * @author yip
 */
public class LoginSelectResultBean
{
    /**
     * 是否成功
     */
    @SerializedName("result")
    private Boolean result;

    /**
     * 具体信息
     */
    @SerializedName("message")
    private String message;
    
    /**
     * 
     */
    @SerializedName("playerInfos")
    private List<SimpleUser> playerInfos;

    /**
     * @return the result
     */
    public Boolean getResult()
    {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Boolean result)
    {
        this.result = result;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @return the playerInfos
     */
    public List<SimpleUser> getPlayerInfos()
    {
        return playerInfos;
    }

    /**
     * @param playerInfos the playerInfos to set
     */
    public void setPlayerInfos(List<SimpleUser> playerInfos)
    {
        this.playerInfos = playerInfos;
    }
    
}
