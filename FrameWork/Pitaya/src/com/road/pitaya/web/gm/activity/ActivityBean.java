/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import java.util.List;

import com.google.gson.annotations.SerializedName;


/**
 *  活动实体
 * @author yip
 * @date 2013-5-22
 * @version
 */

public class ActivityBean
{
    /**
     * 活动信息实体
     */
    @SerializedName("activityinfo")
    private ActivityInfoBean activityInfo;

    /**
     * 背包信息实体
     */
    @SerializedName("giftBag")
    private GiftBagBean giftBag;

    /**
     * 查询条件实体
     */
    @SerializedName("condition")
    private List<ConditionBean> condition;

    /**
     * 奖励列表
     */
    @SerializedName("rewards")
    private List<RewardBean> rewards;

    /**
     * @return the activityInfo
     */
    public ActivityInfoBean getActivityInfo()
    {
        return activityInfo;
    }

    /**
     * @param activityInfo the activityInfo to set
     */
    public void setActivityInfo(ActivityInfoBean activityInfo)
    {
        this.activityInfo = activityInfo;
    }

    /**
     * @return the giftBag
     */
    public GiftBagBean getGiftBag()
    {
        return giftBag;
    }

    /**
     * @param giftBag the giftBag to set
     */
    public void setGiftBag(GiftBagBean giftBag)
    {
        this.giftBag = giftBag;
    }

    /**
     * @return the condition
     */
    public List<ConditionBean> getCondition()
    {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(List<ConditionBean> condition)
    {
        this.condition = condition;
    }

    /**
     * @return the rewards
     */
    public List<RewardBean> getRewards()
    {
        return rewards;
    }

    /**
     * @param rewards the rewards to set
     */
    public void setRewards(List<RewardBean> rewards)
    {
        this.rewards = rewards;
    }
}
