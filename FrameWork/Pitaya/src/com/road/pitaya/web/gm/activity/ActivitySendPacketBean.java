/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 *  gm活动通用接口回包实体
 * @author yip
 * @date 2013-5-22
 * @version
 */
public class ActivitySendPacketBean
{
    /**
     * 返回状态，1表示成功、0表示失败
     */
    @SerializedName("status")
    private Integer status;
    /**
     * 返回信息，失败的时候可以把失败原因写在里面
     */
    @SerializedName("mes")
    private String mes;
    /**
     * 记录总数 ,op为0(查询)返回
     */
    @SerializedName("totalCount")
    private Integer totalCount;
    /**
     * 返回的活动集合, op为0(查询)返回
     */
    @SerializedName("activities")
    private List<ActivityBean> activities;

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
     * @return the mes
     */
    public String getMes()
    {
        return mes;
    }

    /**
     * @param mes
     *            the mes to set
     */
    public void setMes(String mes)
    {
        this.mes = mes;
    }

    /**
     * @return the totalCount
     */
    public Integer getTotalCount()
    {
        return totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(Integer totalCount)
    {
        this.totalCount = totalCount;
    }

    /**
     * @return the activities
     */
    public List<ActivityBean> getActivities()
    {
        return activities;
    }

    /**
     * @param activities
     *            the activities to set
     */
    public void setActivities(List<ActivityBean> activities)
    {
        this.activities = activities;
    }

}
