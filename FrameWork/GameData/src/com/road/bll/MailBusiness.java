package com.road.bll;

import com.road.entity.info.MailInfo;

/**
 * 
 * @author yong.xu
 * @date 2013-5-28
 * @version 
 *
 */
public class MailBusiness {

	/**
	 * 通过邮件编号，获得邮件信息
	 * @param mailID
	 * @return
	 */
	public static MailInfo getMailInfo(int mailID)
	{
		return DaoManager.getMailInfoDao().getByKey(mailID);
	}
	
	/**
	 * 添加一个邮件到DB
	 * @param mailInfo
	 * @return
	 */
    public static boolean addMailInfo(MailInfo mailInfo)
    {
        if (mailInfo == null)
            return false;
        return DaoManager.getMailInfoDao().add(mailInfo);
    }
	
	/**
	 * 更新邮件
	 * @param playerInfo
	 * @return
	 */
	public static boolean updateMailInfo(MailInfo mailInfo)
	{
		return DaoManager.getMailInfoDao().update(mailInfo);
	}
	 
}
