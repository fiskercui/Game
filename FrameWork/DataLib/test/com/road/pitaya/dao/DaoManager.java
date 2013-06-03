package com.road.pitaya.dao;

import com.road.pitaya.database.DBPoolManager;

import com.road.pitaya.dao.impl.AccountDaoImpl;
import com.road.pitaya.dao.impl.PlayerDaoImpl;

public class DaoManager
{
    private static final IAccountDao accountDao = new AccountDaoImpl(DBPoolManager.getInstance().getDBHelper(ConnStrategy.getMainDB()));
    
    
    public static IAccountDao getAccountDao()
    {
        return accountDao;
    }

    private static final IPlayerDao playerDao = new PlayerDaoImpl(DBPoolManager.getInstance().getDBHelper(ConnStrategy.getMainDB()));

    public static IPlayerDao getPlayerDao()
    {
        return playerDao;
    }
}
