/**
 * Date: 2013-5-30
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.component.inf.IItemComponent;
import com.road.dota.object.bag.BaseItem;
import com.road.entity.bean.ItemBean;

/**
 * 物品组件
 * @author Cookie.hu
 */
public class ItemComponent implements IItemComponent
{
    /*日志记录器*/
    private static Logger logger = LoggerFactory.getLogger(ItemComponent.class.getName());
    
    /*物品模板*/
    private static Map<Integer, ItemBean> itemBeanMap;
    
    /*读写锁*/
    private static ReadWriteLock rwLock;
    
    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return "ItemComponent";
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        itemBeanMap = new HashMap<Integer, ItemBean>();
        rwLock = new ReentrantReadWriteLock();
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#destroy()
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.IItemComponent#getItemBeanById(int)
     */
    @Override
    public ItemBean getItemBeanById(int beanId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.IItemComponent#createItem(int, int)
     */
    @Override
    public BaseItem createItem(int beanId, int count)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
