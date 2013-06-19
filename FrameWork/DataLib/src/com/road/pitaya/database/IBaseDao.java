/**
 * Date: 2013-5-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */
package com.road.pitaya.database;

import java.util.List;

/**
 * 
 * @author jinjin.chen
 */
public interface IBaseDao<T>
{

    /**
     * 添加
     * 
     * @param t
     * @return
     */
    boolean add(T t);

    /**
     * 修改
     * 
     * @param t
     * @return
     */
    boolean update(T t);

    /**
     * 根据脚本执行更新
     * 
     * @param sql
     *            更新脚本
     * @param paramWrapper
     *            参数
     * @return
     */
    boolean update(String sql, DBParamWrapper paramWrapper);

    /**
     * 删除
     * 
     * @param t
     * @return
     */
    boolean delete(T t);

    /**
     * 添加或修改缓存
     * 
     * @param t
     * @return
     */
    boolean addOrUpdate(T t);

    /**
     * @param ids
     *            对应数据库中主键
     * @return
     * @注意 参数传入的顺序对应脚本中的主键设置顺序
     */
    boolean deleteByKey(Object... ids);

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            查询的脚本
     * @param paramWrapper
     *            参数
     * @return 返回查询结果对象
     */
    T query(String sql, DBParamWrapper paramWrapper);

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            sql 查询的脚本
     * @param paramWrapper
     *            参数
     * @return 返回查询结果对象集合
     */
    List<T> queryList(String sql, DBParamWrapper paramWrapper);

    /**
     * @param ids
     *            对应数据库中主键
     * @return
     * @注意 参数传入的顺序对应脚本中的主键设置顺序
     */
    T getByKey(Object... ids);

    /**
     * 查询所有
     */
    List<T> listAll();

    /**
     * 批量添加或修改实体
     * 
     * @param t
     * @return
     */
    boolean addOrUpdateBatch(List<T> t);

    /**
     * 批量删除实体
     * 
     * @param t
     * @return
     */
    boolean deleteBatch(List<T> t);
}
