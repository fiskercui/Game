/**
 * Date: 2013-5-21
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
package com.road.pitaya.cache.core;

import java.util.List;

/**
 * 作为缓存器的数据持久接口，提供对应的持久和删除接口方法
 * 
 * @author jinjin.chen
 */
public interface ICacheWriter<V>
{
    /**
     * 实现对缓存器中数据修改后做数据持久
     * 
     * @param v
     *            修改的数据
     * @return 持久该数据成功返回true,失败返回false
     */
    boolean write(V v);

    /**
     * 实现对缓存器中数据修改后做数据持久
     * 
     * @param values
     *            修改的部分数据集合
     * @return 持久该数据成功返回true,失败返回false
     */
    boolean writeAll(List<V> values);

    /**
     * 实现对缓存器中数据被移除缓存器后对数据处理删除（或者其它操作）
     * 
     * @param v
     *            从缓存器中移除的对象
     * @return 删除该对象成功返回true，失败返回false
     */
    boolean delete(V v);

    /**
     * 实现对缓存器中数据被移除缓存器后对数据处理删除（或者其它操作）
     * 
     * @param values
     *            从缓存器中移除的对象集合
     * @return
     */
    boolean deleteAll(List<V> values);
}
