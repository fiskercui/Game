/**
 * Date: 2013-5-30
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.bll;

import com.road.entity.AccountInfo;

/**
 * 
 * @author yip
 */
public class AccountBussiness
{
    /**
     * 获取玩家帐号信息
     * @param username 用户名
     * @param site  区号
     * @return
     */
    public static AccountInfo getAccountInfo(String username,String site)
    {
        return DaoManager.getAccountInfoDao().getAccountInfo(username, site);
    }
}
