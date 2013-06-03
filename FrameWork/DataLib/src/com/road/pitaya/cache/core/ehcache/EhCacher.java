/**
 * Date: 2013-5-21
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
package com.road.pitaya.cache.core.ehcache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheWriterConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

import com.road.pitaya.cache.CacherConfiguration;
import com.road.pitaya.cache.core.AbstractCacher;
import com.road.pitaya.cache.core.ICacheLoader;
import com.road.pitaya.cache.core.ICacheWriter;

/**
 * 
 * @author jinjin.chen
 */
public class EhCacher<K, V> extends AbstractCacher<K, V>
{

    private String cacheName = null;
    /**
     * ehcache框架里面的缓存器
     */
    private Cache cache = null;
    private CacheWriter cacheWriter = null;
    private EhInnerLoader loader = null;
    private CacherConfiguration cacheConfiguration;

    // 数据时否持久化 TODO 考虑去掉
    private boolean isPersistent = true;

    public EhCacher(ICacheLoader<K, V> cacheLoader,
            ICacheWriter<V> cacheDBWriter,
            CacherConfiguration cacheConfiguration)
    {
        this.dataWriter = cacheDBWriter;
        this.dataLoader = cacheLoader;
        this.cacheConfiguration = cacheConfiguration;
        this.cacheName = cacheConfiguration.getCacheName();
        loader = new EhInnerLoader();
        cacheWriter = new EhCacherWriter();
        start();
    }

    @Override
    public boolean start()
    {
        Configuration configuration = ConfigurationFactory.parseConfiguration();
        configuration.setUpdateCheck(false);
        CacheManager cacheManager = CacheManager.create(configuration);
        CacheManager.create();
        cache = cacheManager.getCache(cacheName);
        cache = new Cache(
                new CacheConfiguration(cacheName, cacheConfiguration
                        .getMaxCacherInCacheManager()).cacheWriter(new CacheWriterConfiguration()
                        .writeMode(
                                CacheWriterConfiguration.WriteMode.WRITE_BEHIND)
                        .maxWriteDelay(cacheConfiguration.getMaxWriteDelay())
                        .rateLimitPerSecond(
                                cacheConfiguration.getRateLimitPerSecond())
                        .writeCoalescing(cacheConfiguration.isWriteCoalescing())
                        .writeBatching(cacheConfiguration.isWriteBatching())
                        .writeBatchSize(cacheConfiguration.getWriteBatchSize())
                        .retryAttempts(cacheConfiguration.getRetryAttempts())
                        .retryAttemptDelaySeconds(
                                cacheConfiguration
                                        .getRetryAttemptDelaySeconds())));
        cache.registerCacheLoader(loader);
        cache.registerCacheWriter(cacheWriter);
        cacheManager.addCache(cache);
        this.isPersistent = cacheConfiguration.isPersistent();
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
        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.removeCache(cacheName);
        return true;
    }

    @Override
    public void put(K key, V value)
    {
        getCache().acquireWriteLockOnKey(key);// 加锁
        Element element = new Element(key, value);
        putCacheData(element);
        getCache().releaseWriteLockOnKey(key);// 解锁
    }

    /**
     * 根据isPersistent的值选择是否需要持久到数据库
     * 
     * @param element
     *            缓存的数据
     */
    private void putCacheData(Element element)
    {
        if (isPersistent)
        {
            getCache().putWithWriter(element);
        }
        else
        {
            getCache().put(element);
        }

    }

