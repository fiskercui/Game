/**
 * Date: 2013-5-27
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import com.google.gson.annotations.SerializedName;

/**
 * 登录信息实体
 * 
 * @author yip
 */

public class LoginRecvBean
{
    /**
     * 各种加密后的字符串  
     */
    @SerializedName("p")
    private String p;

    /**
     * 登录的区名
     */
    @SerializedName("site")
    private String site;

    /**
     * 是否客户端
     */
    @SerializedName("client")
    private Boolean client;

    /**
     * 网络名称，可以去掉
     */
    @SerializedName("webname")
    private String webname;

    /**
     * 玩家登录的ip，不需要客户端传值。通过request取得。
     */
    @SerializedName("ip")
    private String ip;

    public String getP()
    {
        return this.p;
    }

    public void setP(String p)
    {
        this.p = p;
    }

    public String getSite()
    {
        return this.site;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public Boolean getClient()
    {
        return this.client;
    }

    public void setClient(Boolean client)
    {
        this.client = client;
    }

    public String getWebname()
    {
        return this.webname;
    }

    public void setWebname(String webname)
    {
        this.webname = webname;
    }

    public String getIp()
    {
        return this.ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

}
