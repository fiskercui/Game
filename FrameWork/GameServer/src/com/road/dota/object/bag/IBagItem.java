/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.object.bag;

import com.road.dota.type.BagType;

/**
 * @author cookie.hu
 * @date 2013-5-27
 * @version 
 * 所有能放进背包的物品的抽象
 */
interface IBagItem
{
    /**
     * 取得物品归属者ID
     * @return
     */
    int getUserID();
    
    /**
     * 取能够叠加的最大数量
     * @return
     */
    int getMaxStack();
    
    /**
     * 设置数量
     * @param count
     */
    void setCount(short count);
    
    /**
     * 返回物品数量
     * @return
     */
    short getCount();

    /**
     * 取当前物品所在的背包中的位置
     * @param place
     * @return
     */
    void setPlace(int place);
    
    /**
     * 返回当前物品在背包中的位置
     * @return
     */
    int getPlace();
    
    /**
     * 取得当前物品所能放置的背包类型
     * @return
     */
    BagType getBagType();
    
    /**
     * 设置当前物品的背包类型
     * @param type
     *          背包类型
     */
    void setBagType(BagType type);
    
    /**
     * 是否是新增物品
     * @return
     */
    boolean isNewItem();
    
    /**
     * 设置是否是新增物品
     * @param isNew
     */
    void setIsNewItem(boolean isNew);

    /**
     * 能否叠加到另外一个物品
     * @param item
     *          另外一个物品
     * @return
     */
    boolean canStackedTo(IBagItem item);

    /**
     * 克隆一件物品
     * @return
     */
    IBagItem cloneItem();
    
}
