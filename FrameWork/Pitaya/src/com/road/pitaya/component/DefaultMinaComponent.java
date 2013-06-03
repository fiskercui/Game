/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.component;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.road.pitaya.net.CommonMessageClientHandler;
import com.road.pitaya.net.mina.ClientIoHandler;
import com.road.pitaya.net.mina.StrictCodecFactory;
import com.road.util.ConfigWrapper;

/**
 * 
 * @author jiayi.wei
 */
public class DefaultMinaComponent extends AbstractMinaComponent
{
    @Override
    protected IoHandler getClientIoHandler()
    {
        ClientIoHandler ioHandler = new ClientIoHandler();
        ioHandler.setPacketHandler(new CommonMessageClientHandler());
        return ioHandler;
    }

    @Override
    protected void acceptorInit(IoAcceptor serverAcceptor)
    {
        ((NioSocketAcceptor) serverAcceptor).setReuseAddress(true);
        serverAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
        serverAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter( new StrictCodecFactory()));
        serverAcceptor.getSessionConfig().setReadBufferSize(4096);
        serverAcceptor.getSessionConfig().setBothIdleTime(60 * 30);// 设置断开时间30分钟
    }

    @Override
    protected int getPort()
    {
        return ConfigWrapper.getInt("server.port");
    }
}
