package com.road.command;

import com.google.protobuf.InvalidProtocolBufferException;
import com.road.GamePlayer;
import com.road.dota.object.inf.IGamePlayer;
import com.road.pb.wrap.UserMsgProtoWrap;
import com.road.pitaya.chat.ChatUserMgr;
import com.road.pitaya.command.AbstractUserCmd;
import com.road.pitaya.command.GamePlayerMgr;
import com.road.pitaya.command.ICode;
import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;


@ICode(code = Protocol.AS3_LOGIN, desc= "登陆")
public class UserLogin extends AbstractUserCmd
{

    @Override
    public void execute(IGamePlayer player, CommonMessage packet)
    {

    }

   
    @Override
    public void executeConnect(IClientConnection conn, CommonMessage packet)
    {
        System.out.println("HelloWorld\n");
        try
        {
            UserMsgProtoWrap userMsgWrap = UserMsgProtoWrap.readData(packet.getBytes());
            System.out.println(userMsgWrap.getUserId());
            System.out.println(userMsgWrap.getName());
            
            IGamePlayer player = new GamePlayer(userMsgWrap.getUserId(), userMsgWrap.getName());
            player.setClientConnection(conn);
            conn.setHolder(player);
            GamePlayerMgr.getInstance().addGamePlayer(player.getUserID(), player);
            
            ChatUserMgr.getInstance().addUser(player.getUserID(), player);
            
            UserMsgProtoWrap.Builder msg = UserMsgProtoWrap.newBuilder();
            msg.setName("weihua");
            msg.setUserId(1);
            CommonMessage cmsg = new CommonMessage((short)0x016);
            cmsg.setBytes(UserMsgProtoWrap.writeData(msg));
            conn.send(cmsg);

        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

    }

}