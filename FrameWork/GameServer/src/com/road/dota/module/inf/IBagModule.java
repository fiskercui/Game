/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.module.inf;

/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 人物的背包模块
 */
public interface IBagModule
{
    void moveItem(short fromPlace, short toPlace, int count, boolean isCanReplace);
}
