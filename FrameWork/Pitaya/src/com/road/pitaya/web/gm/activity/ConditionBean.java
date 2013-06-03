/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import com.google.gson.annotations.SerializedName;

/**
 *  礼包对应的条件
 * @author yip
 * @date 2013-5-22
 * @version
 */
public class ConditionBean
{
    /**
     * giftbagId String 必填 礼包Id（UUID）
     */
    @SerializedName("giftbagId")
    private String giftbagId;
    /**
     * conditionIndex Integer 必填 表示第n个参数的序号
     */
    @SerializedName("conditionIndex")
    private Integer conditionIndex;
    /**
     * conditionValue Long 必填 表示第n个参数值
     */
    @SerializedName("conditionValue")
    private Long conditionValue;
    /**
     * remain1 Integer 可选 以后可用于是否可选或者必须条件
     */
    @SerializedName("remain1")
    private Integer remain1;

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
     * @return the conditionIndex
     */
    public Integer getConditionIndex()
    {
        return conditionIndex;
    }

    /**
     * @param conditionIndex
     *            the conditionIndex to set
     */
    public void setConditionIndex(Integer conditionIndex)
    {
        this.conditionIndex = conditionIndex;
    }

    /**
     * @return the conditionValue
     */
    public Long getConditionValue()
    {
        return conditionValue;
    }

    /**
     * @param conditionValue
     *            the conditionValue to set
     */
    public void setConditionValue(Long conditionValue)
    {
        this.conditionValue = conditionValue;
    }

    /**
     * @return the remain1
     */
    public Integer getRemain1()
    {
        return remain1;
    }

    /**
     * @param remain1
     *            the remain1 to set
     */
    public void setRemain1(Integer remain1)
    {
        this.remain1 = remain1;
    }

}
