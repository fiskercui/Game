/**
 * Date: May 31, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.config;

/**
 * 数据库配置
 * 
 * @author jiayi.wei
 */
public class DatabaseConfig
{
    /** 配置名称 */
    private String name = null;
    /** 连接url */
    private String url = null;
    /** 登陆用户名 */
    private String username = null;
    /** 登陆密码 */
    private String password = null;

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ");
        sb.append(name);
        sb.append("\r\nurl: ");
        sb.append(url);
        sb.append("\r\nusername: ");
        sb.append(username);
        sb.append("\r\npassword: ");
        sb.append(password);
        return sb.toString();
    }
}
