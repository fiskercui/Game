/**
 * Date: 2013-5-31
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.module;

import java.util.List;

import com.road.dota.command.ProtocolOut;
import com.road.dota.module.inf.IMessageModule;
import com.road.dota.object.bag.BaseItem;
import com.road.dota.object.bag.PlayerBag;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.pb.BagUpdateMsgProto.BagUpdateMsg;
import com.road.dota.pb.BagUpdateMsgProto.ItemMsg;
import com.road.dota.pb.BagUpdateMsgProto.UpdateMsg;
import com.road.dota.type.ModuleType;
import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;

/**
 * 人物消息模块
 * 
 * @author Cookie.hu
 */
public class MessageModule extends PlayerModule implements IMessageModule
{
    IClientConnection connection;

    /**
     * @param player
     * @param moduleType
     */
    public MessageModule(IGamePlayer player, ModuleType moduleType)
    {
        super(player, ModuleType.MESSAGE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.IModule#init()
     */
    @Override
    public boolean init()
    {
        this.connection = player.getClientConnection();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.IModule#loadFromDB()
     */
    @Override
    public boolean loadFromDB()
    {
        // TODO Auto-generated method stub
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.IModule#saveIntoDB()
     */
    @Override
    public boolean saveIntoDB()
    {
        // TODO Auto-generated method stub
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.IMessageModule#sendTCP(com.road.pitaya.net.
     * CommonMessage)
     */
    @Override
    public void sendTCP(CommonMessage message)
    {
        connection.send(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.road.dota.module.inf.IMessageModule#sendUpdateBag(com.road.dota.object
     * .bag.PlayerBag, java.util.List)
     */
    @Override
    public void sendUpdateBag(PlayerBag itemBag, List<Integer> changedPlaces)
    {
        CommonMessage updateBagMsg = new CommonMessage(
                ProtocolOut.USER_UPDATE_BAG);
        BagUpdateMsg.Builder msgBuilder = BagUpdateMsg.newBuilder();
        for (int i = 0; i < changedPlaces.size(); i++)
        {
            UpdateMsg.Builder upBuilder = UpdateMsg.newBuilder();
            BaseItem baseItem = itemBag.getItemAt(i);
            upBuilder.setSlot(i);
            upBuilder.setType(itemBag.getType().getValue());
            if (baseItem == null)
            {
                upBuilder.setIsEmpty(true);
            }
            else
            {
                ItemMsg.Builder itemBuilder = ItemMsg.newBuilder();
                itemBuilder.setBeanId(baseItem.getItemBean().getBeanId());
                itemBuilder.setCount(baseItem.getItemInfo().getCount());
                itemBuilder.setIsBind(baseItem.getItemInfo().isBind());
                upBuilder.setIsEmpty(false);
                upBuilder.setItemMsg(itemBuilder);
            }
            msgBuilder.addUpdateMsg(upBuilder);
        }
        updateBagMsg.setBytes(msgBuilder.build().toByteArray());
        sendTCP(updateBagMsg);
    }
}
