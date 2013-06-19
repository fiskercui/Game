/**
 * Date: 2013-5-22
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.orm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.Query;
import it.biobytes.ammentos.query.QueryFilter;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 
 * @author jinjin.chen
 */
public class AmmentosORMComponent extends SimpleORMComponent
{
    /**
     * 连接数据库的url，包括有连接地址，用户名和密码
     */
    private String url = null;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AmmentosORMComponent.class);

    public AmmentosORMComponent(String url, String dbUserName, String dbPassword)
    {
        super("Ammentos");
        this.url = url + "&user=" + dbUserName + "&password=" + dbPassword;
    }

    @Override
    public boolean start()
    {
        try
        {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(url);
            Ammentos.setDataSource(dataSource);
        }
        catch (Exception e)
        {
            LOGGER.error("启动出错 : " + e.toString());
            return false;
        }
        return true;
    }

    public static <T> List<T> listAll(Class<T> classname)
            throws PersistenceException
    {
        try
        {
            return Ammentos.load(classname, new Query());
        }
        catch (PersistenceException e)
        {
            LOGGER.error("获取数据失败 : " + e.toString());
            throw e;
        }
    }

    /**
     * 这个接口可能需要调整
     * 
     * @param classname
     * @param filter
     * @return
     * @throws PersistenceException
     */
    public static <T> List<T> name(Class<T> classname, QueryFilter filter)
            throws PersistenceException
    {
        try
        {
            return Ammentos.load(classname, filter);
        }
        catch (PersistenceException e)
        {
            LOGGER.error("获取数据失败 : " + e.toString());
            throw e;
        }
    }

    public static <T> T getEntity(Class<T> classname, Object... keys)
            throws PersistenceException
    {
        try
        {
            return Ammentos.load(classname, keys);
        }
        catch (PersistenceException e)
        {
            LOGGER.error("获取数据失败 : " + e.toString());
            throw e;
        }
    }
}
