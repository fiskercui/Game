/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.game;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.road.pitaya.net.IClientConnection;



/*
 * 
 * connection管理器
 * 暂时没有用到
 * @author weihua.cui
 */
public class ConnectionManager
{

    private Map<Long, IClientConnection> clientSessions = new ConcurrentHashMap<Long, IClientConnection>();

    private static class LazyHolder
    {
        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    /**
     * 获取ConnectionManager实例。
     * @return
     */
    public static ConnectionManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    
    public void addClientSession(IClientConnection connect)
    {
//        this.clientSessions.put(connect.getConnectionId(), connect);
    }

    public void removeClientSession(long connectId)
    {
        this.clientSessions.remove(connectId);
    }

    public IClientConnection getClientSession(long connectId)
    {
        return this.clientSessions.get(connectId);
    }

    public boolean containsClientSession(long connectId)
    {
        return this.clientSessions.containsKey(connectId);
    }

}
