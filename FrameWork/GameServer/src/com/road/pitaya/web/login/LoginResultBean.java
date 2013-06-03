/**
 * Date: 2013-5-27
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import com.google.gson.annotations.SerializedName;

/**
 * 登录步骤三/结果回包
 * 
 * @author yip
 */

public class LoginResultBean
{
    /**
     * 登录成功与否
     */
    @SerializedName("result")
    private Boolean result;

    /**
     * 具体信息
     */
    @SerializedName("message")
    private String message;

    /**
     * 玩家ID
     */
    @SerializedName("userId")
    private Integer userId;
    
    /**
     * 分配的地址
     */
    @SerializedName("address")
    private String address;
    
    /**
     * 分配的端口
     */
    @SerializedName("port")
    private String port; 
    
    /**
     * 不带分配地址和端口的构造方法。这时不返回该信息，可以通过set该信息
     * @param result 是否成功
     * @param message 返回信息
     * @param userId  用户ID
     */
    public LoginResultBean(boolean result,String message,int userId)
    {
        this.result = result;
        this.message = message;
        this.userId = userId;
    }

    public Boolean getResult()
    {
        return this.result;
    }

    public void setResult(Boolean result)
    {
        this.result = result;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return the port
     */
    public String getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port)
    {
        this.port = port;
    }

}
