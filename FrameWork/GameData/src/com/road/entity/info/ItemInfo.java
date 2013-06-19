/**
 * Date: 2013-5-29
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.entity.info;


import java.util.Date;

import com.road.entity.DataObject;

/**
 * 物品动态信息
 * @author Cookie.hu
 */
public class ItemInfo extends DataObject
{
    /*物品唯一ID*/
    private int itemId;
    
    /*物品模板ID*/
    private int templateId;
    
    /*物品在背包中的位置*/
    private short place;
    
    /*用户唯一ID*/
    private int userId;
    
    /*物品数量*/
    private short count;
    
    /*是否绑定*/
    private boolean isBind;
    
    /*是否存在*/
    private boolean isExist;
    
    /*添加日期*/
    private Date addDate;
    
    /*添加类型*/
    private byte addType;
    
    /*删除日期*/
    private Date removeDate;
    
    /*删除类型*/
    private byte removeType;
    
    /*背包类型*/
    private byte bagType;

    /**
     * @return the itemId
     */
    public int getItemId()
    {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    /**
     * @return the templateId
     */
    public int getTemplateId()
    {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(int templateId)
    {
        this.templateId = templateId;
    }

    /**
     * @return the place
     */
    public short getPlace()
    {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(short place)
    {
        this.place = place;
    }

    /**
     * @return the userId
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * @return the count
     */
    public short getCount()
    {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(short count)
    {
        this.count = count;
    }

    /**
     * @return the isBind
     */
    public boolean isBind()
    {
        return isBind;
    }

    /**
     * @param isBind the isBind to set
     */
    public void setBind(boolean isBind)
    {
        this.isBind = isBind;
    }

    /**
     * @return the isExist
     */
    public boolean isExist()
    {
        return isExist;
    }

    /**
     * @param isExist the isExist to set
     */
    public void setExist(boolean isExist)
    {
        this.isExist = isExist;
    }

    /**
     * @return the addDate
     */
    public Date getAddDate()
    {
        return addDate;
    }

    /**
     * @param addDate the addDate to set
     */
    public void setAddDate(Date addDate)
    {
        this.addDate = addDate;
    }

    /**
     * @return the addType
     */
    public byte getAddType()
    {
        return addType;
    }

    /**
     * @param addType the addType to set
     */
    public void setAddType(byte addType)
    {
        this.addType = addType;
    }

    /**
     * @return the removeDate
     */
    public Date getRemoveDate()
    {
        return removeDate;
    }

    /**
     * @param removeDate the removeDate to set
     */
    public void setRemoveDate(Date removeDate)
    {
        this.removeDate = removeDate;
    }

    /**
     * @return the removeType
     */
    public byte getRemoveType()
    {
        return removeType;
    }

    /**
     * @param removeType the removeType to set
     */
    public void setRemoveType(byte removeType)
    {
        this.removeType = removeType;
    }

    /**
     * @return the bagType
     */
    public byte getBagType()
    {
        return bagType;
    }

    /**
     * @param bagType the bagType to set
     */
    public void setBagType(byte bagType)
    {
        this.bagType = bagType;
    }
}
