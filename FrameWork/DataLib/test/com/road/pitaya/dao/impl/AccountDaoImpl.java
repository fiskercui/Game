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

import com.road.pitaya.dao.IAccountDao;
import com.road.pitaya.entity.AccountInfo;

import com.road.pitaya.database.BaseDao;
import com.road.pitaya.database.DBParameter;
import com.road.pitaya.database.pool.DBHelper;

public class AccountDaoImpl extends BaseDao<AccountInfo> implements IAccountDao
{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(AccountDaoImpl.class);

    /**
     * @param helper
     */
    public AccountDaoImpl(DBHelper helper)
    {
        super(helper);
    }

    /**
     * 添加实体
     * 
     * @param accountInfo
     * @return
     */
    public boolean add(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "insert into t_e_account(`id`,`name`,`password`) values (?,?,?);";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, accountInfo.getId()));
        para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getAccountName()));
        para.put(3, new DBParameter(Types.VARCHAR, accountInfo.getAccountPassword()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 更新实体
     * 
     * @param accountInfo
     * @return
     */
    public boolean update(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "update t_e_account set `name`=?,`password`=? where `id`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.VARCHAR, accountInfo.getAccountName()));
        para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getAccountPassword()));
        para.put(3, new DBParameter(Types.BIGINT, accountInfo.getId()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 删除
     * 
     * @param accountInfo
     * @return
     */
    public boolean delete(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "delete from t_e_account where `id`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, accountInfo.getId()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    /**
     * 添加或修改实体
     * 
     * @param accountInfo
     * @return
     */
    public boolean addOrUpdate(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "insert into t_e_account(`id`,`name`,`password`) values (?,?,?) on DUPLICATE KEY update `name`=?,`password`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, accountInfo.getId()));
        para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getAccountName()));
        para.put(3, new DBParameter(Types.VARCHAR, accountInfo.getAccountPassword()));
        para.put(4, new DBParameter(Types.VARCHAR, accountInfo.getAccountName()));
        para.put(5, new DBParameter(Types.VARCHAR, accountInfo.getAccountPassword()));
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
        String sql = "delete from t_e_account where `id`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, ids[0]));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }


    /**
     * 根据ID获取实体
     * 
     * @param ids
     * @return
     */
    public AccountInfo getByKey(Object... ids)
    {
        String sql = "select * from t_e_account where `id`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.BIGINT, ids[0]));
        ResultSet rs = getDBHelper().execQuery(sql, para);
        List<AccountInfo> entitis = rsToEntityList(rs);
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
    public List<AccountInfo> listAll()
    {
        String sql = "select * from t_e_account";
        ResultSet rs = getDBHelper().execQuery(sql);
        List<AccountInfo> entitis = rsToEntityList(rs);
        return entitis;
    }

    /**
     * 批量添加或修改实体
     * 
     * @param accountInfos
     * @return
     */
    public boolean addOrUpdateBatch(List<AccountInfo> accountInfos)
    {
        String sql = "insert into t_e_account(`id`,`name`,`password`) values (?,?,?) on DUPLICATE KEY update `name`=?,`password`=?;";
        PreparedStatement pstmt = getDBHelper().sqlBatch(sql);
        try
        {
            for (AccountInfo accountInfo : accountInfos)
            {
                Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
                para.put(1, new DBParameter(Types.BIGINT, accountInfo.getId()));
                para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getAccountName()));
                para.put(3, new DBParameter(Types.VARCHAR, accountInfo.getAccountPassword()));
                para.put(4, new DBParameter(Types.VARCHAR, accountInfo.getAccountName()));
                para.put(5, new DBParameter(Types.VARCHAR, accountInfo.getAccountPassword()));
                pstmt = getDBHelper().prepareCommand(pstmt,para);
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
     * @param accountInfos
     * @return
     */
    public boolean deleteBatch(List<AccountInfo> accountInfos)
    {
        String sql = "delete from t_e_account where `id`=?;";
        PreparedStatement pstmt = getDBHelper().sqlBatch(sql);
        try
        {
            for (AccountInfo accountInfo : accountInfos)
            {
                Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
                para.put(1, new DBParameter(Types.BIGINT, accountInfo.getId()));
                pstmt = getDBHelper().prepareCommand(pstmt,para);
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



    @Override
    protected AccountInfo rsToEntity(ResultSet rs) throws SQLException
    {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(rs.getLong("id"));
        accountInfo.setAccountName(rs.getString("name"));
        accountInfo.setAccountPassword(rs.getString("password"));
        return accountInfo;
    }

}
