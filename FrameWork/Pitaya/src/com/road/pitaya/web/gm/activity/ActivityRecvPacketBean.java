/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.road.pitaya.web.gm.PagerBean;

/**
 *  活动通用接口接收的Json的收包实体
 * @author yip
 * @date 2013-5-22
 * @version
 * 
 */
public class ActivityRecvPacketBean
{
    // 注：所有可选参数如果是Integer类型的默认值都为0，如果是string类型的默认值为空
    /**
     * 操作参数
     */
    @SerializedName("op")
    private Integer op;
    /**
     * 活动列表
     */
    @SerializedName("activities")
    private List<ActivityBean> activities;
    /**
     * 查询的页码信息实体
     */
    @SerializedName("pager")
    private PagerBean pager;

    /**
     * @return the op
     */
    public Integer getOp()
    {
        return op;
    }

    /**
     * @param op
     *            the op to set
     */
    public void setOp(Integer op)
    {
        this.op = op;
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

    /**
     * @return the pager
     */
    public PagerBean getPager()
    {
        return pager;
    }

    /**
     * @param pager
     *            the pager to set
     */
    public void setPager(PagerBean pager)
    {
        this.pager = pager;
    }
}
