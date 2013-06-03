/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.type;

/**
 * @author : cookie
 * @date : 2012-12-14
 * @version 
 * 物品的移除类型
 */
public enum ItemRemoveType
{
    NOTREMOVE(0),
    STACK(1),           //叠加
    DISCARD(2),         //丢弃
    GEMCOMPOSE(3),      //宝石合成
    OTHER(20);          //其它
    
    private byte value;
    
    ItemRemoveType(int value)
    {
        this.value = (byte) value;
    }
    public byte getValue()
    {
        return value;
    }
    
    public static ItemRemoveType parse(int value)
    {
        switch (value)
        {
            case 0:
                return NOTREMOVE;
            case 1:
                return STACK;
            case 2:
                return OTHER;
            default:
                return NOTREMOVE;
        }
    }
}
