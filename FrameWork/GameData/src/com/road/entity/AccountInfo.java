/**
 * Date: 2013-5-27
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.entity;

import java.util.Date;

/**
 * 玩家帐号信息
 * 
 * @author yip
 */
public class AccountInfo
{
    /**
     * 账户ID，内部管理全局唯一ID，和玩家角色
     */
    private int userId;
    /**
     * 帐号名称
     */
    private String username;
    /**
     * 玩家所在的游戏区，标识用于合区
     */
    private String site;
    /**
     * 账户密码（yip-添加,如果有平台帐号就不用了。)
     */
    private String userPasswd;
    /**
     * 是否在线
     */
    private boolean isOnline;
    /**
     * 玩家创建账号的时间
     */
    private Date createDate;
    /**
     * 禁言时间，小于当前时间则解封
     */
    private Date forbidDate;
    /**
     * 上次充值时间
     */
    private Date lastPayDate;
    /**
     * 上次登录IP
     */
    private String lastLoginIP;
    /**
     * 上次登录时间
     */
    private Date lastLoginDate;
    /**
     * 上一天最后登录时间
     */
    private Date lastLogin2Date;
    /**
     * 上上一天最后登录时间
     */
    private Date lastLogin3Date;
    /**
     * 最后登出时间
     */
    private Date lastLogoutDate;
    /**
     * 帐号是否已激活，（添加用于查看是否完成注册步骤。）
     */
    private boolean isActived;
    /**
     * @return the userID
     */
    public int getUserId()
    {
        return userId;
    }
    /**
     * @param userID the userID to set
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    /**
     * @return the site
     */
    public String getSite()
    {
        return site;
    }
    /**
     * @param site the site to set
     */
    public void setSite(String site)
    {
        this.site = site;
    }
    /**
     * @return the userPasswd
     */
    public String getUserPasswd()
    {
        return userPasswd;
    }
    /**
     * @param userPasswd the userPasswd to set
     */
    public void setUserPasswd(String userPasswd)
    {
        this.userPasswd = userPasswd;
    }
    /**
     * @return the isOnline
     */
    public boolean getIsOnline()
    {
        return isOnline;
    }
    /**
     * @param isOnline the isOnline to set
     */
    public void setIsOnline(boolean isOnline)
    {
        this.isOnline = isOnline;
    }
    /**
     * @return the createDate
     */
    public Date getCreateDate()
    {
        return createDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    /**
     * @return the forbidDate
     */
    public Date getForbidDate()
    {
        return forbidDate;
    }
    /**
     * @param forbidDate the forbidDate to set
     */
    public void setForbidDate(Date forbidDate)
    {
        this.forbidDate = forbidDate;
    }
    /**
     * @return the lastPayDate
     */
    public Date getLastPayDate()
    {
        return lastPayDate;
    }
    /**
     * @param lastPayDate the lastPayDate to set
     */
    public void setLastPayDate(Date lastPayDate)
    {
        this.lastPayDate = lastPayDate;
    }
    /**
     * @return the lastLoginIP
     */
    public String getLastLoginIP()
    {
        return lastLoginIP;
    }
    /**
     * @param lastLoginIP the lastLoginIP to set
     */
    public void setLastLoginIP(String lastLoginIP)
    {
        this.lastLoginIP = lastLoginIP;
    }
    /**
     * @return the lastLoginDate
     */
    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }
    /**
     * @param lastLoginDate the lastLoginDate to set
     */
    public void setLastLoginDate(Date lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }
    /**
     * @return the lastLogin2Date
     */
    public Date getLastLogin2Date()
    {
        return lastLogin2Date;
    }
    /**
     * @param lastLogin2Date the lastLogin2Date to set
     */
    public void setLastLogin2Date(Date lastLogin2Date)
    {
        this.lastLogin2Date = lastLogin2Date;
    }
    /**
     * @return the lastLogin3Date
     */
    public Date getLastLogin3Date()
    {
        return lastLogin3Date;
    }
    /**
     * @param lastLogin3Date the lastLogin3Date to set
     */
    public void setLastLogin3Date(Date lastLogin3Date)
    {
        this.lastLogin3Date = lastLogin3Date;
    }
    /**
     * @return the lastLogoutDate
     */
    public Date getLastLogoutDate()
    {
        return lastLogoutDate;
    }
    /**
     * @param lastLogoutDate the lastLogoutDate to set
     */
    public void setLastLogoutDate(Date lastLogoutDate)
    {
        this.lastLogoutDate = lastLogoutDate;
    }
    /**
     * @return the isActive
     */
    public boolean getIsActived()
    {
        return isActived;
    }
    /**
     * @param isActive the isActive to set
     */
    public void setIsActived(boolean isActived)
    {
        this.isActived = isActived;
    }
    
}
