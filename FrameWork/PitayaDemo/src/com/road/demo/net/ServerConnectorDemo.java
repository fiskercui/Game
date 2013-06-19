/**
 * Date: May 30, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.demo.net;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IServerConnector;
import com.road.pitaya.net.IServerPacketHandler;
import com.road.pitaya.net.mina.MinaServerConnector;
import com.road.pitaya.net.mina.ServerConnectorIoHandler;
import com.road.pitaya.net.mina.StrictCodecFactory;

/**
 * 
 * @author jiayi.wei
 */
public class ServerConnectorDemo
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ServerConnectorDemo.class);

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String address = "10.10.9.251";
        int port = 9999;
        IServerPacketHandler packetHandler = new ServerPacketHandlerDemo();
        IoHandlerAdapter handler = new ServerConnectorIoHandler();
        ProtocolCodecFactory factory = new StrictCodecFactory();

        IServerConnector connector = new MinaServerConnector(address, port,
                packetHandler, handler, factory);
        if (connector.connect())
        {
            LOGGER.debug("连接成功。");
        }

        CommonMessage msg = new CommonMessage((short) 0x01);
        msg.setEmpty1(111);
        msg.setEmpty2(222);
        connector.send(msg, 9999);
    }
}
