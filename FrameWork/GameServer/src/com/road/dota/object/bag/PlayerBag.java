/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.dota.object.bag;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.road.bll.PlayerBussiness;
import com.road.dota.module.inf.IMessageModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.type.BagType;
import com.road.dota.type.ItemRemoveType;
import com.road.dota.type.ModuleType;
import com.road.entity.DataObject.DataOption;
import com.road.entity.info.ItemInfo;
import com.road.util.wrapper.WrapInteger;

/**
 * @author : cookie
 * @date : 2012-12-17
 * @version 人物背包
 */
public class PlayerBag extends AbstractBag<BaseItem>
{
    // 背包所属主人
    protected IGamePlayer player;

    // 是否要存DB
    protected boolean saveToDb;

    // 待移除的物品列表
    private List<BaseItem> removedList = new LinkedList<BaseItem>();

    /**
     * 构造背包
     * 
     * @param player
     *            宿主
     * @param saveToDb
     *            是否存DB
     * @param capability
     *            容量
     * @param type
     *            类型
     * @param beginSlot
     *            起始位置
     * @param autoStack
     *            是否自动叠加
     */
    public PlayerBag(IGamePlayer player, boolean saveToDb, int capability,
            BagType type, int beginSlot, Boolean autoStack)
    {
        super(capability, type, beginSlot, autoStack);
        this.player = player;
        this.saveToDb = saveToDb;
    }

    public boolean addItemAndCommit(BaseItem item, int place)
    {
//        try
//        {
//            // 设置延时提交数据，新物品需要生成itemId后再下发数据
//            beginChanges();
//
//            if (super.addItem(item, place, false))
//            {
//                item.getItemInfo().setExist(true);
//                item.getItemInfo()
//                        .setUserId(player.getPlayerInfo().getUserID());
//
//                // 如果没有物品ID，即认定为新增物品
//                if (item.getItemInfo().getItemId() <= 0)
//                {
//                    // 新增物品，自动生成物品id并在成功添加进背包后及时保存
//                    item.getItemInfo().setItemId(IDBussiness.getMaxItemId());
//
//                    if (false == PlayerBussiness.addItems(item.getItemInfo()))
//                    {
//                        LOGGER.error(String
//                                .format("PlayerInventory-AddItemTo-addGoods Error:itemId(%d),place(%d),Stack:%s",
//                                        item.getItemInfo().getItemId(), place,
//                                        StackMessagePrint.captureStackTrace()));
//                        return false;
//                    }
//                }
//
//                return true;
//            }
//        }
//        catch (Exception e)
//        {
//            LOGGER.error("addItemTo error", e);
//        }
//        finally
//        {
//            commitChanges();
//        }

        return false;
    }

    /**
     * 返回背包主人
     * 
     * @return
     */
    public IGamePlayer getPlayer()
    {
        return player;
    }

    @Override
    public void updateChangedPlaces()
    {
        if (changedPlaces != null)
        {
            ((IMessageModule)(player.getModule(ModuleType.MESSAGE))).sendUpdateBag(this,
                    changedPlaces);
        }
        super.updateChangedPlaces();
    }

