package com.road.command;


import com.google.protobuf.InvalidProtocolBufferException;

import com.road.dota.object.inf.IGamePlayer;
import com.road.pb.wrap.ArmyMsgProtoWrap;
import com.road.pitaya.command.AbstractUserCmd;
import com.road.pitaya.command.ICode;
import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;



@ICode(code = Protocol.AS3_TEST, desc= "测试")
public class UserCmdTest extends AbstractUserCmd
{

    @Override
    public void execute( IGamePlayer player, CommonMessage packet)
    {

        
    }
   
    @Override
    public void executeConnect(IClientConnection conn, CommonMessage packet)
    {
        System.out.println("HelloWorld\n");
        
        try
        {
            ArmyMsgProtoWrap armyMsgWrap = new ArmyMsgProtoWrap(packet.getBytes());
            armyMsgWrap.fromByteArray(packet.getBytes());
            System.out.println(armyMsgWrap.getId());
            System.out.println(armyMsgWrap.getUserId());
            System.out.println(armyMsgWrap.getType());
            System.out.println(armyMsgWrap.getUserName());
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }
    }

}
