/**
 * Date: 2013-5-31
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.module.inf;

import java.util.List;

import com.road.dota.object.bag.PlayerBag;
import com.road.pitaya.net.CommonMessage;

/**
 * 人物消息模块(用来给客户端发消息)
 * @author Cookie.hu
 */
public interface IMessageModule
{
    /**
     * 给客户端发送消息
     * @param message
     */
    void sendTCP(CommonMessage message);
    
    /**
     * 发送背包更新的消息
     * @param itemBag
     * @param changedPlaces
     */
    void sendUpdateBag(PlayerBag itemBag, List<Integer> changedPlaces);
}
