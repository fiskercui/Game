/**
 * Date: 2013-5-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
package com.road.pitaya.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.database.pool.DBHelper;

/**
 * 
 * @author jinjin.chen
 */
public abstract class BaseDao<T>
{
    /**
     * 数据库Helper
     */
    protected static DBHelper dbhelper = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);

    public BaseDao(DBHelper helper)
    {
        setDBHelper(helper);
    }

    public void setDBHelper(DBHelper helper)
    {
        dbhelper = helper;
    }

    protected DBHelper getDBHelper()
    {
        return dbhelper;
    }

    /**
     * 根据脚本执行更新
     * 
     * @param sql
     *            查询的脚本
     * @param paramWrapper
     *            参数
     * @return
     */
    public boolean update(String sql, DBParamWrapper paramWrapper)
    {
        boolean result = false;
        result = getDBHelper().execNoneQuery(sql, paramWrapper.getParams()) > -1 ? true
                : false;
        return result;
    }

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            查询的脚本
     * @param paramWrapper
     *            参数
     * @return
     */
    public T query(String sql, DBParamWrapper paramWrapper)
    {
        ResultSet rs = getDBHelper().execQuery(sql, paramWrapper.getParams());
        List<T> entitis = rsToEntityList(rs);
        getDBHelper().closeConn(rs);//释放连接
        if (entitis.size() > 0)
        {
            return entitis.get(0);
        }
        return null;
    }

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            sql 查询的脚本
     * @param paramWrapper
     *            参数
     * @return 返回查询结果对象集合
     */
    public List<T> queryList(String sql, DBParamWrapper paramWrapper)
    {
        ResultSet rs = getDBHelper().execQuery(sql, paramWrapper.getParams());
        List<T> entitis = rsToEntityList(rs);
        getDBHelper().closeConn(rs);//释放连接
        return entitis;
    }

    /**
     * 将ResultSet转换成List
     * 
     * @param rs
     * @return
     */
    protected List<T> rsToEntityList(ResultSet rs)
    {
        List<T> entitis = null;
        if (rs != null)
        {
            try
            {
                entitis = new ArrayList<T>();
                while (rs.next())
                {
                    T entity = rsToEntity(rs);
                    entitis.add(entity);
                }
            }
            catch (Exception e)
            {
                LOGGER.error(e.toString());
            }
            finally
            {
                getDBHelper().closeConn(rs);
            }
        }
        return entitis;
    }

    /**
     * 将resultset转为实体对象
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    protected abstract T rsToEntity(ResultSet rs) throws SQLException;

}
