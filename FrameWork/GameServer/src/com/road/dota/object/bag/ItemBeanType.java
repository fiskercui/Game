/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.object.bag;

/**
 * @author : cookie
 * @date : 2013-4-16
 * @version 
 * 物品模板类型
 */
public enum ItemBeanType
{
    PROP(1),        //道具
    EQUIP(2);       //装备
    
    private byte value;
    /**
     * 
     */
    private ItemBeanType(int value)
    {
        this.setValue((byte) value);
    }
    /**
     * @param value the value to set
     */
    public void setValue(byte value)
    {
        this.value = value;
    }
    /**
     * @return the value
     */
    public byte getValue()
    {
        return value;
    }
}
