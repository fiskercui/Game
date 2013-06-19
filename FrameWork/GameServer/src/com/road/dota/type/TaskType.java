/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.type;

/**
 * @author yutao.chen
 * @date 2013-5-30
 * @version 
 *
 */
public interface TaskType
{
    /**
     * 主线
     */
    public final static byte MAIN = 0;
    
    /**
     *支线 
     */
    public final static byte BRANCH = 1;
    /**
     * 悬赏任务
     */
    public final static byte REWARD = 2;
    
    /**
     * 环任务
     */
    public final static byte LINK = 3;
    
    
}
