/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.component.inf;

import java.util.List;

import com.road.entity.info.MailInfo;
import com.road.entity.info.PlayerInfo;
import com.road.pitaya.component.IComponent;

/**
 * @author cookie.hu
 * @date 2013-5-21
 * @version 
 * 邮件组件
 */
public interface IMailComponent extends IComponent
{
    /**
     * 发送系统邮件
     * @param receiverList
     * @param mailInfo（title,content,validPeriod,gold,money,moneyGet,annex）
     */
    void sendSystemMail(List<PlayerInfo> receiverList, MailInfo mailInfo);
}
