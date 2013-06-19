/**
 * Date: 2013-5-23
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache.core;

import java.util.List;
import java.util.Map;

/**
 * 数据加载接口<br>
 * 缓存加载器可以继承这个接口，实现loadFromStorage方法<br>
 * 当缓存不存在key对应的数据的时候<br>
 * 同实现的方法去加载数据。
 * 
 * @author jinjin.chen
 */
public interface ICacheLoader<K, V>
{
    /**
     * 从存储器中加载数据
     * 
     * @param key
     *            对应数据库中的主键
     * @return
     * 
     *         如果数据库中对应的实体是有多个主键的，采用连接主键作为key<br>
     *         例如：<br>
     * 
     *         [表t_u_player中有id和name作为主键,那么他的缓存的key是id_name]<br>
     */
    V loadFromStorage(K key);

    Map<K, V> loadFromStorage(List<K> keys);
}
