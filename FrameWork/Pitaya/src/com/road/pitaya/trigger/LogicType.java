package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/


/**
 * @author : cookie
 * @date 2012-11-22
 * @version 
 * Ai结点之间的逻辑关系
 */
public enum LogicType
{
    AND(0),     //与
    
    OR(1),      //或
    
    RANDOM(2);      //随机执行N个
    
    private byte value;
    
    LogicType(int value)
    {
        this.value = (byte) value;
    }
    
    public byte getValue()
    {
        return value;
    }
    
    public static LogicType parse(int value)
    {
        LogicType result = AND;
        switch (value)
        {
            case 0:
                result = AND;
                break;
            case 1:
                result = OR;
                break;
            case 2:
                result = RANDOM;
                break;
            default:
                result = AND;
                break;
        }
        return result;
    }
    
    public static LogicType parse(String value)
    {
        LogicType result = AND;
        if (value == null ||value.isEmpty())
        {
            return result;
        }
        if (value.equals("or"))
        {
            result = OR;
        }
        else if (value.equals("random"))
        {
            result = RANDOM;
        }
        else
        {
            result = AND;
        }
        return result;
    }
}
