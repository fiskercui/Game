/**
 * Date: 2013-5-30
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dao;

import com.road.entity.AccountInfo;
import com.road.pitaya.database.IBaseDao;

/**
 *  
 * 
 * @author yip
 */
public interface IAccountInfoDao extends IBaseDao<AccountInfo>
{
    public AccountInfo getAccountInfo(String username,String site);
}
