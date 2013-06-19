/**
 * Date: 2013-5-22
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya;

import java.util.List;

import it.biobytes.ammentos.PersistenceException;

import com.road.pitaya.orm.AmmentosORMComponent;
import com.road.pitaya.orm.Tony;

/**
 * 
 * @author jinjin.chen
 */
public class TestORM
{
    public static void main(String[] args) throws PersistenceException
    {
        test01();
        List<Tony> tonys = AmmentosORMComponent.listAll(Tony.class);
        for (Tony tony : tonys)
        {
            System.err.println(tony.toString());
        }
    }

    /**
     * 
     */
    private static void test01()
    {
        String url = "jdbc:mysql://localhost:3306/db_ddw?characterEncoding=utf-8&autoReconnect=true";
        String name = "root";
        String password = "root";
        
        AmmentosORMComponent ammentosORMComponent = new AmmentosORMComponent(url, name, password);
        ammentosORMComponent.start();
    }
}
