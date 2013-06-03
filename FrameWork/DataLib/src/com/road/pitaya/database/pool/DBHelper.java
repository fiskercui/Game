/**
 * Date: 2013-5-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
package com.road.pitaya.database.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.database.DBParameter;
import com.road.pitaya.database.DBWatcher;
import com.road.pitaya.database.DataReader;

/**
 * 
 * @author jinjin.chen
 */
public class DBHelper
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DBHelper.class);

    private IDBPool pool;

    public DBHelper(IDBPool pool)
    {
        this.pool = pool;
    }

    /**
     * @Action INSERT, UPDATE or DELETE
     * @param sql
     *            执行的脚本
     * @return
     */
    public int execNoneQuery(String sql)
    {
        return execNoneQuery(sql, null);
    }

    /**
     * @Action INSERT, UPDATE or DELETE
     * @param sql
     *            执行的脚本
     * @param params
     *            脚本参数
     * @return
     */
    public int execNoneQuery(String sql, Map<Integer, DBParameter> params)
    {
        int result = 0;
        DBWatcher watcher = new DBWatcher();
        Connection conn = openConn();
        watcher.watchGetConnector();
        if (conn == null)
        {
            return result;
        }
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conn.prepareStatement(sql);
            prepareCommand(pstmt, params);
            result = pstmt.executeUpdate();
            watcher.watchSqlExec();
            watcher.keeyRecords(sql);
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            closeConn(conn, pstmt);
        }
        return result;
    }

    /**
     * @Action SELECT
     * @param sql
     *            执行的脚本
     * @return
     */
    public ResultSet execQuery(String sql)
    {
        return execQuery(sql, null);
    }

    /**
     * @Action SELECT
     * @param sql
     *            执行的脚本
     * @param params
     *            脚本参数
     * @return
     */
    public ResultSet execQuery(String sql, Map<Integer, DBParameter> params)
    {
        DBWatcher watcher = new DBWatcher();
        Connection conn = openConn();
        watcher.watchGetConnector();
        if (conn == null)
        {
            return null;
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = conn.prepareStatement(sql);
            prepareCommand(pstmt, params);
            rs = pstmt.executeQuery();
            watcher.watchSqlExec();
            watcher.keeyRecords(sql);
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
            closeConn(conn, pstmt);
        }
        finally
        {
        }
        return rs;
    }

    /**
     * 执行无参查询并返回单一记录
     * 
     * @param sqlText
     * @param reader
     *            记录读取接口，实现单一记录读取过程
     * @return
     */
    public <T> T executeQuery(String sql, DataReader<T> reader)
    {
        return executeQuery(sql, null, reader);
    }

    /**
     * 执行查询并返回单一记录
     * 
     * @param sqlText
     * @param params
     * @param reader
     *            记录读取接口，实现单一记录读取过程
     * @return
     */
    public <T> T executeQuery(String sql, Map<Integer, DBParameter> params,
            DataReader<T> reader)
    {
        DBWatcher watcher = new DBWatcher();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T resultData = null;
        Connection conn = openConn();

        if (conn != null)
        {
            try
            {
                pstmt = conn.prepareStatement(sql);
                prepareCommand(pstmt, params);
                rs = pstmt.executeQuery();

                if (rs.last())
                {
                    resultData = reader.ReadData(rs);
                }
            }
            catch (Exception e)
            {
                LOGGER.error(e.toString());

            }
            finally
            {
                closeConn(conn, pstmt, rs);
                watcher.keeyRecords(sql);
            }
        }

        return resultData;
    }

    public PreparedStatement sqlBatch(String sql)
    {
        DBWatcher watcher = new DBWatcher();
        Connection conn = openConn();
        watcher.watchGetConnector();
        if (conn == null)
        {
            return null;
        }
        PreparedStatement pstmt = null;
        try
        {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        return pstmt;
    }

    /**
     * @Action INSERT_Batch, UPDATE_Batch or DELETE_Batch
     * @param sqlComm
     *            执行的脚本
     * @return 返回影响行数
     */
    public int sqlBatch(List<String> sqlComm)
    {
        int[] results = null;
        int result = 0;
        Connection conn = openConn();

        if (conn == null)
        {
            return result;
        }
        Statement stmt = null;
        try
        {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (int i = 0; i < sqlComm.size(); i++)
            {
                stmt.addBatch(sqlComm.get(i));
            }
            results = stmt.executeBatch();

        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            closeConn(conn, stmt);
        }
        if (results != null)
        {
            for (int i = 0; i < results.length; i++)
            {
                result += results[i];
            }
        }
        return result;
    }

    /**
     * 给Statement赋值
     * 
     * @param pstmt
     * @param parms
     * @throws SQLException
     */
    public PreparedStatement prepareCommand(PreparedStatement pstmt,
            Map<Integer, DBParameter> parms) throws SQLException
    {
        if (pstmt == null || parms == null)
            return null;
        for (Map.Entry<Integer, DBParameter> entry : parms.entrySet())
        {
            pstmt.setObject(entry.getKey(), entry.getValue().getResult());
        }

        return pstmt;
    }

    /**
     * 获取连接池的连接
     * 
     * @return
     */
    protected Connection openConn()
    {
        return pool.getConnection();
    }

    /**
     * 关闭ResultSet
     * 
     * @param rs
     */
    public void closeConn(ResultSet rs)
    {
        try
        {
            if (rs != null)
            {
                Statement stmt = rs.getStatement();
                closeConn(stmt, rs);
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
    }

    /**
     * 关闭Statement和ResultSet
     * 
     * @param stmt
     * @param rs
     */
    public void closeConn(Statement stmt, ResultSet rs)
    {
        try
        {
            if (stmt != null)
            {
                Connection conn = stmt.getConnection();
                closeConn(conn, stmt, rs);
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
    }

    /**
     * 关闭Connection、Statem和ResultSet
     * 
     * @param conn
     * @param stmt
     * @param rs
     */
    public void closeConn(Connection conn, Statement stmt, ResultSet rs)
    {
        try
        {
            if (rs != null)
            {
                rs.close();
                rs = null;
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            closeConn(conn, stmt);
        }

    }

    /**
     * 关闭Conne和Statement
     * 
     * @param conn
     * @param stmt
     */
    protected void closeConn(Connection conn, Statement stmt)
    {
        try
        {
            if (stmt != null)
            {
                if (stmt instanceof PreparedStatement)
                {

                    ((PreparedStatement) stmt).clearParameters();
                }
                stmt.close();
                stmt = null;
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            closeConn(conn);
        }
    }

    /**
     * 关闭Connection
     * 
     * @param conn
     */
    protected void closeConn(Connection conn)
    {

        try
        {
            if (conn != null)
            {
                conn.setAutoCommit(true);
                conn.close();
                conn = null;
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
        }
    }
}
