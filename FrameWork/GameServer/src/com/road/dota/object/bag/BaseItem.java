/**
 * Date: 2013-5-30
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.object.bag;

import com.road.dota.type.BagType;
import com.road.entity.bean.ItemBean;
import com.road.entity.info.ItemInfo;

/**
 * 物品
 * @author Cookie.hu
 */
public class BaseItem implements IBagItem
{
    /*物品模板*/
    private ItemBean itemBean;
    
    /*物品动态信息*/
    private ItemInfo itemInfo;

    /**
     * @param itemBean
     * @param itemInfo
     */
    public BaseItem(ItemBean itemBean, ItemInfo itemInfo)
    {
        super();
        this.itemBean = itemBean;
        this.itemInfo = itemInfo;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#getUserID()
     */
    @Override
    public int getUserID()
    {
        return itemInfo.getUserId();
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#getMaxStack()
     */
    @Override
    public int getMaxStack()
    {
        return itemBean.getMaxStack();
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#setCount(short)
     */
    @Override
    public void setCount(short count)
    {
        itemInfo.setCount(count);
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#getCount()
     */
    @Override
    public short getCount()
    {
        return itemInfo.getCount();
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#setPlace(int)
     */
    @Override
    public void setPlace(int place)
    {
        itemInfo.setPlace((short) place);
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#getPlace()
     */
    @Override
    public int getPlace()
    {
        return itemInfo.getPlace();
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#getBagType()
     */
    @Override
    public BagType getBagType()
    {
        return BagType.parse(itemInfo.getBagType());
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#setBagType(com.road.dota.type.BagType)
     */
    @Override
    public void setBagType(BagType type)
    {
        itemInfo.setBagType(type.getValue());
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#isNewItem()
     */
    @Override
    public boolean isNewItem()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#setIsNewItem(boolean)
     */
    @Override
    public void setIsNewItem(boolean isNew)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#canStackedTo(com.road.dota.object.bag.IBagItem)
     */
    @Override
    public boolean canStackedTo(IBagItem item)
    {
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBagItem#cloneItem()
     */
    @Override
    public IBagItem cloneItem()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the itemBean
     */
    public ItemBean getItemBean()
    {
        return itemBean;
    }

    /**
     * @param itemBean the itemBean to set
     */
    public void setItemBean(ItemBean itemBean)
    {
        this.itemBean = itemBean;
    }

    /**
     * @return the itemInfo
     */
    public ItemInfo getItemInfo()
    {
        return itemInfo;
    }

    /**
     * @param itemInfo the itemInfo to set
     */
    public void setItemInfo(ItemInfo itemInfo)
    {
        this.itemInfo = itemInfo;
    }
}
