/**
 * Date: 2013-5-21
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache.core;

import java.util.List;

import com.road.pitaya.database.IBaseDao;

/**
 * 作为对数据库持久的实现 抽象类继承ICacheWriter
 * 
 * @author jinjin.chen
 * @param <T>
 */
public abstract class AbstractCacheWriter<T> implements ICacheWriter<T>
{

    private IBaseDao<T> baseDao;

    public AbstractCacheWriter(IBaseDao<T> baseDao)
    {
        this.baseDao = baseDao;
    }

    @Override
    public boolean write(T v)
    {
        return baseDao.addOrUpdate(v);
    }

    @Override
    public boolean writeAll(List<T> values)
    {
        return baseDao.addOrUpdateBatch(values);
    }

    @Override
    public boolean delete(T v)
    {
        return baseDao.delete(v);
    }

    @Override
    public boolean deleteAll(List<T> values)
    {
        return baseDao.deleteBatch(values);
    }

}
