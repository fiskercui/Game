/**
 * Date: 2013-5-22
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.road.pitaya.cache.core.ICacheLoader;
import com.road.pitaya.dao.DaoManager;
import com.road.pitaya.entity.AccountInfo;

/**
 * 
 * @author jinjin.chen
 */
public class AccountLoader implements ICacheLoader<Long, AccountInfo>
{

    @Override
    public AccountInfo loadFromStorage(Long key)
    {
        return DaoManager.getAccountDao().getByKey(key);
    }

    @Override
    public Map<Long, AccountInfo> loadFromStorage(List<Long> keys)
    {
        /**
         * TODO mark Dao层应该提供一个根据keys类表获取实体对象的接口，不过这个接口应该是上层去实现
         * 
         * 暂时用get的方式实现一下
         */
        Map<Long, AccountInfo> accountInfos = new HashMap<Long, AccountInfo>();
        for (Long key : keys)
        {
            AccountInfo accountInfo = DaoManager.getAccountDao().getByKey(key);
            if (accountInfo != null)
            {
                accountInfos.put(key, accountInfo);
            }
        }

        return accountInfos;
    }

}
