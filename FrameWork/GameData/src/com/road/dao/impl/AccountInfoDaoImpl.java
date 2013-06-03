/**
 * Date: 2013-5-30
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.database.BaseDao;
import com.road.pitaya.database.DBParamWrapper;
import com.road.pitaya.database.DBParameter;
import com.road.pitaya.database.pool.DBHelper;

import com.road.dao.IAccountInfoDao;
import com.road.entity.AccountInfo;

public class AccountInfoDaoImpl extends BaseDao<AccountInfo> implements IAccountInfoDao
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoDaoImpl.class);

    public AccountInfoDaoImpl(DBHelper helper)
    {
        super(helper);
    }

    @Override
    public boolean add(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "insert into t_u_account(`UserID`,`Username`,`Site`,`IsOnline`,`CreateDate`,`ForbidDate`,`LastPayDate`,`LastLoginIP`,`LastLoginDate`,`LastLogin2Date`,`LastLogin3Date`,`LastLogoutDate`) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.INTEGER, accountInfo.getUserId()));
        para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getUsername()));
        para.put(3, new DBParameter(Types.VARCHAR, accountInfo.getSite()));
        para.put(4, new DBParameter(Types.TINYINT, accountInfo.getIsOnline()));
        para.put(5, new DBParameter(Types.TIMESTAMP, accountInfo.getCreateDate()));
        para.put(6, new DBParameter(Types.TIMESTAMP, accountInfo.getForbidDate()));
        para.put(7, new DBParameter(Types.TIMESTAMP, accountInfo.getLastPayDate()));
        para.put(8, new DBParameter(Types.VARCHAR, accountInfo.getLastLoginIP()));
        para.put(9, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLoginDate()));
        para.put(10, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin2Date()));
        para.put(11, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin3Date()));
        para.put(12, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogoutDate()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean update(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "update t_u_account set `Username`=?,`Site`=?,`IsOnline`=?,`CreateDate`=?,`ForbidDate`=?,`LastPayDate`=?,`LastLoginIP`=?,`LastLoginDate`=?,`LastLogin2Date`=?,`LastLogin3Date`=?,`LastLogoutDate`=? where `UserID`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.VARCHAR, accountInfo.getUsername()));
        para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getSite()));
        para.put(3, new DBParameter(Types.TINYINT, accountInfo.getIsOnline()));
        para.put(4, new DBParameter(Types.TIMESTAMP, accountInfo.getCreateDate()));
        para.put(5, new DBParameter(Types.TIMESTAMP, accountInfo.getForbidDate()));
        para.put(6, new DBParameter(Types.TIMESTAMP, accountInfo.getLastPayDate()));
        para.put(7, new DBParameter(Types.VARCHAR, accountInfo.getLastLoginIP()));
        para.put(8, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLoginDate()));
        para.put(9, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin2Date()));
        para.put(10, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin3Date()));
        para.put(11, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogoutDate()));
        para.put(12, new DBParameter(Types.INTEGER, accountInfo.getUserId()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean delete(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "delete from t_u_account where `UserID`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.INTEGER, accountInfo.getUserId()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean addOrUpdate(AccountInfo accountInfo)
    {
        boolean result = false;
        String sql = "insert into t_u_account(`UserID`,`Username`,`Site`,`IsOnline`,`CreateDate`,`ForbidDate`,`LastPayDate`,`LastLoginIP`,`LastLoginDate`,`LastLogin2Date`,`LastLogin3Date`,`LastLogoutDate`) values(?,?,?,?,?,?,?,?,?,?,?,?) on DUPLICATE KEY update `Username`=?,`Site`=?,`IsOnline`=?,`CreateDate`=?,`ForbidDate`=?,`LastPayDate`=?,`LastLoginIP`=?,`LastLoginDate`=?,`LastLogin2Date`=?,`LastLogin3Date`=?,`LastLogoutDate`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.INTEGER, accountInfo.getUserId()));
        para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getUsername()));
        para.put(3, new DBParameter(Types.VARCHAR, accountInfo.getSite()));
        para.put(4, new DBParameter(Types.TINYINT, accountInfo.getIsOnline()));
        para.put(5, new DBParameter(Types.TIMESTAMP, accountInfo.getCreateDate()));
        para.put(6, new DBParameter(Types.TIMESTAMP, accountInfo.getForbidDate()));
        para.put(7, new DBParameter(Types.TIMESTAMP, accountInfo.getLastPayDate()));
        para.put(8, new DBParameter(Types.VARCHAR, accountInfo.getLastLoginIP()));
        para.put(9, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLoginDate()));
        para.put(10, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin2Date()));
        para.put(11, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin3Date()));
        para.put(12, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogoutDate()));
        para.put(13, new DBParameter(Types.VARCHAR, accountInfo.getUsername()));
        para.put(14, new DBParameter(Types.VARCHAR, accountInfo.getSite()));
        para.put(15, new DBParameter(Types.TINYINT, accountInfo.getIsOnline()));
        para.put(16, new DBParameter(Types.TIMESTAMP, accountInfo.getCreateDate()));
        para.put(17, new DBParameter(Types.TIMESTAMP, accountInfo.getForbidDate()));
        para.put(18, new DBParameter(Types.TIMESTAMP, accountInfo.getLastPayDate()));
        para.put(19, new DBParameter(Types.VARCHAR, accountInfo.getLastLoginIP()));
        para.put(20, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLoginDate()));
        para.put(21, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin2Date()));
        para.put(22, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin3Date()));
        para.put(23, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogoutDate()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean deleteByKey(Object... ids)
    {
        boolean result = false;
        String sql = "delete from t_u_account where `UserID`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.INTEGER, ids[0]));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public AccountInfo getByKey(Object... ids)
    {
        String sql = "select * from t_u_account where `UserID`=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.INTEGER, ids[0]));
        ResultSet rs = getDBHelper().execQuery(sql, para);
        List<AccountInfo> accounts = rsToEntityList(rs);
        if (accounts.size() > 0)
        {
            return accounts.get(0);
        }
        return null;
    }

    @Override
    public List<AccountInfo> listAll()
    {
        String sql = "select * from t_u_account;";
        ResultSet rs = getDBHelper().execQuery(sql);
        List<AccountInfo> accountInfos = rsToEntityList(rs);
        return accountInfos;
    }

    @Override
    public boolean addOrUpdateBatch(List<AccountInfo> accountInfos)
    {
        String sql = "insert into t_u_account(`UserID`,`Username`,`Site`,`IsOnline`,`CreateDate`,`ForbidDate`,`LastPayDate`,`LastLoginIP`,`LastLoginDate`,`LastLogin2Date`,`LastLogin3Date`,`LastLogoutDate`) values(?,?,?,?,?,?,?,?,?,?,?,?) on DUPLICATE KEY update `Username`=?,`Site`=?,`IsOnline`=?,`CreateDate`=?,`ForbidDate`=?,`LastPayDate`=?,`LastLoginIP`=?,`LastLoginDate`=?,`LastLogin2Date`=?,`LastLogin3Date`=?,`LastLogoutDate`=?;";
        PreparedStatement pstmt = getDBHelper().sqlBatch(sql);
        try
        {
            for (AccountInfo accountInfo : accountInfos)
            {
                Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
                para.put(1, new DBParameter(Types.INTEGER, accountInfo.getUserId()));
                para.put(2, new DBParameter(Types.VARCHAR, accountInfo.getUsername()));
                para.put(3, new DBParameter(Types.VARCHAR, accountInfo.getSite()));
                para.put(4, new DBParameter(Types.TINYINT, accountInfo.getIsOnline()));
                para.put(5, new DBParameter(Types.TIMESTAMP, accountInfo.getCreateDate()));
                para.put(6, new DBParameter(Types.TIMESTAMP, accountInfo.getForbidDate()));
                para.put(7, new DBParameter(Types.TIMESTAMP, accountInfo.getLastPayDate()));
                para.put(8, new DBParameter(Types.VARCHAR, accountInfo.getLastLoginIP()));
                para.put(9, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLoginDate()));
                para.put(10, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin2Date()));
                para.put(11, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin3Date()));
                para.put(12, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogoutDate()));
                para.put(13, new DBParameter(Types.VARCHAR, accountInfo.getUsername()));
                para.put(14, new DBParameter(Types.VARCHAR, accountInfo.getSite()));
                para.put(15, new DBParameter(Types.TINYINT, accountInfo.getIsOnline()));
                para.put(16, new DBParameter(Types.TIMESTAMP, accountInfo.getCreateDate()));
                para.put(17, new DBParameter(Types.TIMESTAMP, accountInfo.getForbidDate()));
                para.put(18, new DBParameter(Types.TIMESTAMP, accountInfo.getLastPayDate()));
                para.put(19, new DBParameter(Types.VARCHAR, accountInfo.getLastLoginIP()));
                para.put(20, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLoginDate()));
                para.put(21, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin2Date()));
                para.put(22, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogin3Date()));
                para.put(23, new DBParameter(Types.TIMESTAMP, accountInfo.getLastLogoutDate()));
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

    @Override
    public boolean deleteBatch(List<AccountInfo> accountInfos)
    {
        String sql = "delete from t_u_account where `UserID`=?;";
        PreparedStatement pstmt = getDBHelper().sqlBatch(sql);
        try
        {
            for (AccountInfo accountInfo : accountInfos)
            {
                Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
                para.put(1, new DBParameter(Types.INTEGER, accountInfo.getUserId()));
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

    @Override
    public AccountInfo rsToEntity(ResultSet rs) throws SQLException
    {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUserId(rs.getInt("UserID"));
        accountInfo.setUsername(rs.getString("Username"));
        accountInfo.setSite(rs.getString("Site"));
        accountInfo.setIsOnline(rs.getBoolean("IsOnline"));
        accountInfo.setCreateDate(rs.getTimestamp("CreateDate"));
        accountInfo.setForbidDate(rs.getTimestamp("ForbidDate"));
        accountInfo.setLastPayDate(rs.getTimestamp("LastPayDate"));
        accountInfo.setLastLoginIP(rs.getString("LastLoginIP"));
        accountInfo.setLastLoginDate(rs.getTimestamp("LastLoginDate"));
        accountInfo.setLastLogin2Date(rs.getTimestamp("LastLogin2Date"));
        accountInfo.setLastLogin3Date(rs.getTimestamp("LastLogin3Date"));
        accountInfo.setLastLogoutDate(rs.getTimestamp("LastLogoutDate"));
        return accountInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dao.IAccountInfoDao#getAccountInfo(java.lang.String, java.lang.String)
     */
    @Override
    public AccountInfo getAccountInfo(String username, String site)
    {
        String sql = "select * from t_u_account where username = ? and site = ?";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.VARCHAR, username);
        params.put(Types.VARCHAR, site);
        return query(sql, params);
    }
}
