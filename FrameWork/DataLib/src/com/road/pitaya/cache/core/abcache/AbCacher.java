/**
 * Date: 2013-5-22
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache.core.abcache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.WeakHashMap;

import com.road.pitaya.cache.CacherConfiguration;
import com.road.pitaya.cache.core.AbstractCacher;
import com.road.pitaya.cache.core.ICacheLoader;
import com.road.pitaya.cache.core.ICacheWriter;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.timer.GlobelTimer;
import com.road.pitaya.timer.ITask;

/**
 * 
 * @author jinjin.chen
 */
public class AbCacher<K, V> extends AbstractCacher<K, V> implements ITask
{

    private Map<K, V> cache;
    private Map<K, V> updateQueue;
    private Map<K, V> deleteQueue;
    private ICacheLoader<K, V> cacheLoader;
    private ICacheWriter<V> writer;
    private CacherConfiguration cacherConfiguration;

    public AbCacher(ICacheLoader<K, V> cacheLoader, ICacheWriter<V> writer,
            CacherConfiguration cacherConfiguration)
    {
        cache = new WeakHashMap<K, V>();
        updateQueue = new ConcurrentHashMap<K, V>();
        deleteQueue = new ConcurrentHashMap<K, V>();
        this.cacheLoader = cacheLoader;
        this.writer = writer;
        this.cacherConfiguration = cacherConfiguration;
        start();
    }

    @Override
    public boolean start()
    {
        ((GlobelTimer)ComponentManager.getInstance().getComponent("GlobelTimerComponent")).addRepeatTask(this,
                cacherConfiguration.getDelayTime(),
                cacherConfiguration.getRepeatPeriod());
        return true;
    }

    @Override
    public boolean save()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean clear()
    {
        cache.clear();
        updateQueue.clear();
        deleteQueue.clear();
        return true;
    }

    @Override
    public void put(K key, V value)
    {
        synchronized (cache)
        {
            if (cache.containsKey(key))
            {
                updateQueue.put(key, value);
            }
            cache.put(key, value);

        }
    }

    @Override
    public boolean delete(K key)
    {
        synchronized (cache)
        {
            V temp = cache.get(key);
            if (temp != null)
            {
                cache.remove(key);
                if (updateQueue.containsKey(key))
                {
                    updateQueue.remove(key);
                }
                deleteQueue.put(key, temp);
            }
        }
        return true;
    }

    @Override
    public V get(K key)
    {
        V value = cache.get(key);
        if (value == null)
        {
            if (this.cacheLoader instanceof ICacheLoader)
            {
                value = ((ICacheLoader<K, V>) this.cacheLoader)
                        .loadFromStorage(key);
            }
            else
            {
                return value;
            }
            if (value != null)
            {
                put(key, value);
            }
        }
        return value;
    }

    @Override
    public List<V> getList(List<K> keys)
    {
        List<V> values = new ArrayList<V>();
        // 从缓存查找不到的keys
        List<K> tempKeys = new ArrayList<K>();
        // 从缓存中获取
        for (K key : keys)
        {
            V value = cache.get(key);

            if (value == null)
            {
                tempKeys.add(key);
            }
            else
            {
                values.add(value);
            }
        }

        // 从存储介质中读取
        Map<K, V> tempValues = null;
        if (this.cacheLoader instanceof ICacheLoader)
        {
            tempValues = ((ICacheLoader<K, V>) this.cacheLoader)
                    .loadFromStorage(tempKeys);
        }
        if (tempValues != null)
        {
            for (K key : tempKeys)
            {
                V value = tempValues.get(key);
                if (value != null)
                {
                    put(key, value);
                    values.add(value);
                }
            }
        }
        return values;
    }

    @Override
    public List<V> getAll()
    {
        List<V> values = new ArrayList<V>();
        Set<Entry<K, V>> set = cache.entrySet();
        for (Entry<K, V> entry : set)
        {
            V value = entry.getValue();
            if (value != null)
            {
                values.add(value);
            }
        }
        return values;
    }

    private List<V> mapToList(Map<K, V> map)
    {
        List<V> values = new ArrayList<V>();
        map.entrySet();
        Set<Entry<K, V>> set = map.entrySet();
        for (Entry<K, V> entry : set)
        {
            V value = entry.getValue();
            if (value != null)
            {
                values.add(value);
            }
        }
        return values;
    }

    @Override
    public void execute()
    {
        synchronized (updateQueue)
        {
            writer.writeAll(mapToList(updateQueue));
            updateQueue.clear();
        }
        synchronized (deleteQueue)
        {
            writer.deleteAll(mapToList(deleteQueue));
            deleteQueue.clear();
        }
    }
}
