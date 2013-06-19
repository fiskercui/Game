package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/


/**
 * @author : cookie
 * @date 2012-11-22
 * @version 
 *
 */
public enum AiType
{
    GAME(0),        //游戏AI即监听游戏事件，控制游戏流程
    LIVING(1);      //生物AI即监听生物事件，控制生物行为
    
    private byte value;
    /**
     * 
     */
    private AiType(int value)
    {
        this.value = (byte)value;
    }
    
    public byte getValue()
    {
        return value;
    }
    
    public static AiType parse(int value)
    {
        AiType result = LIVING;
        switch (value)
        {
            case 0:
                result = GAME;
                break;
            case 1:
                result = LIVING;
                break;
            default:
                break;
        }
        return result;
    }
}
