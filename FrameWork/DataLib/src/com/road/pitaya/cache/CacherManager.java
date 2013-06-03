/**
 * Date: 2013-5-21
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.road.pitaya.cache.core.AbstractCacher;
import com.road.pitaya.cache.core.ICacher;
import com.road.pitaya.component.IComponent;

/**
 * 提供一个Cacher的管理器<br>
 * 这里只是提供简单的Cacher保存功能<br>
 * 不同的游戏项目可以自定义自己的管理器<br>
 * 
 * 不推荐使用这个缓存管理器<br>
 * 可以自己定义一个类似于DaoManager一样的管理器,这样方便上层调用<br>
 * 
 * @author jinjin.chen
 */
public final class CacherManager implements IComponent
{
    private Map<Class<?>, ICacher<?, ?>> cachers = new HashMap<Class<?>, ICacher<?, ?>>();

    private static final String NAME = "CacheManagerComponent";

    private static class LazyHolder
    {
        private static final CacherManager INSTANCE = new CacherManager();
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static CacherManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <K, V> ICacher<K, V> getCacher(Class<V> c, Class<K> key)
    {
        synchronized (cachers)
        {
            return (ICacher<K, V>) cachers.get(c);
        }
    }

    public void putCacher(Class<?> cacherName, ICacher<?, ?> cacher)
    {
        synchronized (cachers)
        {
            ICacher<?, ?> temp = cachers.get(cacherName);
            if (temp != null)
            {
                throw new RuntimeException(cacherName + " already exit!");
            }
            else
            {
                cachers.put(cacherName, cacher);
            }
        }
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        // do nothing
        return true;
    }

    @Override
    public boolean start()
    {
        return true;
    }

    @Override
    public void stop()
    {
        Set<Entry<Class<?>, ICacher<?, ?>>> sets = cachers.entrySet();

        for (Entry<Class<?>, ICacher<?, ?>> entry : sets)
        {
            // 这里强转不安全
            ((AbstractCacher<?, ?>) entry.getValue()).save();
            ((AbstractCacher<?, ?>) entry.getValue()).clear();
        }
        cachers.clear();
    }

    @Override
    public void destroy()
    {
        // do nothing
    }
}
