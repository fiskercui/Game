package com.road.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.road.dao.IPlayerInfoDao;
import com.road.entity.SimpleUser;
import com.road.entity.info.PlayerInfo;
import com.road.pitaya.database.BaseDao;
import com.road.pitaya.database.DBParamWrapper;
import com.road.pitaya.database.DBParameter;
import com.road.pitaya.database.pool.DBHelper;

/**
 * 
 * @author volcano
 * @date 2013-5-28
 * @version
 * 
 */
public class PlayerInfoDaoImpl extends BaseDao<PlayerInfo> implements
		IPlayerInfoDao {

	public PlayerInfoDaoImpl(DBHelper helper) {
		super(helper);
	}

	@Override
	protected PlayerInfo rsToEntity(ResultSet rs) throws SQLException {
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setUserID(rs.getInt("UserID"));
		playerInfo.setNickname(rs.getString("Nickname"));
		playerInfo.setSex(rs.getByte("Sex"));
		playerInfo.setCareer(rs.getShort("Career"));
		playerInfo.setLevel(rs.getShort("Level"));
		playerInfo.setGold(rs.getLong("Gold"));
		playerInfo.setExp(rs.getInt("Exp"));
		playerInfo.setMoney(rs.getInt("Money"));
		playerInfo.setGuildID(rs.getInt("GuildID"));
		return null;
	}

	@Override
	public boolean add(PlayerInfo info) {
		boolean result = false;
		String sql = "INSERT INTO t_u_player(`UserID`,`Nickname`,`Sex`,`Career`,`Level`,`Gold`,`Exp`, `Money`, `GuildID`)  VALUES(?,?, ?, ?, ?, ?, ?, ?, ?);";
		DBParamWrapper params = new DBParamWrapper();
		params.put(Types.INTEGER, info.getUserID());
		params.put(Types.VARCHAR, info.getNickname());
		params.put(Types.TINYINT, info.getSex());
		params.put(Types.TINYINT, info.getCareer());
		params.put(Types.SMALLINT, info.getLevel());
		params.put(Types.BIGINT, info.getGold());
		params.put(Types.INTEGER, info.getExp());
		params.put(Types.INTEGER, info.getMoney());
		params.put(Types.INTEGER, info.getGuildID());
		result = getDBHelper().execNoneQuery(sql, params.getParams()) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean update(PlayerInfo info) {
		boolean result = false;
		String sql = "update t_u_player set Nickname = ?,Sex = ?,Career = ?, Level = ?,Gold = ?, Exp = ?, Money = ? ,GuildID = ? where UserID = ?;";
		DBParamWrapper params = new DBParamWrapper();
		params.put(Types.VARCHAR, info.getNickname());
		params.put(Types.TINYINT, info.getSex());
		params.put(Types.TINYINT, info.getCareer());
		params.put(Types.SMALLINT, info.getLevel());
		params.put(Types.BIGINT, info.getGold());
		params.put(Types.INTEGER, info.getExp());
		params.put(Types.INTEGER, info.getMoney());
		params.put(Types.INTEGER, info.getGuildID());
		params.put(Types.INTEGER, info.getUserID());
		result = getDBHelper().execNoneQuery(sql, params.getParams()) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean delete(PlayerInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addOrUpdate(PlayerInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByKey(Object... ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlayerInfo getByKey(Object... ids) {
		String sql = "select * from t_u_player where userID=?;";
		DBParamWrapper params = new DBParamWrapper();
		params.put(Types.INTEGER, ids[0]);
		ResultSet rs = getDBHelper().execQuery(sql, params.getParams());
        List<PlayerInfo> entitis = rsToEntityList(rs);
        if (entitis.size() > 0)
        {
            return entitis.get(0);
        }
        return null;
	}

	@Override
	public List<PlayerInfo> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrUpdateBatch(List<PlayerInfo> t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBatch(List<PlayerInfo> t) {
		// TODO Auto-generated method stub
		return false;
	}
    @Override
    public List<SimpleUser> getPlayerInfos(String username, String site)
    {
        String sql = "SELECT A.UserID,A.Username,A.isActived,A.Site,A.IsOnline,B.Nickname,B.`Level`,B.Sex from  t_u_account A LEFT OUTER JOIN t_u_player B ON A.UserID = B.UserID  WHERE Username =? AND Site = ?;";
        Map<Integer,DBParameter> params = new HashMap<Integer, DBParameter>();
        params.put(1,new DBParameter(Types.VARCHAR, username));
        params.put(2,new DBParameter(Types.VARCHAR, site));
        ResultSet rs =  getDBHelper().execQuery(sql, params);
        List<SimpleUser> users = new ArrayList<SimpleUser>(); 
        try
        {
            while(rs.next())
            {
                SimpleUser user = new SimpleUser();
                user.setUserId(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setActive(rs.getBoolean("IsActived"));
                user.setGrades(rs.getInt("Level"));
                user.setSexs(rs.getShort("Sex"));
                user.setIsOnline(rs.getBoolean("IsOnline"));
                user.setSite(rs.getString("Site"));
                
                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }
}
