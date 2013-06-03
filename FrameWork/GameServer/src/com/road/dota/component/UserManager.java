/**
 * Date: 2013-5-29
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.road.bll.DaoManager;
import com.road.dota.component.inf.IUserLoginComponent;
import com.road.entity.AccountInfo;
import com.road.pitaya.component.IComponent;

/**
 * 
 * @author yip
 */
public class UserManager implements IUserLoginComponent,IComponent
{
    /**
     * 处于登录的用户缓存，该主键为用户，注意用户(userName)可能有多个userId
     */
    public Map<String, String> usersInLogin;
    /**
     * 准备上线的玩家
     */
    public Map<Integer,String> usersOnline;
    
    public static final String NAME = "UserManager";

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.component.inf.IUserComponent#addLogin(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean addLogin(String userNameAndSite, String tempPassWord)
    {
        if(tempPassWord == null || userNameAndSite == null || tempPassWord.trim().equals(""))
        {
            return false;
        }
        usersInLogin.put(userNameAndSite, tempPassWord);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.component.inf.IUserComponent#checkLogin(java.lang.String, java.lang.String)
     */
    @Override
    public boolean checkLogin(String userName)
    {
        return usersInLogin.containsKey(userName);
    }

    /*
     * (non-Javadoc)
     * @see com.road.dota.component.inf.IUserComponent#checkLogin(java.lang.String, java.lang.String)
     */
    public boolean checkLogin(String userName,String passwd)
    {
        return passwd.equals(usersInLogin.get(userName));
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.IUserLoginComponent#removeUser(java.lang.String)
     */
    @Override
    public void removeUser(String userName)
    {
        this.usersInLogin.remove(userName);
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.IUserLoginComponent#updateLoginUser(com.road.entity.AccountInfo)
     */
    @Override
    public void updateLoginUser(AccountInfo info)
    {
        info.setIsOnline(true);
        DaoManager.getAccountInfoDao().update(info);
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.IUserLoginComponent#createOnline(int, java.lang.String)
     */
    @Override
    public boolean createOnline(int userId, String password)
    {
        this.usersOnline.put(userId, password);
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
       usersInLogin = new ConcurrentHashMap<String, String>();
       usersOnline = new ConcurrentHashMap<Integer, String>();
       return true;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#destroy()
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }
}
