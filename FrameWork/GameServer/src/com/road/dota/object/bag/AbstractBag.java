/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.object.bag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.type.BagType;
import com.road.dota.type.ItemRemoveType;

/**
 * @author cookie.hu
 * @date 2013-5-24
 * @version 
 * 抽象背包
 */
public abstract class AbstractBag<T extends IBagItem> implements IBag<T>
{
    //日志记录器
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBag.class.getName());
    
    //容量
    protected volatile int capalility;
    
    //类型
    protected BagType type;
    
    //能够添加进来的最小位置
    protected int beginSlot;
    
    //物品数组
    protected T[] items;
    
    //能否自动叠加
    protected boolean autoStack;
    
    //有更新的位置
    protected List<Integer> changedPlaces;

    //更新的位置的数量
    protected Integer changeCount;
    
    //物品用的读写锁
    protected ReadWriteLock itemLock;
    
    //构造函数
    public AbstractBag(int capability, BagType type, int beginSlot,
            Boolean autoStack)
    {
        this.capalility = capability;
        this.type = type;
        this.beginSlot = beginSlot;
        this.autoStack = autoStack;
        this.itemLock = new ReentrantReadWriteLock();
        this.changedPlaces = new ArrayList<Integer>();
        this.changeCount = 0;
    }
    
    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#initItems(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initItems(Class<T> class1)
    {
        this.items = (T[]) Array.newInstance(class1, capalility);
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#onPlaceChanged(int)
     */
    @Override
    public void onPlaceChanged(int place)
    {
        synchronized (changedPlaces)
        {
            if (changedPlaces.contains(place) == false)
                changedPlaces.add(place);
        }
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#updateChangedPlaces()
     */
    @Override
    public void updateChangedPlaces()
    {
        synchronized (changedPlaces)
        {
            changedPlaces.clear();
        }
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#addCountToStack(java.lang.Object, short, boolean)
     */
    @Override
    public boolean addCountToStackUnCommit(T item, short count)
    {
        if (item == null)
            return false;
        
        if (count <= 0 || item.getBagType() != type)
            return false;

        if (item.getCount() + count > item.getMaxStack())
            return false;

        item.setCount((short) (count + item.getCount()));

        onPlaceChanged(item.getPlace());
        
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#addItem(java.lang.Object, int, boolean)
     */
    @Override
    public boolean addItem(T item, int place, boolean shouldCommit)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#addItemReplace(java.lang.Object, int, boolean)
     */
    @Override
    public T addItemReplace(T item, int place, boolean shouldCommit)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#addItemsAndCommit(java.lang.Object, int, int)
     */
    @Override
    public boolean addItemsAndCommit(T cloneItem, int count, int place)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#stackAllItemsUnCommit()
     */
    @Override
    public boolean stackAllItemsUnCommit()
    {
        itemLock.writeLock().lock();
        try
        {
            for (int i = beginSlot; i <= capalility - 1; i++)
            {
                if (items[i] != null)
                {
                    for (int j = i; j <= capalility - 1; j++)
                    {
                        if (items[j] != null
                                && items[i] != null
                                && items[i] != items[j]
                                && items[j].canStackedTo(items[i])
                                && items[i].getCount()
                                        + items[j].getCount() <= items[j].getMaxStack())
                        {
                            items[i].setCount((short) (items[i].getCount() + items[j].getCount()));
                            removeItem(items[j], ItemRemoveType.STACK);
                            updateItem(items[i]);
                        }

                    }
                    for (int place = beginSlot; place < items[i].getPlace(); place++)
                    {
                        if (items[place] == null
                                && items[place] != items[i])
                        {
                            exchangeItemsUnCommit(i, place);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("AbstractBag:stackAllItems", e);
            return false;
        }
        finally
        {
            itemLock.writeLock().unlock();
        }
        return true;
    }

    /**
     * 更新物品
     * @param t
     */
    protected void updateItem(T item)
    {
        if (item.getBagType() == type)
        {
            if (item.getCount() <= 0)
            {
                removeItem(item, ItemRemoveType.OTHER);
            }
            else
            {
                onPlaceChanged(item.getPlace());
            }
        }
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#removeItem(java.lang.Object, com.road.dota.type.ItemRemoveType)
     */
    @Override
    public boolean removeItem(T items, ItemRemoveType removeType)
    {
        if (items == null)
            return false;
        
        int place = items.getPlace();
        
        if (place != -1 && items.getBagType().getValue()==this.type.getValue())
        {
            onPlaceChanged(place);
            items.setPlace(-1);
            items.setBagType(BagType.NULL);
            this.items[place] = null;
        }

        return place != -1;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#exchangeItemsUnCommit(int, int)
     */
    @Override
    public boolean exchangeItemsUnCommit(int fromSlot, int toSlot)
    {
        if (fromSlot == toSlot)
            return false;
        
        T fromItem = items[toSlot];
        T toItem = items[fromSlot];

        items[fromSlot] = fromItem;
        items[toSlot] = toItem;

        if (fromItem != null)
            fromItem.setPlace(fromSlot);

        if (toItem != null)
            toItem.setPlace(toSlot);

        return true;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#stackItemsUnCommit(int, int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean stackItemsUnCommit(int fromSlot, int toSlot, int itemCount)
    {
        if (fromSlot == toSlot)
            return false;
        T fromItem = items[fromSlot];
        T toItem = items[toSlot];
        if (fromItem == null)
            return false;
        if (itemCount < 0)
            return false;

        if (itemCount == 0)
        {
            if (fromItem.getCount() > 0)
                itemCount = fromItem.getCount();
            else
                itemCount = 1;
        }

        if (fromItem.getCount() < itemCount)
            return false;

        //如果能要叠加的位置有物品
        if (toItem != null
                && fromItem.canStackedTo(toItem))
        {
            if (itemCount + toItem.getCount() > toItem.getMaxStack())
            {
                fromItem.setCount((short) (fromItem.getCount()
                                                        - toItem.getMaxStack() + toItem.getCount()));
                toItem.setCount((short) (toItem.getMaxStack()));
            }
            else
            {
                toItem.setCount((short) (toItem.getCount() + itemCount));
                if (itemCount == fromItem.getCount())
                {
                    removeItem(fromItem, ItemRemoveType.STACK);
                }
                else
                {
                    fromItem.setCount((short) (fromItem.getCount() - itemCount));
                }
            }
            return true;
        }
        else if (toItem == null)
        {
            T newItem = (T) fromItem.cloneItem();
            newItem.setCount((short) itemCount);

            if (addItem(newItem, toSlot, false))
            {
                fromItem.setCount((short) (fromItem.getCount() - itemCount));
                updateItem(fromItem);
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#moveItemAndCommit(int, int, int)
     */
    @Override
    public boolean moveItemAndCommit(int fromSlot, int toSlot, int count)
    {
        if (fromSlot < 0 || toSlot < 0 || fromSlot >= capalility
                || toSlot >= capalility || count < 0)
            return false;
        
        if (fromSlot == toSlot)
            return false;

        boolean result = false;
        itemLock.writeLock().lock();
        if (!stackItemsUnCommit(fromSlot, toSlot, count))
        {
            result = exchangeItemsUnCommit(fromSlot, toSlot);
        }
        else
        {
            result = true;
        }
        itemLock.writeLock().unlock();

        if (result)
        {
            beginChanges();
            try
            {
                onPlaceChanged(fromSlot);
                onPlaceChanged(toSlot);
            }
            finally
            {
                commitChanges();
            }
        }

        return result;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#removeCountFromStackUnCommit(java.lang.Object, int, com.road.dota.type.ItemRemoveType)
     */
    @Override
    public boolean removeCountFromStackUnCommit(T item, int count,
            ItemRemoveType itemRemoveType)
    {
        if (item == null)
            return false;

        if (count <= 0 || item.getBagType() != type)
            return false;
        
        if (item.getCount() < count)
            return false;
        
        if (item.getCount() == count && removeItem(item, itemRemoveType))
            item.setCount((short) 0);
        else
        {
            item.setCount((short) (item.getCount() - count));
            onPlaceChanged(item.getPlace());
        }
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#clearBag(int, int, boolean)
     */
    @Override
    public void clearBag(int minSlot, int maxSlot, boolean shouldCommit)
    {
        if (shouldCommit)
        {
            beginChanges();
        }
        try
        {
            itemLock.writeLock().lock();
            {
                for (int i = minSlot; i <= maxSlot; i++)
                {
                    items[i] = null;
                    onPlaceChanged(i);
                }
            }
            itemLock.writeLock().unlock();
        }
        catch (Exception e)
        {
            LOGGER.error("clear bag error", e);
        }
        finally
        {
            itemLock.writeLock().unlock();
            if (shouldCommit)
            {
                commitChanges();
            }
        }
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#clearAndUnChange(int, int)
     */
    @Override
    public void clearAndUnChange(int minSlot, int maxSlot)
    {
        itemLock.writeLock().lock();
        {
            for (int i = minSlot; i <= maxSlot; i++)
            {
                items[i] = null;
            }
        }
        itemLock.writeLock().unlock();
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getEmptyCount()
     */
    @Override
    public int getEmptyCount()
    {
        if (beginSlot < 0 || beginSlot > capalility - 1)
            return 0;

        int count = 0;
        itemLock.readLock().lock();
        for (int i = beginSlot; i < capalility; i++)
        {
            if (items[i] == null)
            {
                count++;
            }
        }
        itemLock.readLock().unlock();
        return count;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getItemAt(int)
     */
    @Override
    public T getItemAt(int slot)
    {
        if (slot < 0 || slot >= capalility)
            return null;
        
        T item = null;
        itemLock.readLock().lock();
        item = items[slot];
        itemLock.readLock().unlock();
        
        return item;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getItems()
     */
    @Override
    public List<T> getItems()
    {
        return getItems(0, capalility - 1);
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getItems(int, int)
     */
    @Override
    public List<T> getItems(int minSlot, int maxSlot)
    {
        List<T> list = new LinkedList<T>();

        itemLock.readLock().lock();
        {
            for (int i = minSlot; i <= maxSlot; i++)
            {
                if (items[i] != null)
                {
                    list.add(items[i]);
                }
            }
        }
        itemLock.readLock().unlock();
        return list;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#inBagSlot(int)
     */
    @Override
    public boolean inBagSlot(int slot)
    {
        return slot >= beginSlot && slot < capalility;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getAllItemCount()
     */
    @Override
    public int getAllItemCount()
    {
        int count = 0;
        itemLock.readLock().lock();
        for (int i = beginSlot; i < capalility; i++)
        {
            if (items[i] != null)
            {
                count += items[i].getCount();
            }
        }
        itemLock.readLock().unlock();
        return count;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#bak(java.util.Map)
     */
    @Override
    public Map<Integer, T> bak(Map<Integer, Integer> outCounts)
    {
        Map<Integer, T> dics = new HashMap<Integer, T>();
        itemLock.readLock().lock();
        for (int i = 0; i < items.length; i++)
        {
            if (items[i] != null)
            {
                dics.put(i, items[i]);
                outCounts.put(i, (int) (items[i].getCount()));
            }
        }
        itemLock.readLock().unlock();
        return dics;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#loadItemsCommit(java.util.List)
     */
    @Override
    public abstract void loadItemsCommit(List<T> items);
    
    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#saveItemsIntoDB()
     */
    @Override
    public abstract void saveItemsIntoDB();

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getCapalility()
     */
    @Override
    public int getCapalility()
    {
        return capalility;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#getType()
     */
    @Override
    public BagType getType()
    {
        return type;
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#commitChanges()
     */
    @Override
    public void commitChanges()
    {
        synchronized (changeCount)
        {
            changeCount--;
            if (changeCount < 0)
            {
                if (LOGGER.isInfoEnabled())
                    LOGGER.error("Inventory changes counter is bellow zero (forgot to use BeginChanges?)!",
                                 new Throwable());

                changeCount = 0;
            }
            if (changeCount == 0)
            {
                synchronized (changedPlaces)
                {
                    if(changedPlaces.size() > 0)
                    {
                        updateChangedPlaces();
                    }
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see com.road.dota.object.bag.IBag#beginChanges()
     */
    @Override
    public void beginChanges()
    {
        synchronized (changeCount)
        {
            ++changeCount;
        }
    }

}
