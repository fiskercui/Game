/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.pitaya.web.gm.activity;

import java.util.List;

import com.road.pitaya.web.gm.PagerBean;

/**
 * 通用活动接口
 * @author yip
 * @date 2013-5-22
 * @version 
 *
 */
public interface IActivity
{
    /**
     * 查询活动 op=0
     */
    String query(PagerBean pager);
    
    /**
     * 添加活动 op=1
     * @param activites
     * @return
     */
    String add(List<ActivityBean> activites);
    
    /**
     * 删除活动 op=2
     */
    String delete(List<ActivityBean> activites);
    
    /**
     * 修改活动 op=3
     * @param activites
     * @return
     */
    String change(List<ActivityBean> activites);
    
    /**
     * 激活活动 op=4 state=1 \ 停用活动op=4 state=0
     */
    String activeOrStopActivities(List<ActivityBean> activites);
    
}
