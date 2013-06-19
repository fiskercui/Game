/**
 * Date: 2013-5-28
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.register;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author yip
 */

public class VisualRegisterRecvBean
{
    /**
     * 玩家ID
     */
    @SerializedName("userid")
    private String userid;

    /**
     * 用户名
     */
    @SerializedName("username")
    private String username;
    
    /**
     * 昵称
     */
    @SerializedName("nickname")
    private String nickname;

    /**
     * 性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 阵营
     */
    @SerializedName("camp")
    private String camp;

    /**
     * 头像
     */
    @SerializedName("icon")
    private String icon;

    /**
     * 区号
     */
    @SerializedName("site")
    private String site;

    /**
     * md5Key,网络不传明文，服务器也用md5校验
     */
    @SerializedName("key")
    private String key;

    /**
     * 是否客户端登录
     */
    @SerializedName("client")
    private Integer client;

    public String getUserid()
    {
        return this.userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public Integer getSex()
    {
        return this.sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public String getCamp()
    {
        return this.camp;
    }

    public void setCamp(String camp)
    {
        this.camp = camp;
    }

    public String getIcon()
    {
        return this.icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getSite()
    {
        return this.site;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public String getKey()
    {
        return this.key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Integer getClient()
    {
        return this.client;
    }

    public void setClient(Integer client)
    {
        this.client = client;
    }

}
