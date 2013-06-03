/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.entity.info;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.road.entity.DataObject;
import com.road.util.TimeUtil;

/**
 * 邮件对象
 * 
 * @author yong.xu
 */
public class MailInfo extends DataObject implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 邮件ID
     */
    private int mailID;

    /**
     * 发送时间
     */
    private Timestamp sendDate;

    /**
     * 阅读时间，NULL为未读
     */
    private Timestamp readDate;

    /**
     * 发送人用户ID
     */
    private int senderUserID;

    /**
     * 发送人昵称
     */
    private String senderNickname;

    /**
     * 接收人用户ID
     */
    private int receiverUserID;

    /**
     * 接收人昵称
     */
    private String receiverNickname;
    
    /**
     * 类型
     */
    private int type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 邮件是否已删除
     */
    private boolean isExist;

    /**
     * 邮件有效期，小时为单位
     */
    private int validPeriod;

    /**
     * 金币
     */
    private int gold;

    /**
     * 是否提取金币
     */
    private boolean isGoldGet;

    /**
     * 点卷
     */
    private int money;

    /**
     * 是否提取点卷
     */
    private boolean isMoneyGet;

    /**
     * 附件1编号
     */
    private int annex1;

    /**
     * 附件一名称
     */
    private String annex1Name;

    /**
     * 是否提取附件
     */
    private boolean isAnnex1Get;

    /**
     * 附件2编号
     */
    private int annex2;

    /**
     * 附件二名称
     */
    private String annex2Name;

    /**
     * 是否提取附件
     */
    private boolean isAnnex2Get;

    /**
     * 附件三编号
     */
    private int annex3;

    /**
     * 附件三名称
     */
    private String annex3Name;

    /**
     * 是否提取附件
     */
    private boolean isAnnex3Get;

    /**
     * 附件四编号
     */
    private int annex4;

    /**
     * 附件四名称
     */
    private String annex4Name;

    /**
     * 是否提取附件
     */
    private boolean isAnnex4Get;

    /**
     * 附件五编号
     */
    private int annex5;

    /**
     * 附件五名称
     */
    private String annex5Name;

    /**
     * 是否提取附件
     */
    private boolean isAnnex5Get;

    /**
     * 附件汇总说明
     */
    private String annexRemark;

    /**
     * 邮件ID
     */
    public int getMailID()
    {
        return mailID;
    }

    /**
     * 邮件ID
     */
    public void setMailID(int mailID)
    {
        this.mailID = mailID;
    }

    /**
     * 发送时间
     */
    public Date getSendDate()
    {
        return sendDate;
    }

    /**
     * 发送时间
     */
    public void setSendDate(Timestamp sendDate)
    {
        this.sendDate = sendDate;
    }

    /**
     * 阅读时间，NULL为未读
     */
    public Date getReadDate()
    {
        return readDate;
    }

    /**
     * 阅读时间，NULL为未读
     */
    public void setReadDate(Timestamp readDate)
    {
        if (readDate != null && !readDate.equals(this.readDate))
        {
            this.readDate = readDate;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 发送人用户ID
     */
    public int getSenderUserID()
    {
        return senderUserID;
    }

    /**
     * 发送人用户ID
     */
    public void setSenderUserID(int senderUserID)
    {
        this.senderUserID = senderUserID;
    }

    /**
     * 发送人昵称
     */
    public String getSenderNickname()
    {
        return senderNickname;
    }

    /**
     * 发送人昵称
     */
    public void setSenderNickname(String senderNickname)
    {
        this.senderNickname = senderNickname;
    }

    /**
     * 接收人用户ID
     */
    public int getReceiverUserID()
    {
        return receiverUserID;
    }

    /**
     * 接收人用户ID
     */
    public void setReceiverUserID(int receiverUserID)
    {
        this.receiverUserID = receiverUserID;
    }

    /**
     * 接收人昵称
     */
    public String getReceiverNickname()
    {
        return receiverNickname;
    }

    /**
     * 接收人昵称
     */
    public void setReceiverNickname(String receiverNickname)
    {
        this.receiverNickname = receiverNickname;
    }

    /**
     * 类型
     */
    public int getType()
    {
        return type;
    }

    /**
     * 类型
     */
    public void setType(int type)
    {
        this.type = type;
    }
    
    /**
     * 标题
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * 内容
     */
    public String getContent()
    {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * 邮件是否已删除
     */
    public boolean getIsExist()
    {
        return isExist;
    }

    /**
     * 邮件是否已删除
     */
    public void setIsExist(boolean isExist)
    {
        if (isExist != this.isExist)
        {
            this.isExist = isExist;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 邮件有效期，小时为单位
     */
    public int getValidPeriod()
    {
        return validPeriod;
    }

    /**
     * 邮件有效期，小时为单位
     */
    public void setValidPeriod(int validPeriod)
    {
        this.validPeriod = validPeriod;
    }

    /**
     * 金币
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * 金币
     */
    public void setGold(int gold)
    {
        this.gold = gold;
    }

    /**
     * 是否提取金币
     */
    public boolean getIsGoldGet()
    {
        return isGoldGet;
    }

    /**
     * 是否提取金币
     */
    public void setIsGoldGet(boolean isGoldGet)
    {
        if (isGoldGet != this.isGoldGet)
        {
            this.isGoldGet = isGoldGet;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 点卷
     */
    public int getMoney()
    {
        return money;
    }

    /**
     * 点卷
     */
    public void setMoney(int money)
    {
        this.money = money;
    }

    /**
     * 是否提取点卷
     */
    public boolean getIsMoneyGet()
    {
        return isMoneyGet;
    }

    /**
     * 是否提取点卷
     */
    public void setIsMoneyGet(boolean isMoneyGet)
    {
        if (isMoneyGet != this.isMoneyGet)
        {
            this.isMoneyGet = isMoneyGet;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 附件1编号
     */
    public int getAnnex1()
    {
        return annex1;
    }

    /**
     * 附件1编号
     */
    public void setAnnex1(int annex1)
    {
        this.annex1 = annex1;
    }

    /**
     * 附件一名称
     */
    public String getAnnex1Name()
    {
        return annex1Name;
    }

    /**
     * 附件一名称
     */
    public void setAnnex1Name(String annex1Name)
    {
        this.annex1Name = annex1Name;
    }

    /**
     * 是否提取附件
     */
    public boolean getIsAnnex1Get()
    {
        return isAnnex1Get;
    }

    /**
     * 是否提取附件
     */
    public void setIsAnnex1Get(boolean isAnnex1Get)
    {
        if (isAnnex1Get != this.isAnnex1Get)
        {
            this.isAnnex1Get = isAnnex1Get;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 附件2编号
     */
    public int getAnnex2()
    {
        return annex2;
    }

    /**
     * 附件2编号
     */
    public void setAnnex2(int annex2)
    {
        this.annex2 = annex2;
    }

    /**
     * 附件二名称
     */
    public String getAnnex2Name()
    {
        return annex2Name;
    }

    /**
     * 附件二名称
     */
    public void setAnnex2Name(String annex2Name)
    {
        this.annex2Name = annex2Name;
    }

    /**
     * 是否提取附件
     */
    public boolean getIsAnnex2Get()
    {
        return isAnnex2Get;
    }

    /**
     * 是否提取附件
     */
    public void setIsAnnex2Get(boolean isAnnex2Get)
    {
        if (isAnnex2Get != this.isAnnex2Get)
        {
            this.isAnnex2Get = isAnnex2Get;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 附件三编号
     */
    public int getAnnex3()
    {
        return annex3;
    }

    /**
     * 附件三编号
     */
    public void setAnnex3(int annex3)
    {
        this.annex3 = annex3;
    }

    /**
     * 附件三名称
     */
    public String getAnnex3Name()
    {
        return annex3Name;
    }

    /**
     * 附件三名称
     */
    public void setAnnex3Name(String annex3Name)
    {
        this.annex3Name = annex3Name;
    }

    /**
     * 是否提取附件
     */
    public boolean getIsAnnex3Get()
    {
        return isAnnex3Get;
    }

    /**
     * 是否提取附件
     */
    public void setIsAnnex3Get(boolean isAnnex3Get)
    {
        if (isAnnex3Get != this.isAnnex3Get)
        {
            this.isAnnex3Get = isAnnex3Get;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 附件四编号
     */
    public int getAnnex4()
    {
        return annex4;
    }

    /**
     * 附件四编号
     */
    public void setAnnex4(int annex4)
    {
        this.annex4 = annex4;
    }

    /**
     * 附件四名称
     */
    public String getAnnex4Name()
    {
        return annex4Name;
    }

    /**
     * 附件四名称
     */
    public void setAnnex4Name(String annex4Name)
    {
        this.annex4Name = annex4Name;
    }

    /**
     * 是否提取附件
     */
    public boolean getIsAnnex4Get()
    {
        return isAnnex4Get;
    }

    /**
     * 是否提取附件
     */
    public void setIsAnnex4Get(boolean isAnnex4Get)
    {
        if (isAnnex4Get != this.isAnnex4Get)
        {
            this.isAnnex4Get = isAnnex4Get;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 附件五编号
     */
    public int getAnnex5()
    {
        return annex5;
    }

    /**
     * 附件五编号
     */
    public void setAnnex5(int annex5)
    {
        this.annex5 = annex5;
    }

    /**
     * 附件五名称
     */
    public String getAnnex5Name()
    {
        return annex5Name;
    }

    /**
     * 附件五名称
     */
    public void setAnnex5Name(String annex5Name)
    {
        this.annex5Name = annex5Name;
    }

    /**
     * 是否提取附件
     */
    public boolean getIsAnnex5Get()
    {
        return isAnnex5Get;
    }

    /**
     * 是否提取附件
     */
    public void setIsAnnex5Get(boolean isAnnex5Get)
    {
        if (isAnnex5Get != this.isAnnex5Get)
        {
            this.isAnnex5Get = isAnnex5Get;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 附件汇总说明
     */
    public String getAnnexRemark()
    {
        return annexRemark;
    }

    /**
     * 附件汇总说明
     */
    public void setAnnexRemark(String annexRemark)
    {
        this.annexRemark = annexRemark;
    }

    /**
     * 默认构造函数
     */
    public MailInfo()
    {
        this.sendDate = TimeUtil.getSysteCurTime();
        this.isExist = true;
        this.validPeriod = 72;
    }
    
    @Override
    public String toString()
    {
        String message = "[\nmailID:" + getMailID() + ",sendDate:"
                + getSendDate() + ",readDate:" + getReadDate()
                + ",senderUserID:" + getSenderUserID() + ",senderNickname:"
                + getSenderNickname() + ",receiverUserID:"
                + getReceiverUserID() + ",receiverNickname:"
                + getReceiverNickname() + ",title:" + getTitle() + ",content:"
                + getContent() + ",isExist:" + getIsExist() + ",validPeriod:"
                + getValidPeriod() + ",gold:" + getGold() + ",isGoldGet:"
                + getIsGoldGet() + ",money:" + getMoney() + ",isMoneyGet:"
                + getIsMoneyGet() + ",annex1:" + getAnnex1() + ",annex1Name:"
                + getAnnex1Name() + ",isAnnex1Get:" + getIsAnnex1Get()
                + ",annex2:" + getAnnex2() + ",annex2Name:" + getAnnex2Name()
                + ",isAnnex2Get:" + getIsAnnex2Get() + ",annex3:" + getAnnex3()
                + ",annex3Name:" + getAnnex3Name() + ",isAnnex3Get:"
                + getIsAnnex3Get() + ",annex4:" + getAnnex4() + ",annex4Name:"
                + getAnnex4Name() + ",isAnnex4Get:" + getIsAnnex4Get()
                + ",annex5:" + getAnnex5() + ",annex5Name:" + getAnnex5Name()
                + ",isAnnex5Get:" + getIsAnnex5Get() + ",annexRemark:"
                + getAnnexRemark() + "\n]";
        return message;
    }
}
