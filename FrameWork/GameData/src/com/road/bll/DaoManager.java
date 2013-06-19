package com.road.bll;

import com.road.dao.IAccountInfoDao;
import com.road.dao.IMailInfoDao;
import com.road.dao.IPlayerInfoDao;
import com.road.dao.impl.AccountInfoDaoImpl;
import com.road.dao.impl.MailInfoDaoImpl;
import com.road.dao.impl.PlayerInfoDaoImpl;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.dao.ConnStrategy;
import com.road.pitaya.database.DBPoolManager;

/**
 * 
 * @author volcano
 * @date 2013-5-28
 * @version 
 *
 */
public class DaoManager
{
    private static final IPlayerInfoDao playerInfoDao = new PlayerInfoDaoImpl(((DBPoolManager)ComponentManager.getInstance().getComponent("DBPoolManagerComponent")).getDBHelper(ConnStrategy.getMainDB()));
    private static final IMailInfoDao mailInfoDao = new MailInfoDaoImpl(((DBPoolManager)ComponentManager.getInstance().getComponent("DBPoolManagerComponent")).getDBHelper(ConnStrategy.getMainDB()));
    private static final IAccountInfoDao accountInfoDao = new AccountInfoDaoImpl(((DBPoolManager)ComponentManager.getInstance().getComponent("DBPoolManagerComponent")).getDBHelper(ConnStrategy.getMainDB()));
    
    public static IPlayerInfoDao getPlayerInfoDao()
    {
    	return playerInfoDao;
    }
    
    public static IMailInfoDao getMailInfoDao()
    {
        return mailInfoDao;
    }
    
    public static IAccountInfoDao getAccountInfoDao()
    {
        return accountInfoDao;
    }
}
