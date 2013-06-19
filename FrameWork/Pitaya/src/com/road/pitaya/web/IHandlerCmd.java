/**
 * Date: 2013-5-21
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web;


/**
 * 处理指令接口
 * @author yip
 */
public interface IHandlerCmd
{
    /**
     * 处理json String
     * @param jsonString 要处理的String
     * @return  返回的String
     */
    String execute(String jsonString);
    
    /**
     * 通过Request传入的其他数据
     * @param jsonString 处理的json String
     * @param otherStrings
     * @return
     */
    String execute(String jsonString,String ... otherStrings);
}
