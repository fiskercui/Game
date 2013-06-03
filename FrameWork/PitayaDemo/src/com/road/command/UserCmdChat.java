package com.road.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import com.road.dota.object.inf.IGamePlayer;
import com.road.pb.ChatMsgProto.ChatMsg;
import com.road.pitaya.chat.ChannelManager;
import com.road.pitaya.command.AbstractUserCmd;
import com.road.pitaya.command.ICode;
import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;

@ICode(code = Protocol.AS3_CHAT, desc = "聊天")
public class UserCmdChat extends AbstractUserCmd
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCmdChat.class);

    @Override
    public void execute(IGamePlayer player, CommonMessage packet)
    {
        System.out.println("HelloChat\n");

        try
        {
            ChatMsg msg = ChatMsg.parseFrom(packet.getBytes());
            ChatMsg.Builder builder = ChatMsg.newBuilder();

            if (ChannelManager.getInstance().isPublicChannel( (byte) msg.getChannelType()))
            {
                System.out.println(msg.getChannelType());
                System.out.println(msg.getSendName());
                System.out.println(msg.getMessage());

                builder.setChannelType(msg.getChannelType());
                builder.setSendName(msg.getSendName());
                builder.setMessage(msg.getMessage());
                List<Integer> userList= ChannelManager.getInstance().getChannelUserList(player.getUserID(), (byte) msg.getChannelType(),
                        player.getChannelID((byte) msg.getChannelType()));
                builder.addAllRecvIdList(userList);
                
                CommonMessage message = new CommonMessage(Protocol.AS3_CHAT);
                message.setBytes(builder.build().toByteArray());
                
                ChannelManager.getInstance().onDispatchMessage(player.getPlayerInfo().getUserID(), userList, msg);

            }
            else if (ChannelManager.getInstance().isPrivateChannel((byte) msg.getChannelType()))
            {
                System.out.println(msg.getChannelType());
                System.out.println(msg.getSendName());
                System.out.println(msg.getRecvId());
                System.out.println(msg.getMessage());
                
                
                builder.setChannelType(msg.getChannelType());
                builder.setSendName(msg.getSendName());
                builder.setRecvId(msg.getRecvId());
                builder.setMessage(msg.getMessage());
                CommonMessage replyMessage = new CommonMessage(Protocol.AS3_CHAT);
                replyMessage.setBytes(builder.build().toByteArray());
                ChannelManager.getInstance().onDispatchMessage(player.getPlayerInfo().getUserID(), msg.getRecvId(),replyMessage);

            }
            else
            {
                LOGGER.error("unknown channel Type" + msg.getChannelType());
            }

            // armyMsgWrap.fromByteArray(packet.getBytes());
            // System.out.println(armyMsgWrap.getId());
            // System.out.println(armyMsgWrap.getUserId());
            // System.out.println(armyMsgWrap.getType());
            // System.out.println(armyMsgWrap.getUserName());
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

    }

    /*
     * 显示
     */
    @Override
    public void executeConnect(IClientConnection conn, CommonMessage packet)
    {
        System.out.println("HelloChat\n");

        // try
        // {
        // // ArmyMsgProtoWrap armyMsgWrap = new ArmyMsgProtoWrap(packet.getBytes());
        // // armyMsgWrap.fromByteArray(packet.getBytes());
        // // System.out.println(armyMsgWrap.getId());
        // // System.out.println(armyMsgWrap.getUserId());
        // // System.out.println(armyMsgWrap.getType());
        // // System.out.println(armyMsgWrap.getUserName());
        // }
        // catch (InvalidProtocolBufferException e)
        // {
        // e.printStackTrace();
        // }
    }
}
