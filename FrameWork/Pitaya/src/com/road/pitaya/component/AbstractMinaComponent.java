/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.component;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jiayi.wei
 */
public abstract class AbstractMinaComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMinaComponent.class);

    public static final String NAME = "mina";

    private IoAcceptor serverAcceptor = null;

    /**
     * 获取ClientIoHandler实例。
     * 
     * @return
     */
    protected abstract IoHandler getClientIoHandler();

    protected abstract void acceptorInit(IoAcceptor serverAcceptor);

    /**
     * 获取监听的端口。
     * 
     * @return
     */
    protected abstract int getPort();

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        serverAcceptor = new NioSocketAcceptor();
        serverAcceptor.setHandler(getClientIoHandler());
        acceptorInit(serverAcceptor);
        return true;
    }

    @Override
    public boolean start()
    {
        try
        {
            int port = getPort();
            serverAcceptor.bind(new InetSocketAddress(port));
            LOGGER.info("Listening at port " + port);
        }
        catch (IOException e)
        {
            LOGGER.error("", e);
            return false;
        }

        return true;
    }

    @Override
    public void stop()
    {

    }

    @Override
    public void destroy()
    {

    }
}
