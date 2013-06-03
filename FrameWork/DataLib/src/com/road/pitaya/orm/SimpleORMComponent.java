/**
 * Date: 2013-5-22
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.orm;

import com.road.pitaya.component.IComponent;

/**
 * 
 * @author jinjin.chen
 */
public abstract class SimpleORMComponent implements IComponent
{
    private String name;

    public SimpleORMComponent(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public boolean initialize()
    {
        return false;
    }

    @Override
    public void stop()
    {
        // do nothing
    }

    @Override
    public void destroy()
    {
        // do nothing
    }
}
