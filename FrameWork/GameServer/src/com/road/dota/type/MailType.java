package com.road.dota.type;

public interface MailType {
	
    /**
	 * 默认
	 */
	public static final short DEFAULT = 0;
	
	 /**
     * 系统邮件
     */
    public static final short SYSTEM = 1;
    
    /**
     * 用户邮件
     */
    public static final short USER = 2;
    
    /**
     * 公会邮件
     */
    public static final short GUILD = 3;
    
    
    
	/**
	 *  普通邮件
	 */
	public static final short COMMON = 1;
	/**
	 *  拍卖成功
	 */
	public static final short AUCTIONSUCCESS = 2;
	/**
	 *  拍卖失败
	 */
	public static final short AUCTIONFAIL = 3;
	/**
	 *  竞标成功
	 */
	public static final short BIDSUCCESS = 4;
	/**
	 *  竞标失败
	 */
	public static final short BIDFAIL = 5;
	/**
	 *  返回付款
	 */
	public static final short RETURNPAYMENT = 6;
	/**
	 *  付款退回
	 */
	public static final short PAYMENTCANCEL = 7;
	/**
	 *  购买物品
	 */
	public static final short BUYITEM = 8;
	/**
	 *  物品过期
	 */
	public static final short ITEMOVERDUE = 9;
	/**
	 *  赠送物品
	 */
	public static final short PRESENTITEM = 10;
	/**
	 *  赠送礼品
	 */
	public static final short PRESENTITEMGIFT = 10;

	/**
	 * 付款
	 */
	public static final short PAYMENTFINISH = 11;
	/**
	 *  打开物品
	 */
	public static final short OPENUPARK = 12;
	/**
	 *  公会退回
	 */
	public static final short STORECANEL = 13;
	/**
	 *  结婚
	 */
	public static final short MARRY = 14;
	/**
	 *  日常奖励
	 */
	public static final short DAILYAWARD = 15;

	/**
	 *  抽奖
	 */
	public static final short LOTTERY = 16;

	/**
	 *  后台管理
	 */
	public static final short MANAGE = 51;
	/**
	 *  活动
	 */
	public static final short ACTIVE = 52;

	/**
	 *  付款邮件
	 */
	public static final short PAYMENT = 101;

	/**
	 *  时间宝箱
	 */
	public static final short TIMEBOX = 102;

	/**
	 *  VIP提示
	 */
	public static final short VIPNOTICE = 53;

	/**
	 * 师徒系统邮件
	 */
	public static final short APPMAIL = 54;

	/**
	 * 邀请人
	 */
	public static final short INVITER = 54;

	/**
	 * 礼品盒引导邮件
	 */
	public static final short GIFTGUIDE = 55;

	/**
	 * 作战实验室奖励
	 */
	public static final short FIGHTLABAWARD = 56;

	/**
	 *  生日提醒邮件
	 */
	public static final short BIRTHDAYNOTICE = 57;
	
	public static final short GIFT = 200;
}
