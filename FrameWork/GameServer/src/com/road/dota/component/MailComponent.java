/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.component;

import java.util.List;

import com.road.bll.MailBusiness;
import com.road.dota.component.inf.IMailComponent;
import com.road.dota.type.MailType;
import com.road.entity.info.MailInfo;
import com.road.entity.info.PlayerInfo;

/**
 * @author cookie.hu
 * @date 2013-5-21
 * @version 
 *
 */
public class MailComponent implements IMailComponent
{

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean initialize()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean start()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void sendSystemMail(List<PlayerInfo> receiverList, MailInfo mailInfo)
    {
        mailInfo.setSenderUserID(0);
        mailInfo.setSenderNickname("系统管理员");
        mailInfo.setType(MailType.SYSTEM);
        mailInfo.setIsExist(true);
        for(PlayerInfo receiver : receiverList){
            mailInfo.setReceiverUserID(receiver.getUserID());
            mailInfo.setReceiverNickname(receiver.getNickname());
            MailBusiness.addMailInfo(mailInfo);
        }
    }
  
}
