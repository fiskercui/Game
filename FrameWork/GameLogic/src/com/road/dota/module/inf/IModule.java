/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.module.inf;

import com.road.dota.type.ModuleType;

/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 人物模块相前接口
 */
public interface IModule
{
    /**
     * 初始化
     */
    boolean init();
    
    /**
     * 读DB
     * @return
     */
    boolean loadFromDB();
    
    
    /**
     * 写DB
     * @return
     */
    boolean saveIntoDB();
    
    /**
     * 得到模块类型
     * @return
     */
    ModuleType getType();
}
