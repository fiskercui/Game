package com.road.pitaya.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.dao.IPlayerDao;
import com.road.pitaya.database.BaseDao;
import com.road.pitaya.database.DBParameter;
import com.road.pitaya.database.pool.DBHelper;
import com.road.pitaya.entity.Player;

public class PlayerDaoImpl extends BaseDao<Player> implements IPlayerDao
{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PlayerDaoImpl.class);

    public PlayerDaoImpl(DBHelper helper)
    {
        super(helper);
    }

    /**
     * 添加实体
     * 
     * @param t
     * @return
     */
    public boolean add(Player player)
    {
        boolean result = false;
        String sql = "insert into player(`id`,`name`,`nick`) values (?,?,?);";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, player.getId()));
        para.put(2, new DBParameter(Types.VARCHAR, player.getName()));
        para.put(3, new DBParameter(Types.VARCHAR, player.getNick()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 更新实体
     * 
     * @param t
     * @return
     */
    public boolean update(Player player)
    {
        boolean result = false;
        String sql = "update player set `nick`=? where `id`=? and `name`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.VARCHAR, player.getNick()));
        para.put(2, new DBParameter(Types.BIGINT, player.getId()));
        para.put(3, new DBParameter(Types.VARCHAR, player.getName()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 删除
     * 
     * @param player
     * @return
     */
    public boolean delete(Player player)
    {
        boolean result = false;
        String sql = "delete from player where `id`=? and `name`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, player.getId()));
        para.put(2, new DBParameter(Types.VARCHAR, player.getName()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 添加或修改实体
     * 
     * @param player
     * @return
     */
    public boolean addOrUpdate(Player player)
    {
        boolean result = false;
        String sql = "insert into player(`id`,`name`,`nick`) values (?,?,?) on DUPLICATE KEY update `nick`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, player.getId()));
        para.put(2, new DBParameter(Types.VARCHAR, player.getName()));
        para.put(3, new DBParameter(Types.VARCHAR, player.getNick()));
        para.put(4, new DBParameter(Types.VARCHAR, player.getNick()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 根据ID删除实体
     * 
     * @param ids
     * @return
     */
    public boolean deleteByKey(Object... ids)
    {
        boolean result = false;
        String sql = "delete from player where `id`=? and `name`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, ids[0]));
        para.put(2, new DBParameter(Types.VARCHAR, ids[1]));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 根据ID获取实体
     * 
     * @param ids
     * @return
     */
    public Player getByKey(Object... ids)
    {
        String sql = "select * from player where `id`=? and `name`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, ids[0]));
        para.put(2, new DBParameter(Types.VARCHAR, ids[1]));
        ResultSet rs = getDBHelper().execQuery(sql, para);
        List<Player> entitis = rsToEntityList(rs);
        if (entitis.size() > 0)
        {
            return entitis.get(0);
        }
        return null;
    }

    /**
     * 获取所有实体
     * 
     * @return
     */
    public List<Player> listAll()
    {
        String sql = "select * from player";
        ResultSet rs = getDBHelper().execQuery(sql);
        List<Player> entitis = rsToEntityList(rs);
        return entitis;
    }

    /**
     * 批量添加或修改实体
     * 
     * @param t
     * @return
     */
    public boolean addOrUpdateBatch(List<Player> t)
    {
        String sql = "insert into player(`id`,`name`,`nick`) values (?,?,?) on DUPLICATE KEY update `nick`=?;";
        PreparedStatement pstmt = getDBHelper().sqlBatch(sql);
        try
        {
            for (Player temp : t)
            {
                Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
                para.put(1, new DBParameter(Types.BIGINT, temp.getId()));
                para.put(2, new DBParameter(Types.VARCHAR, temp.getName()));
                para.put(3, new DBParameter(Types.VARCHAR, temp.getNick()));
                para.put(4, new DBParameter(Types.VARCHAR, temp.getNick()));
                pstmt = getDBHelper().prepareCommand(pstmt, para);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            return true;
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            getDBHelper().closeConn(pstmt, null);
        }
        return false;
    }

    /**
     * 批量删除实体
     * 
     * @param t
     * @return
     */
    public boolean deleteBatch(List<Player> t)
    {
        String sql = "delete from player where `id`=? and `name`=?;";
        PreparedStatement pstmt = getDBHelper().sqlBatch(sql);
        try
        {
            for (Player temp : t)
            {
                Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
                para.put(1, new DBParameter(Types.BIGINT, temp.getId()));
                para.put(2, new DBParameter(Types.VARCHAR, temp.getName()));
                pstmt = getDBHelper().prepareCommand(pstmt, para);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            return true;
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            getDBHelper().closeConn(pstmt, null);
        }
        return false;
    }

    protected Player rsToEntity(ResultSet rs) throws SQLException
    {
        Player player = new Player();
        player.setId(rs.getLong("id"));
        player.setName(rs.getString("name"));
        player.setNick(rs.getString("nick"));
        return player;
    }

}
