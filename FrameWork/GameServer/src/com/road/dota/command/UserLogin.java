package com.road.dota.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.pb.LoginReqMsgProto.LoginReqMsg;
import com.road.pitaya.command.AbstractUserCmd;
import com.road.pitaya.command.ICode;
import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;


@ICode(code = ProtocolIn.USER_LOGIN, desc= "登陆")
public class UserLogin extends AbstractUserCmd
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserCmd.class);

    @Override
    public void execute(IGamePlayer player, CommonMessage packet)
    {

    }

   
    @Override
    public void executeConnect(IClientConnection conn, CommonMessage packet)
    {
        System.out.println("HelloWorld\n");

        
        
        boolean result = false;
        try {           
            //反向解析
            LoginReqMsg loginReq = LoginReqMsg.parseFrom(packet.toByteArray());
            int userId = loginReq.getPlayerId();
            String key = loginReq.getKey();
            List<Integer> temps = loginReq.getEkeysList();
            int[] ekeys = new int[8];
            int[] dkeys = new int[8];
            if(temps.size() != 8) {
                return;
            }
            for(int i = 0; i < 8; i++) {
                int temp = temps.get(i);
                ekeys[i] = temp;
                dkeys[i] = temp;
            }
            conn.setEncryptionKey(ekeys);
            conn.setDecryptionKey(dkeys);
            
            
            //发送 正向序列化
            LoginReqMsg.Builder msgBuilder = LoginReqMsg.newBuilder();
            msgBuilder.setPlayerId(111);
            msgBuilder.setKey("123");
            msgBuilder.addAllEkeys(temps);
            CommonMessage message = new CommonMessage(ProtocolOut.USER_LOGIN);
            message.setBytes(msgBuilder.build().toByteArray());
            conn.send(message);
 
        } catch (Exception e) {
            LOGGER.error("UserLoginCmd", e);
        } finally {
            //没有通过的断开连接
            if (result == false) {
                conn.onDisconnect();
            }
        }

    }

}