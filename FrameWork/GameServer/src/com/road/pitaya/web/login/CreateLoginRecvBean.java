/**
 * Date: 2013-5-29
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import com.google.gson.annotations.SerializedName;

/**
 * 创建登录使用的实体
 * @author yip
 */
public class CreateLoginRecvBean
{
    /**
     * 用户名+"|"+密码+"|"+不明内容+"|"+"加密后字符串"
     */
    @SerializedName("content")
    private String content;

    /**
     * 登录的区号
     */
    @SerializedName("site")
    private String site;

    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getSite()
    {
        return this.site;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

}
