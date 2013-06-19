/**
 * Date: 2013-5-30
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.component.inf;

import com.road.dota.object.bag.BaseItem;
import com.road.entity.bean.ItemBean;
import com.road.pitaya.component.IComponent;

/**
 * 物品模板相关
 * @author Cookie.hu
 */
public interface IItemComponent extends IComponent
{
    /**
     * 取得物品模板Id
     * @param beanId
     *          模板id
     * @return
     *          物品模板
     */
    ItemBean getItemBeanById(int beanId);
    
    /**
     * 根据模板id创建一件无归属物品
     * @param beanId
     *          模板id
     * @param count
     *          物品数量
     * @return
     *          物品
     */
    BaseItem createItem(int beanId, int count);
}
