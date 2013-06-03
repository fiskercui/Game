/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.type;

/**
 * @author yutao.chen
 * @date 2013-5-31
 * @version 
 *悬赏任务品质
 */
public enum RewardTaskQualityType
{
    NULL(-1),
    WHITE(0),//白色任务
    BlUE(1),//蓝色任务
    PURPLE(2),//紫色任务
    YELLOW(3);//黄色任务
    
    private byte value;
    
    private RewardTaskQualityType(int value)
    {
        this.value = (byte) value;
    }
    
    /**
     * @return the value
     */
    public byte getValue()
    {
        return value;
    }
    
    public static RewardTaskQualityType parse(int value)
    {
        RewardTaskQualityType type = RewardTaskQualityType.WHITE;
        switch (value)
        {
            case 0:
                type = RewardTaskQualityType.WHITE;
                break;
            case 1:
                type = RewardTaskQualityType.BlUE;
                break;
            case 2:
                type = RewardTaskQualityType.PURPLE;
                break;
            case 3:
                type = RewardTaskQualityType.YELLOW;
                break;
        }
        return type;
    }
}

