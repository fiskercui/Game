/**
 * Date: 2013-5-24
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache.core;

/**
 * 
 * @author jinjin.chen
 */
public abstract class AbstractCacher<K, V> implements ICacher<K, V>
{
    protected String cacheName;
    protected ICacheLoader<K, V> dataLoader;
    protected ICacheWriter<V> dataWriter;

    public abstract boolean start();

    public abstract boolean save();

    public abstract boolean clear();

}
