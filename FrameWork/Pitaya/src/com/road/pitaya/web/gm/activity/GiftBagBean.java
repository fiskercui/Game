/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import com.google.gson.annotations.SerializedName;

/**
 * 礼包实体
 * @author yip
 * @date 2013-5-22
 * @version
 * 
 */
public class GiftBagBean
{
    /**
     * 必填 礼包ID(UUID)
     */
    @SerializedName("giftbagId")
    private String giftbagId;
    /**
     * 必填 活动ID(UUID)
     */
    @SerializedName("activityId")
    private String activityId;
    /**
     * 可选 用于激活码活动默认为0
     */
    @SerializedName("rewardMark")
    private Integer rewardMark;
    /**
     * 可选 礼包排序
     */
    @SerializedName("giftbagOrder")
    private Integer giftbagOrder;

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
     * @return the activityId
     */
    public String getActivityId()
    {
        return activityId;
    }

    /**
     * @param activityId
     *            the activityId to set
     */
    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    /**
     * @return the rewardMark
     */
    public Integer getRewardMark()
    {
        return rewardMark;
    }

    /**
     * @param rewardMark
     *            the rewardMark to set
     */
    public void setRewardMark(Integer rewardMark)
    {
        this.rewardMark = rewardMark;
    }

    /**
     * @return the giftbagOrder
     */
    public Integer getGiftbagOrder()
    {
        return giftbagOrder;
    }

    /**
     * @param giftbagOrder
     *            the giftbagOrder to set
     */
    public void setGiftbagOrder(Integer giftbagOrder)
    {
        this.giftbagOrder = giftbagOrder;
    }

}
