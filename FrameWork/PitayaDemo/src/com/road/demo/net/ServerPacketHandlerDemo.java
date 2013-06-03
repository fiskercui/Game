/**
 * Date: May 30, 2013
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.demo.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IServerConnector;
import com.road.pitaya.net.IServerPacketHandler;

/**
 * 
 * @author jiayi.wei
 */
public class ServerPacketHandlerDemo implements IServerPacketHandler
{
    private static final Logger LOGGER= LoggerFactory.getLogger(ServerPacketHandlerDemo.class);

    /* (non-Javadoc)
     * @see com.road.pitaya.net.IServerPacketHandler#process(com.road.pitaya.net.IServerConnector, java.lang.Object)
     */
    @Override
    public void process(IServerConnector client, Object packet)
    {
        // TODO Auto-generated method stub
        CommonMessage msg = (CommonMessage)packet;
        LOGGER.debug(msg.headerToStr());
    }
}
