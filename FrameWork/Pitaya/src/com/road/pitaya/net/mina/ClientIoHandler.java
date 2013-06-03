/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.net.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.net.IClientConnection;
import com.road.pitaya.net.IClientPacketHandler;
import com.road.pitaya.type.CommonConst;

/**
 * 与客户端交互的处理器
 * 
 * @author jiayi.wei
 */
public class ClientIoHandler extends IoHandlerAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientIoHandler.class);

    private IClientPacketHandler handler = null;
    
    @Override
    public void sessionCreated(IoSession session) throws Exception
    {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception
    {
        IClientConnection conn = new MinaClientConnection(handler, session);
        session.setAttribute(CommonConst.CLIENT_CONNECTION, conn);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception
    {
        IClientConnection conn = (IClientConnection) session.getAttribute(CommonConst.CLIENT_CONNECTION);
        conn.onDisconnect();
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception
    {
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception
    {
        IClientConnection conn = (IClientConnection) session.getAttribute(CommonConst.CLIENT_CONNECTION);
        conn.getPacketHandler().process(conn, message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception
    {
        LOGGER.error(cause.toString());
    }
    
    public void setPacketHandler(IClientPacketHandler handler)
    {
        this.handler = handler;
    }
}
