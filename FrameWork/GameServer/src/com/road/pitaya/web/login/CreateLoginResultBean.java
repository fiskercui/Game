/**
 * Date: 2013-5-29
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import com.google.gson.annotations.SerializedName;

/**
 * 创建登录使用的实体
 * 
 * @author yip
 */
public class CreateLoginResultBean
{
    /**
     * 结果枚举
     */
    @SerializedName("result")
    private Integer result;

    /**
     * @return the result
     */
    public Integer getResult()
    {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(Integer result)
    {
        this.result = result;
    }

}
