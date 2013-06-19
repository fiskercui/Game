/**
 * Date: 2013-5-21
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache;

import com.road.pitaya.cache.core.AbstractCacheWriter;
import com.road.pitaya.database.IBaseDao;
import com.road.pitaya.entity.AccountInfo;

/**
 * 
 * @author jinjin.chen
 */
public class AccountWriter extends AbstractCacheWriter<AccountInfo>
{
    
    /**
     * @param baseDao
     */
    public AccountWriter(IBaseDao<AccountInfo> baseDao)
    {
        super(baseDao);
    }
}
