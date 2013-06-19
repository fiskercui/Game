/**
 * Date: 2013-5-29
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.entity.bean;

/**
 * 物品模板，包括道具和装备
 * @author Cookie.hu
 */
public class ItemBean
{
    /* 物品ID */
    private int beanId;

    /* 物品名字 */
    private String name;

    /* bean类型，是属于装备还是道具；1道具:2装备 */
    private byte beanType;

    /* 物品类型 (如果beanType为装备则:1.头盔 2.铠甲 3.靴子 4.主手 5.副手 6.远程 7.项链 8.戒指  9.勋章)*/
    private byte type;

    /* 叠加数目 */
    private int maxStack;

    /**
     * @return the beanId
     */
    public int getBeanId()
    {
        return beanId;
    }

    /**
     * @param beanId the beanId to set
     */
    public void setBeanId(int beanId)
    {
        this.beanId = beanId;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the beanType
     */
    public byte getBeanType()
    {
        return beanType;
    }

    /**
     * @param beanType the beanType to set
     */
    public void setBeanType(byte beanType)
    {
        this.beanType = beanType;
    }

    /**
     * @return the type
     */
    public byte getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(byte type)
    {
        this.type = type;
    }

    /**
     * @return the maxStack
     */
    public int getMaxStack()
    {
        return maxStack;
    }

    /**
     * @param maxStack the maxStack to set
     */
    public void setMaxStack(int maxStack)
    {
        this.maxStack = maxStack;
    }

}