    @Override
    public boolean delete(K key)
    {
        boolean result = false;
        getCache().acquireWriteLockOnKey(key);// 加锁
        result = getCache().removeWithWriter(key);
        getCache().releaseWriteLockOnKey(key);// 解锁
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key)
    {
        Element element = getCache().getWithLoader(key, loader, null);
        return (V) element.getObjectValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<V> getList(List<K> keys)
    {
        Map<K, V> entits = (Map<K, V>) getCache().getAllWithLoader(keys, null);
        Set<Entry<K, V>> sets = entits.entrySet();
        List<V> values = new ArrayList<V>();
        for (Entry<K, V> entry : sets)
        {
            values.add(entry.getValue());
        }
        return values;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<V> getAll()
    {
        return getList(getCache().getKeys());
    }

    private Cache getCache()
    {
        return cache;
    }

    @SuppressWarnings("unchecked")
    class EhInnerLoader implements CacheLoader
    {

        @Override
        public Object load(Object key) throws CacheException
        {
            // 如果该存储器实现了数据加载接口，则从存储介质（DB，File等）中加载数据
            V value = null;
            if (EhCacher.this.dataLoader instanceof ICacheLoader)
            {
                value = ((ICacheLoader<K, V>) EhCacher.this.dataLoader)
                        .loadFromStorage((K) key);
            }
            if (value == null)
            {
                return value;
            }

            Element element = null;
            getCache().acquireWriteLockOnKey(key);// 加锁
            element = new Element(key, value);
            getCache().put(element);// 添加到缓存，这个时候不需要回调writer
            getCache().releaseWriteLockOnKey(key);// 解锁
            return value;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Map loadAll(Collection keys)
        {
            // 如果该存储器实现了数据加载接口，则从存储介质（DB，File等）中加载数据
            List<K> _keys = new ArrayList<K>(keys);
            Map<K, V> values = null;
            if (EhCacher.this.dataLoader instanceof ICacheLoader)
            {
                values = ((ICacheLoader<K, V>) EhCacher.this.dataLoader)
                        .loadFromStorage(_keys);
            }
            if (values == null)
            {
                return values;
            }

            for (K key : _keys)
            {
                V value = values.get(key);
                Element element = null;
                if (value != null)
                {
                    getCache().acquireWriteLockOnKey(key);// 加锁
                    element = new Element(key, value);
                    getCache().put(element);// 添加到缓存，这个时候不需要回调writer
                    getCache().releaseWriteLockOnKey(key);// 解锁
                }
            }
            return values;
        }

        @Override
        public Object load(Object key, Object argument)
        {
            System.out.println("EhInnerLoader load argument test");
            // do nothing
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Map loadAll(Collection keys, Object argument)
        {
            System.out.println("EhInnerLoader loadAll argument test");
            // do nothing
            return null;
        }

        @Override
        public String getName()
        {
            return null;
        }

        @Override
        public CacheLoader clone(Ehcache cache)
                throws CloneNotSupportedException
        {
            return null;
        }

        @Override
        public void init()
        {

        }

        @Override
        public void dispose() throws CacheException
        {

        }

        @Override
        public Status getStatus()
        {
            return null;
        }

    }

    class EhCacherWriter implements CacheWriter
    {
        @Override
        public CacheWriter clone(Ehcache ehcache)
                throws CloneNotSupportedException
        {
            System.err.println("EhCacherWriter clone");
            return null;
        }

        @Override
        public void dispose() throws CacheException
        {
            System.err.println("EhCacherWriter dispose");
        }

        @Override
        public void init()
        {
            System.err.println("EhCacherWriter init");
        }

        @Override
        public void throwAway(Element arg0, SingleOperationType arg1,
                RuntimeException arg2)
        {
            System.err.println("EhCacherWriter throwAway");
        }

        @SuppressWarnings("unchecked")
        @Override
        public void delete(CacheEntry cacheEntry) throws CacheException
        {
            System.err.println("EhCacherWriter delete");
            EhCacher.this.dataWriter.delete((V) cacheEntry.getElement()
                    .getObjectValue());
        }

        @SuppressWarnings("unchecked")
        @Override
        public void deleteAll(Collection<CacheEntry> cacheEntrys)
                throws CacheException
        {
            System.out
                    .println("============================ test EhCacherWriter deleteAll============================");
            List<V> values = new ArrayList<V>();
            for (CacheEntry cacheEntry : cacheEntrys)
            {
                values.add((V) cacheEntry.getElement().getObjectValue());
            }
            EhCacher.this.dataWriter.deleteAll(values);
            System.out
                    .println("============================ test EhCacherWriter over ================================");
        }

        @SuppressWarnings("unchecked")
        @Override
        public void write(Element element) throws CacheException
        {
            System.err.println("EhCacherWriter write");
            EhCacher.this.dataWriter.write((V) element.getObjectValue());
        }

        @SuppressWarnings("unchecked")
        @Override
        public void writeAll(Collection<Element> elements)
                throws CacheException
        {
            System.err.println("EhCacherWriter writeAll");
            List<V> values = new ArrayList<V>();
            for (Element element : elements)
            {
                values.add((V) element.getObjectValue());
            }
            EhCacher.this.dataWriter.writeAll(values);
        }
    }
}