    /**
     * 加载本背包物品，即从所有物品中过滤出本背包里面的物品
     * 
     * @param baseItems
     */
    @Override
    public void loadItemsCommit(List<BaseItem> baseItems)
    {
        if (baseItems == null || baseItems.size() == 0)
            return;

        boolean check = true;
        beginChanges();
        try
        {
            for (BaseItem item : baseItems)
            {
                check = checkItemPlace(item);
                if (item.getBagType() == type
                        && item.getCount() <= item.getMaxStack()
                        && item.getCount() > 0 && check)
                {
                    addItem(item, item.getPlace(), false);
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("LoadItems error", e);
        }
        finally
        {
            commitChanges();
        }
    }

    /**
     * 校验物品的位置
     * 
     * @param item
     * @return
     */
    protected boolean checkItemPlace(BaseItem item)
    {
        return true;
    }

    @Override
    public boolean removeItem(BaseItem item, ItemRemoveType itemRemoveType)
    {
        if (super.removeItem(item, itemRemoveType))
        {
            item.getItemInfo().setExist(false);
            item.getItemInfo().setRemoveType(itemRemoveType.getValue());
            item.getItemInfo().setRemoveDate(new Date());

            if (saveToDb)
            {
                synchronized (removedList)
                {
                    removedList.add(item);
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 根据beanId查找指定类型的物品
     * 
     * @param type
     * @param beanId
     * @return
     */
    public int getItemCountByBeanId(ItemBeanType type, int beanId)
    {
        int count = 0;
        for (BaseItem t : items)
        {
            if (t != null && t.getItemBean().getBeanType() == type.getValue()
                    && t.getItemBean().getBeanId() == beanId)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * 拿出一件物品，其实是换位置或叠加
     * 
     * @param item
     * @return
     */
    public boolean takeOutItemUnCommit(BaseItem item)
    {
        if (super.removeItem(item, ItemRemoveType.STACK))
        {
            if (saveToDb)
            {
                PlayerBussiness.updateGoods(item.getItemInfo());
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 叠加物品
     * 
     * @param item
     * @return
     */
    public boolean stackItemToAnother(BaseItem item)
    {
        itemLock.writeLock().lock();
        {
            ItemInfo itemInfo = item.getItemInfo();

            for (int i = capalility - 1; i >= 0; i--)
            {
                if (item != null
                        && items[i] != null
                        && items[i] != item
                        && item.canStackedTo(items[i])
                        && items[i].getCount() + item.getCount() <= item
                                .getMaxStack())
                {
                    items[i].setCount((short) (items[i].getCount() + itemInfo
                            .getCount()));
                    itemInfo.setExist(false);
                    itemInfo.setRemoveType(ItemRemoveType.STACK.getValue());
                    itemInfo.setRemoveDate(new Date());

                    if (itemInfo.getPlace() < 1 && itemInfo.getUserId() == 0)
                    {
                        onPlayerObtainitem(item);
                    }

                    updateItem(items[i]);
                    return true;
                }
            }
        }
        itemLock.writeLock().unlock();
        return false;
    }

    /**
     * @param item
     */
    private void onPlayerObtainitem(BaseItem item)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.bag.AbstractBag#saveIntoDB()
     */
    @Override
    public void saveItemsIntoDB()
    {
        if (saveToDb)
        {
            List<ItemInfo> tempInfos = new ArrayList<ItemInfo>();
            itemLock.readLock().lock();
            {
                for (int i = 0; i < items.length; i++)
                {
                    BaseItem item = items[i];
                    if (item != null
                            && (item.getItemInfo().getOp() == DataOption.UPDATE || item
                                    .getItemInfo().getOp() == DataOption.INSERT))
                    {
                        if (item.getItemInfo().getItemId() > 0)
                        {
                            item.getItemInfo().setOp(DataOption.UPDATE);
                        }
                        else
                        {
                            item.getItemInfo().setOp(DataOption.INSERT);
                        }
                        tempInfos.add(item.getItemInfo());
                    }
                }

                PlayerBussiness.getUpdateItem(player.getPlayerInfo()
                        .getUserID(), tempInfos);
            }
            itemLock.readLock().unlock();
            synchronized (removedList)
            {
                tempInfos.clear();
                for (BaseItem item : removedList)
                {
                    if (item.getItemInfo().getItemId() > 0
                            && item.getItemInfo().getOp() != DataOption.NONE)
                    {
                        item.getItemInfo().setOp(DataOption.UPDATE);
                    }
                    else
                    {
                        item.getItemInfo().setOp(DataOption.INSERT);
                    }
                    tempInfos.add(item.getItemInfo());
                }

                PlayerBussiness.getUpdateItem(player.getPlayerInfo()
                        .getUserID(), tempInfos);
                removedList.clear();
            }
        }
    }

    /**
     * 得到当前
     * 
     * @param itemBeanId
     * @return
     */
    public List<BaseItem> getItemsPos(int itemBeanId, WrapInteger count)
    {
        List<BaseItem> itemList = null;
        count.setParam(0);
        itemLock.readLock().lock();
        for (BaseItem baseItem : items)
        {
            if (baseItem != null
                    && baseItem.getItemInfo().getTemplateId() == itemBeanId
                    && baseItem.getItemInfo().isExist())
            {
                if (itemList == null)
                {
                    itemList = new ArrayList<BaseItem>();
                }
                count.setParam(count.getParam() + baseItem.getCount());
                itemList.add(baseItem);
            }
        }
        itemLock.readLock().unlock();
        return itemList;
    }

    /**
     * 得到物品
     * 
     * @param itemBeanId
     * @return
     */
    public BaseItem getBaseItem(int itemBeanId)
    {
        BaseItem itemInfo = null;
        for (BaseItem baseItem : items)
        {
            if (baseItem.getItemInfo().getTemplateId() == itemBeanId
                    && baseItem.getItemInfo().isExist())
            {
                itemInfo = baseItem;
                break;
            }
        }
        return itemInfo;
    }

}
