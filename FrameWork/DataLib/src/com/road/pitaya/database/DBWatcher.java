/**
 * Date: 2013-5-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.util.TimeUtil;

/**
 * 
 * @author jinjin.chen
 */
public class DBWatcher
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DBWatcher.class);
    private long first = 0;
    private long second = 0;
    private long end = 0;

    public DBWatcher()
    {
        first = TimeUtil.getSysCurTimeMillis();
    }

    public void watchGetConnector()
    {
        second = TimeUtil.getSysCurTimeMillis();
    }

    public void watchSqlExec()
    {
        end = TimeUtil.getSysCurTimeMillis();
    }

    public void keeyRecords(String procName)
    {
        long spendTime = end - first;
        if (spendTime > 1000)
        {
            LOGGER.error(String.format(
                    "执行语句%s花耗时间总时间 超过:%sms,获取连接：%sms,执行sql:%sms", procName,
                    spendTime, second - first, end - second));
        }
    }
}
