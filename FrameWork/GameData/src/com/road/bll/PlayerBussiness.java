package com.road.bll;

import java.util.List;

import com.road.entity.SimpleUser;
import com.road.entity.info.ItemInfo;
import com.road.entity.info.PlayerInfo;


/**
 * 
 * @author volcano
 * @date 2013-5-28
 * @version 
 *
 */
public class PlayerBussiness {

	/**
	 * 获取玩家基础信息
	 * @param userID
	 * @return
	 */
	public static PlayerInfo getPlayerInfo(int userID)
	{
		return DaoManager.getPlayerInfoDao().getByKey(userID);
	}
	
	/**
	 * 添加一个玩家信息到DB
	 * @param playerInfo
	 * @return
	 */
	public static boolean addPlayerInfo(PlayerInfo playerInfo)
	{
		if (playerInfo == null || playerInfo.getUserID() == 0)
		{
			return false;
		}
		return DaoManager.getPlayerInfoDao().add(playerInfo);
	}
	
	/**
	 * 更新玩家基本信息
	 * @param playerInfo
	 * @return
	 */
	public static boolean updatePlayerInfo(PlayerInfo playerInfo)
	{
		return DaoManager.getPlayerInfoDao().update(playerInfo);
	}
	
	   /**
     * 获得该用户的所有角色。（该区）
     * @param userName
     * @param site
     * @return
     */
    public static List<SimpleUser> getPlayerInfos(String userName,String site)
    {
        return DaoManager.getPlayerInfoDao().getPlayerInfos(userName, site);
    }

    /**
     * @param userID
     * @param tempInfos
     */
    public static void getUpdateItem(int userID, List<ItemInfo> tempInfos)
    {
        // TODO Auto-generated method stub
        
    }

    /**
     * @param itemInfo
     */
    public static void updateGoods(ItemInfo itemInfo)
    {
        // TODO Auto-generated method stub
        
    }

    /**
     * @param itemInfo
     * @return
     */
    public static boolean addItems(ItemInfo itemInfo)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param userId
     * @return
     */
    public static List<ItemInfo> getItemInfos(int userId)
    {
        // TODO Auto-generated method stub
        return null;
    }
	 
}
