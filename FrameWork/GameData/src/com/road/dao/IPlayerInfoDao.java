package com.road.dao;

import java.util.List;

import com.road.entity.info.PlayerInfo;
import com.road.entity.SimpleUser;
import com.road.pitaya.database.IBaseDao;

/**
 * 
 * @author volcano
 * @date 2013-5-27
 * @version
 * 
 */
public interface IPlayerInfoDao extends IBaseDao<PlayerInfo>
{
    List<SimpleUser> getPlayerInfos(String username,String site);
}
