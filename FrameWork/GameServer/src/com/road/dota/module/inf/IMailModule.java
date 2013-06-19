/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.module.inf;

import java.util.List;

import com.road.entity.info.PlayerInfo;

/**
 * @author yong.xu
 * @date 2013-5-23
 * @version 
 *
 */
public interface IMailModule
{
    /**
     * 发送用户邮件
     * @param receiver    接受者 
     * @param title       标题
     * @param content     内容
     */
    void sendUserMail(PlayerInfo receiver, String title, String content);
    
    /**
     * 发送公会邮件
     * @param receiverList  接受者列表
     * @param title         标题
     * @param content       内容
     */
    void sendGuildMail(List<PlayerInfo> receiverList, String title, String content);
}
