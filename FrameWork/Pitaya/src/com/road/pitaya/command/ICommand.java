package com.road.pitaya.command;

import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;

/**
 * 
 * @author weihua.cui
 * 
 */
public interface ICommand
{    
    public abstract void execute(IClientConnection session, CommonMessage packet) throws Exception;
}
