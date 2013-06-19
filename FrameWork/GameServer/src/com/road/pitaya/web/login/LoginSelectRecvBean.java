/**
 * Date: 2013-5-28
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import com.google.gson.annotations.SerializedName;

/**
 * 多个角色返回结果
 * @author yip
 */
public class LoginSelectRecvBean
{
    @SerializedName("userName")
    private String userName;

    @SerializedName("site")
    private String site;

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName=userName;
    }

    public String getSite()
    {
        return this.site;
    }

    public void setSite(String site)
    {
        this.site=site;
    }

}
