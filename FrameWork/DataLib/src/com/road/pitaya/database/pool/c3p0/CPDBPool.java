package com.road.pitaya.database.pool.c3p0;

/**
 * Date: 2013-5-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
import java.sql.Connection;

import com.road.pitaya.database.pool.IDBPool;

/**
 * 
 * @author jinjin.chen
 */
public class CPDBPool implements IDBPool
{

    @Override
    public Connection getConnection()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean startup()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void shutdown()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public String getState()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCurConns()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean validConn()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
