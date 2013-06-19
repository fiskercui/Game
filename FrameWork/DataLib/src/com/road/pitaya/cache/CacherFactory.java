/**
 * Date: 2013-5-27
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache;

import org.dom4j.Element;

import com.road.pitaya.cache.core.ICacheLoader;
import com.road.pitaya.cache.core.ICacheWriter;
import com.road.pitaya.cache.core.ICacher;
import com.road.pitaya.cache.core.ehcache.EhCacher;

/**
 * 
 * @author jinjin.chen
 */
public class CacherFactory
{
    public static <K, V> ICacher<K, V> createCacher(
            ICacheLoader<K, V> cacheLoader, ICacheWriter<V> cacheDBWriter,
            Element element)
    {
        return new EhCacher<K, V>(cacheLoader, cacheDBWriter,
                new CacherConfiguration(element));
    }

    public static <K, V> ICacher<K, V> createCacher(
            ICacheLoader<K, V> cacheLoader, ICacheWriter<V> cacheDBWriter,
            CacherConfiguration configuration)
    {
        return new EhCacher<K, V>(cacheLoader, cacheDBWriter, configuration);
    }
}
