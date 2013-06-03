package com.road.entity;

/**
 * 玩家的简要信息
 * 
 * @author yip
 */
public class SimpleUser
{

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public Integer getGrades()
    {
        return grades;
    }

    public void setGrades(Integer grades)
    {
        this.grades = grades;
    }


    public Short getSexs()
    {
        return sexs;
    }

    public void setSexs(Short sexs)
    {
        this.sexs = sexs;
    }

    public boolean getActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public String getSite()
    {
        return site;
    }

    public void setIsOnline(boolean isOnline)
    {
        this.isOnline = isOnline;
    }

    public boolean getIsOnline()
    {
        return isOnline;
    }

    public int userId;
    public String userName;
    public String nickName;
    public Integer grades;
    public Short sexs;
    public boolean active;
    private String site;
    private boolean isOnline;
}
