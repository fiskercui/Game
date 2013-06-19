/**
 * Date: 2013-5-28
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import com.google.gson.annotations.SerializedName;

/**
 * 玩家信息实体
 * 
 * @author yip
 */
public class PlayerInfoBean
{
    @SerializedName("ID")
    private Integer ID;

    @SerializedName("UserName")
    private Integer UserName;

    @SerializedName("NickName")
    private String NickName;

    @SerializedName("Grade")
    private Integer Grade;

    @SerializedName("Repute")
    private Integer Repute;

    @SerializedName("Sex")
    private Integer Sex;

    @SerializedName("Rename")
    private Integer Rename;

    @SerializedName("Active")
    private Boolean Active;

    @SerializedName("Site")
    private String Site;

    @SerializedName("IsOnline")
    private Boolean IsOnline;

    public Integer getID()
    {
        return this.ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public Integer getUserName()
    {
        return this.UserName;
    }

    public void setUserName(Integer UserName)
    {
        this.UserName = UserName;
    }

    public String getNickName()
    {
        return this.NickName;
    }

    public void setNickName(String NickName)
    {
        this.NickName = NickName;
    }

    public Integer getGrade()
    {
        return this.Grade;
    }

    public void setGrade(Integer Grade)
    {
        this.Grade = Grade;
    }

    public Integer getRepute()
    {
        return this.Repute;
    }

    public void setRepute(Integer Repute)
    {
        this.Repute = Repute;
    }

    public Integer getSex()
    {
        return this.Sex;
    }

    public void setSex(Integer Sex)
    {
        this.Sex = Sex;
    }

    public Integer getRename()
    {
        return this.Rename;
    }

    public void setRename(Integer Rename)
    {
        this.Rename = Rename;
    }

    public Boolean getActive()
    {
        return this.Active;
    }

    public void setActive(Boolean Active)
    {
        this.Active = Active;
    }

    public String getSite()
    {
        return this.Site;
    }

    public void setSite(String Site)
    {
        this.Site = Site;
    }

    public Boolean getIsOnline()
    {
        return this.IsOnline;
    }

    public void setIsOnline(boolean IsOnline)
    {
        this.IsOnline = IsOnline;
    }

}
