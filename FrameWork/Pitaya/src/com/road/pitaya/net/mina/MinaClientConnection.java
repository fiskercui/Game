/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.net.mina;

import org.apache.mina.core.session.IoSession;

import com.road.pitaya.net.AbstractClientConnection;
import com.road.pitaya.net.IClientPacketHandler;
import com.road.pitaya.type.CommonConst;

/**
 * 
 * @author jiayi.wei
 */
public class MinaClientConnection extends AbstractClientConnection
{

    private IoSession session = null;
    /**
     * @param packetHandler
     */
    public MinaClientConnection(IClientPacketHandler packetHandler, IoSession session)
    {
        super(packetHandler);
        this.session = session;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.net.IClientConnection#send(java.lang.Object)
     */
    @Override
    public void send(Object packet)
    {
        if (session != null && session.isClosing() == false)
        {
            session.write(packet);
        }
    }

    @Override
    public void setEncryptionKey(int[] key)
    {
        session.setAttribute(CommonConst.ENCRYPTION_KEY, key);        
    }

    @Override
    public void setDecryptionKey(int[] key)
    {
        session.setAttribute(CommonConst.DECRYPTION_KEY, key);
    }
}
