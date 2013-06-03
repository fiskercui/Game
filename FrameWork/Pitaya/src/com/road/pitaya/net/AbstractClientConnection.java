/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.net;

/**
 * 
 * @author jiayi.wei
 */
public abstract class AbstractClientConnection implements IClientConnection
{
    private IConnectionHolder holder = null;
    private IClientPacketHandler packetHandler = null;

    public AbstractClientConnection(IClientPacketHandler packetHandler)
    {
        this.packetHandler = packetHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.net.IClientConnection#getPacketHandler()
     */
    @Override
    public IClientPacketHandler getPacketHandler()
    {
        return packetHandler;
    }

    @Override
    public IConnectionHolder getHolder()
    {
        return holder;
    }

    @Override
    public void setHolder(IConnectionHolder holder)
    {
        this.holder = holder;
        this.holder.setClientConnection(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.net.IClientConnection#onDisconnect()
     */
    @Override
    public void onDisconnect()
    {
        holder.onDisconnect();
    }
}
