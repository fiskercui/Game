/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.module;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.bll.PlayerBussiness;
import com.road.dota.module.inf.IBagModule;
import com.road.dota.object.bag.BaseItem;
import com.road.dota.object.bag.PlayerBag;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.type.BagType;
import com.road.dota.type.ModuleType;
import com.road.entity.bean.ItemBean;
import com.road.entity.info.ItemInfo;

/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 背包模块
 */
public class BagModule extends PlayerModule implements IBagModule
{
    /*日志记录 */
    private final static Logger logger = LoggerFactory.getLogger(BagModule.class.getName());
    
    /*主背包*/
    private PlayerBag mainBag;
    
    /**
     * @param player
     */
    public BagModule(IGamePlayer player)
    {
        super(player, ModuleType.BAG);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.road.dota.module.inf.IModule#init()
     */
    @Override
    public boolean init()
    {
        mainBag = new PlayerBag(player, true, 80, BagType.MAIN, 0, true);
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.dota.module.inf.IModule#loadFromDB()
     */
    @Override
    public boolean loadFromDB()
    {
        int userId = player.getPlayerInfo().getUserID();
        List<ItemInfo> itemInfos = PlayerBussiness.getItemInfos(userId);

        if (itemInfos != null && itemInfos.size() > 0)
        {
            List<BaseItem> baseItems = new ArrayList<BaseItem>();
            List<BaseItem> equips = new ArrayList<BaseItem>();

            for (ItemInfo itemInfo : itemInfos)
            {
                try
                {
                    ItemBean itemBean = getItemBean();
                    // 如果是装备
                    if (itemBean == null)
                    {
                        logger.error("bean is not exist: "
                                + itemBean.getBeanId());
                        continue;
                    }
                    BaseItem baseItem = new BaseItem(itemBean, itemInfo);
                    baseItems.add(baseItem);
                }
                catch (Exception e)
                {
                    logger.error("BagModule:loadFromDB find null itemBean", e);
                    continue;
                }
            }

            // 过滤到各种背包
            mainBag.loadItemsCommit(baseItems);
        }
        return true;
    }

    /**
     * @return
     */
    private ItemBean getItemBean()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.dota.module.inf.IModule#saveIntoDB()
     */
    @Override
    public boolean saveIntoDB()
    {
        try
        {
            mainBag.saveItemsIntoDB();
        }
        catch (Exception e)
        {
            logger.error("BagModule saveIntoDB error:", e);
            return false;
        }

        return true;
    }

    /* (non-Javadoc)
     * @see com.road.dota.module.inf.IBagModule#moveItem(short, short, int, boolean)
     */
    @Override
    public void moveItem(short fromPlace, short toPlace, int count,
            boolean isCanReplace)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.road.dota.module.inf.IModule#getType()
     */
    @Override
    public ModuleType getType()
    {
        return ModuleType.BAG;
    }
}
