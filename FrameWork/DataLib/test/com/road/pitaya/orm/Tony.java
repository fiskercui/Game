/**
 * Date: 2013-5-22
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.orm;

import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

/**
 * CREATE TABLE `tony` 
 * ( 
 * `id` bigint(20) NOT NULL, 
 * `name` varchar(20) NOT NULL, 
 * `password` varchar(20) NOT NULL,
 *  PRIMARY KEY (`id`) 
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 测试脚本
 */

@PersistentEntity(sourceDomain = "tony", targetDomain = "tony", primaryKey = "id")
/**
 * 
 * @author jinjin.chen
 */
public class Tony
{
    @PersistentField
    private long id;

    @PersistentField
    private String name;

    @PersistentField
    private String password;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "id : " + id + " , name : " + name + " , password : " + password;
    }
}
