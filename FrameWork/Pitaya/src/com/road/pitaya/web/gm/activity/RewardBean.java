/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import com.google.gson.annotations.SerializedName;

/**
 *  奖励实体
 * @author yip
 * @date 2013-5-22
 * @version
 * 
 */
public class RewardBean
{
    /**
     * String 必填 礼包ID
     */
    @SerializedName("giftbagId")
    private String giftbagId;
    /**
     * 必填 物品ID
     */
    @SerializedName("templateId")
    private Integer templateId;
    /**
     * 必填 数量
     */
    @SerializedName("count")
    private long count;
    /**
     * 是否绑定
     */
    @SerializedName("isBind")
    private Integer isBind;
    /**
     * 必填 职业/性别
     */
    @SerializedName("occupationOrSex")
    private Integer occupationOrSex;
    /**
     * 可选 物品类别 例如：物品、星运、装备、道具
     */
    @SerializedName("rewardType")
    private Integer rewardType;
    /**
     * 可选 有效期
     */
    @SerializedName("validDate")
    private Integer validDate;
    /**
     * 可选 格式：1,2,3,4,5,6 (以逗号相隔，如需增加属性再在逗号后加) 表示：等级,攻击,防御,敏捷,幸运
     */
    @SerializedName("property")
    private String property;
    /**
     * 可选 预留字段1
     */
    @SerializedName("remain1")
    private String remain1;

    /**
     * @return the giftbagId
     */
    public String getGiftbagId()
    {
        return giftbagId;
    }

    /**
     * @param giftbagId
     *            the giftbagId to set
     */
    public void setGiftbagId(String giftbagId)
    {
        this.giftbagId = giftbagId;
    }

    /**
     * @return the templateId
     */
    public Integer getTemplateId()
    {
        return templateId;
    }

    /**
     * @param templateId
     *            the templateId to set
     */
    public void setTemplateId(Integer templateId)
    {
        this.templateId = templateId;
    }

    /**
     * @return the count
     */
    public long getCount()
    {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(long count)
    {
        this.count = count;
    }

    /**
     * @return the isBind
     */
    public Integer getIsBind()
    {
        return isBind;
    }

    /**
     * @param isBind
     *            the isBind to set
     */
    public void setIsBind(Integer isBind)
    {
        this.isBind = isBind;
    }

    /**
     * @return the occupationOrSex
     */
    public Integer getOccupationOrSex()
    {
        return occupationOrSex;
    }

    /**
     * @param occupationOrSex
     *            the occupationOrSex to set
     */
    public void setOccupationOrSex(Integer occupationOrSex)
    {
        this.occupationOrSex = occupationOrSex;
    }

    /**
     * @return the rewardType
     */
    public Integer getRewardType()
    {
        return rewardType;
    }

    /**
     * @param rewardType
     *            the rewardType to set
     */
    public void setRewardType(Integer rewardType)
    {
        this.rewardType = rewardType;
    }

    /**
     * @return the validDate
     */
    public Integer getValidDate()
    {
        return validDate;
    }

    /**
     * @param validDate
     *            the validDate to set
     */
    public void setValidDate(Integer validDate)
    {
        this.validDate = validDate;
    }

    /**
     * @return the property
     */
    public String getProperty()
    {
        return property;
    }

    /**
     * @param property
     *            the property to set
     */
    public void setProperty(String property)
    {
        this.property = property;
    }

    /**
     * @return the remain1
     */
    public String getRemain1()
    {
        return remain1;
    }

    /**
     * @param remain1
     *            the remain1 to set
     */
    public void setRemain1(String remain1)
    {
        this.remain1 = remain1;
    }

}
