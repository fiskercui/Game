/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import com.google.gson.annotations.SerializedName;

/**
 *  活动信息实体
 * @author yip
 * @date 2013-5-22
 * @version
 */
public class ActivityInfoBean
{

    /**
     * activityId String 必填（主键） 活动ID(UUID)
     */
    @SerializedName("activityId")
    private String activityId;
    /**
     * 活动名称，选填
     */
    @SerializedName("activityName")
    private String activityName;
    /**
     * 活动类型,必填
     */
    @SerializedName("activityType")
    private Integer activityType;
    /**
     * 活动类型子类型,可选 例：一次性充值、累计充值
     */
    @SerializedName("activityChildType")
    private Integer activityChildType;
    /**
     * 领取方式,必填 1、单次领取2、多次领取3、每天领取4、不能领取
     */
    @SerializedName("getWay")
    private Integer getWay;
    /**
     * 活动描述,必填
     */
    @SerializedName("desc")
    private String desc;
    /**
     * 奖励描述
     */
    @SerializedName("rewardDesc")
    private String rewardDesc;
    /**
     * 开始时间,必选
     */
    @SerializedName("begIntegerime")
    private String begIntegerime;
    /**
     * 默认和开始时间一致,必选
     */
    @SerializedName("beginShowTime")
    private String beginShowTime;
    /**
     * 结束时间
     */
    @SerializedName("endTime")
    private String endTime;
    /**
     * 默认和结束时间一致
     */
    @SerializedName("endShowTime")
    private String endShowTime;
    /**
     * 图标、图片路径,可选
     */
    @SerializedName("icon")
    private String icon;
    /**
     * 是否时间段,可选
     */
    @SerializedName("isContinue")
    private Integer isContinue;
    /**
     * 激活状态 1表示激活,0表示停用,可选
     */
    @SerializedName("status")
    private Integer status;
    /**
     * 预留字段
     */
    @SerializedName("remain1")
    private String remain1;
    /**
     * 预留字段
     */
    @SerializedName("remain2")
    private String remain2;

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
     * @return the activityName
     */
    public String getActivityName()
    {
        return activityName;
    }

    /**
     * @param activityName
     *            the activityName to set
     */
    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    /**
     * @return the activityType
     */
    public Integer getActivityType()
    {
        return activityType;
    }

    /**
     * @param activityType
     *            the activityType to set
     */
    public void setActivityType(Integer activityType)
    {
        this.activityType = activityType;
    }

    /**
     * @return the activityChildType
     */
    public Integer getActivityChildType()
    {
        return activityChildType;
    }

    /**
     * @param activityChildType
     *            the activityChildType to set
     */
    public void setActivityChildType(Integer activityChildType)
    {
        this.activityChildType = activityChildType;
    }

    /**
     * @return the getWay
     */
    public Integer getGetWay()
    {
        return getWay;
    }

    /**
     * @param getWay
     *            the getWay to set
     */
    public void setGetWay(Integer getWay)
    {
        this.getWay = getWay;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the rewardDesc
     */
    public String getRewardDesc()
    {
        return rewardDesc;
    }

    /**
     * @param rewardDesc
     *            the rewardDesc to set
     */
    public void setRewardDesc(String rewardDesc)
    {
        this.rewardDesc = rewardDesc;
    }

    /**
     * @return the begIntegerime
     */
    public String getBegIntegerime()
    {
        return begIntegerime;
    }

    /**
     * @param begIntegerime
     *            the begIntegerime to set
     */
    public void setBegIntegerime(String begIntegerime)
    {
        this.begIntegerime = begIntegerime;
    }

    /**
     * @return the beginShowTime
     */
    public String getBeginShowTime()
    {
        return beginShowTime;
    }

    /**
     * @param beginShowTime
     *            the beginShowTime to set
     */
    public void setBeginShowTime(String beginShowTime)
    {
        this.beginShowTime = beginShowTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime()
    {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    /**
     * @return the endShowTime
     */
    public String getEndShowTime()
    {
        return endShowTime;
    }

    /**
     * @param endShowTime
     *            the endShowTime to set
     */
    public void setEndShowTime(String endShowTime)
    {
        this.endShowTime = endShowTime;
    }

    /**
     * @return the icon
     */
    public String getIcon()
    {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    /**
     * @return the isContinue
     */
    public Integer getIsContinue()
    {
        return isContinue;
    }

    /**
     * @param isContinue
     *            the isContinue to set
     */
    public void setIsContinue(Integer isContinue)
    {
        this.isContinue = isContinue;
    }

    /**
     * @return the status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status)
    {
        this.status = status;
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

    /**
     * @return the remain2
     */
    public String getRemain2()
    {
        return remain2;
    }

    /**
     * @param remain2
     *            the remain2 to set
     */
    public void setRemain2(String remain2)
    {
        this.remain2 = remain2;
    }

}
