/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.net;

/**
 * 包处理器
 * 
 * @author jiayi.wei
 */
public interface IClientPacketHandler
{
    void process(IClientConnection conn, Object packet);
}
