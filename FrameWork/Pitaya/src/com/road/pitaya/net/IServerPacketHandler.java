/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.net;

/**
 * 服务器包处理器，用来处理从目标服务器接收到的包。
 * 
 * @author jiayi.wei
 */
public interface IServerPacketHandler
{
    void process(IServerConnector client, Object packet);
}
