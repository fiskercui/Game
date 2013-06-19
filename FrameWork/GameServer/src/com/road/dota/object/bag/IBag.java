/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.object.bag;

import java.util.List;
import java.util.Map;

import com.road.dota.type.BagType;
import com.road.dota.type.ItemRemoveType;

/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 背包接口
 */
public interface IBag<T>
{
    /**
     * 初始化背包容器
     * @param class1
     *      背包内物品的类元数据
     */
    void initItems(Class<T> class1);

    /**
     * 标记某个位置有更新
     * @param place
     *      更新的位置
     */
    void onPlaceChanged(int place);
    
    /**
     * 对有更新的位置进行更新操作(比如说对更新的物品发送到)
     */
    void updateChangedPlaces();
    
    /**
     * 添加物品到背包
     * @param item
     *          待添加的物品
     * @param count
     *          物品的数量
     * @return
     *          是否添加成功
     */
    boolean addCountToStackUnCommit(T item, short count);
    
    /**
     * 添加物品到指定位置 ,如果待添加的位置已经存在物品，则添加失败
     * @param item
     *          待添加的物品
     * @param place
     *          指定位置
     * @param shouldCommit
     *          要不要提交到客户端:true,提交；false,不提交
     * @return
     */
    boolean addItem(T item, int place, boolean shouldCommit);

    /**
     * 物品添加到指定位置，如果指定位置存在物品，弹出该物品再添加新物品
     * @param item
     *          待添加的物品
     * @param place
     *          指定位置
     * @param shouldCommit
     *          要不要提交到客户端:true,提交；false,不提交
     * @return
     *          被替换的物品
     */
    T addItemReplace(T item, int place, boolean shouldCommit);

    /**
     * 添加多个物品,参叠加就叠加，不能叠加就找合适位置放下
     * @param item
     *          物品
     * @param count
     *          数量
     * @param place
     *          位置
     * @return
     *          是否添加成功
     */
    boolean addItemsAndCommit(T cloneItem, int count, int place);
    
    /**
     * 叠加当前背包所有能叠加的物品(未提交到客户端)
     * @return
     */
    boolean stackAllItemsUnCommit();

    /**
     * 移除某个物品
     * @param items
     * @param removeType
     * @return
     */
    boolean removeItem(T items, ItemRemoveType removeType);
    
    /**
     * 交换两个物品的位置，不提交到客户端
     * @param slot1
     *          位置1
     * @param slot2
     *          位置2
     * @return
     */
    boolean exchangeItemsUnCommit(int slot1, int slot2);
    
    /**
     * 将fromSlot位置的物品叠加到toSlot位置
     * @param fromSlot
     * @param toSlot
     * @param itemCount
     * @return
     */
    boolean stackItemsUnCommit(int fromSlot, int toSlot, int itemCount);
    
    /**
     * 将fromSlot位置上的count个物品移动到toSlot上面
     * @param fromSlot
     * @param toSlot
     * @param count
     * @return
     */
    boolean moveItemAndCommit(int fromSlot, int toSlot, int count);
    
    /**
     * 移除某件物品count个
     * @param item
     * @param count
     * @param itemRemoveType
     *          移除原因
     * @return
     */
    boolean removeCountFromStackUnCommit(T item, int count,
            ItemRemoveType itemRemoveType);
    
    /**
     * 清除指定位置范围的物品
     * @param minSlot
     *          清除的起始位置
     * @param maxSlot
     *          清除的结束位置
     * @param shouldCommit
     *          要不要提交到客户端:true,提交；false,不提交
     */
    void clearBag(int minSlot, int maxSlot, boolean shouldCommit);
    
    /**
     * 清除背包指位置范围的物品，不提交，不保存到更新列表
     * @param minSlot
     *          清除的起始位置
     * @param maxSlot
     *          清除的结束位置
     */
    void clearAndUnChange(int minSlot, int maxSlot);
    
    /**
     * 当前背包有多少空格
     * @return
     *          返回空格数
     */
    int getEmptyCount();
    
    /**
     * 取背包某个位置上的物品
     * @param slot
     *          位置
     * @return
     */
    T getItemAt(int slot);
    
    /**
     * 取背包所有物品
     * @return
     */
    List<T> getItems();
    
    /**
     * 取背包位置范围内的所有物品
     * @param minSlot
     * @param maxSlot
     * @return
     */
    List<T> getItems(int minSlot, int maxSlot);
    
    /**
     * 是否在背包格子范围内
     * @param slot
     * @return
     */
    boolean inBagSlot(int slot);
    
    /**
     * 取得背包所有物品的数量,包括叠加数量
     * @return
     *          所有物品的数量
     */
    int getAllItemCount();
    
    /**
     * 备份背包物品到内存
     * @param outCounts
     *          输出参数，物品位置到物品数量的映射即Map<key = 物品位置, value = 物品数量>
     * @return  
     *          Map<key = 物品位置, value = 物品T的引用>
     */
    Map<Integer, T> bak(Map<Integer, Integer> outCounts);
    
    /**
     * 将物品将背包类型过滤
     * @param items
     */
    void loadItemsCommit(List<T> items);
    
    /**
     * 保存到数据库 
     */
    void saveItemsIntoDB();
    
    /**
     * 取得背包的容量
     * @return
     */
    int getCapalility();
    
    /**
     * 取得背包类型
     * @return
     */
    BagType getType();
    
    /**
     * 提交更改
     */
    void commitChanges();
    
    /**
     * 开始更改
     */
    void beginChanges();
    
}
