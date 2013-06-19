/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.dota.module;

import java.util.List;

import com.road.bll.MailBusiness;
import com.road.dota.module.inf.IMailModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.type.MailType;
import com.road.dota.type.ModuleType;
import com.road.entity.info.MailInfo;
import com.road.entity.info.PlayerInfo;

/**
 * @author yong.xu
 * @date 2013-5-27
 * @version 邮件模块
 */
public class MailModule extends PlayerModule implements IMailModule
{
    /**
     * @param player
     */
    public MailModule(IGamePlayer player)
    {
        super(player,ModuleType.MAIL);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean init()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean loadFromDB()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean saveIntoDB()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ModuleType getType()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendUserMail(PlayerInfo receiver, String title, String content)
    {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setSenderUserID(player.getUserID());
        mailInfo.setSenderNickname(player.getPlayerInfo().getNickname());
        mailInfo.setReceiverUserID(receiver.getUserID());
        mailInfo.setReceiverNickname(receiver.getNickname());
        mailInfo.setType(MailType.USER);
        mailInfo.setTitle(title);
        mailInfo.setContent(content);
        mailInfo.setIsExist(true);
        MailBusiness.addMailInfo(mailInfo);
    }

    @Override
    public void sendGuildMail(List<PlayerInfo> receiverList, String title,
            String content)
    {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setSenderUserID(player.getUserID());
        mailInfo.setSenderNickname(player.getPlayerInfo().getNickname());
        mailInfo.setType(MailType.USER);
        mailInfo.setTitle(title);
        mailInfo.setContent(content);
        mailInfo.setIsExist(true);
        for(PlayerInfo receiver : receiverList){
            mailInfo.setReceiverUserID(receiver.getUserID());
            mailInfo.setReceiverNickname(receiver.getNickname());
            MailBusiness.addMailInfo(mailInfo);
        }
    }

}
