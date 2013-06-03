package com.road.pitaya.cache.core;

/**
 * Date: 2013-5-21
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
import java.util.List;

/**
 * 作为缓存库的基类，采用key-value的方式对数据对象进行缓存<br>
 * 可以继承这个基类实现缓存器<br>
 * 如果系统采用了缓存器，则建议所有数据操作都通过缓存器获取<br>
 * 绕过缓存器对数据库进行操作，该缓存器不提供同步<br>
 * 
 * @author jinjin.chen
 */
public interface ICacher<K, V>
{

    /**
     * 将对象缓存到缓存库中
     * 
     * @param key对应数据库中的主键
     * @param value
     *            缓存对象<br>
     * <br>
     * 
     *            如果数据库中对应的实体是有多个主键的，采用连接主键作为key<br>
     *            例如：<br>
     * 
     *            [表t_u_player中有id和name作为主键,那么他的缓存的key是id_name]<br>
     * @throws Exception
     */
    void put(K key, V value);

    /**
     * 将缓存对象从缓存库中移除，同时会删除对应数据库中的实体记录
     * 
     * @param key
     *            对应数据库中的主键
     * @return 从缓存中删除返回true，否则返回false。<br>
     * <br>
     * 
     *         这里的key同put接口
     */
    boolean delete(K key);

    /**
     * 从缓存库中获取缓存对象
     * 
     * @param key
     *            对应数据库中的主键
     * @return key对应的实体对象<br>
     * <br>
     * 
     *         这里的key同put接口
     */
    V get(K key);

    /**
     * 根据keys获取缓存对象集合
     * 
     * @param keys
     *            对应数据库中的主键集合
     * @return keys对应的实体集合<br>
     * <br>
     * 
     *         这里的keys同put接口
     */
    List<V> getList(List<K> keys);

    /**
     * 获取所有的缓存对象集合
     * 
     * @return 所有的缓存实体集合
     * 
     * @重要 是缓存中对象的集合，不是数据库中所有对象的集合
     */
    List<V> getAll();

}
