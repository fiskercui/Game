/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.pitaya.web.gm.activity;

import java.util.List;

import com.google.gson.Gson;
import com.road.pitaya.web.gm.PagerBean;
import com.road.pitaya.web.gm.activity.AbstractActivityGMServlet;
import com.road.pitaya.web.gm.activity.ActivityBean;

/**
 * 
 * 一个具体的实现类
 * @author yip
 * @date 2013-5-22
 * @version 
 *
 */
public class DDTActivityGMServletImpl extends AbstractActivityGMServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see com.road.pitaya.web.activity.IActivity#activeOrStopActivities(java.util.List)
     */
    @Override
    public String activeOrStopActivities(List<ActivityBean> activites)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.web.activity.IActivity#add(java.util.List)
     */
    @Override
    public String add(List<ActivityBean> activites)
    {
        if(activites == null)
        {
            return "null activites to add";
        }
//        return activites.toString();
        return new Gson().toJson(activites);
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.web.activity.IActivity#change(java.util.List)
     */
    @Override
    public String change(List<ActivityBean> activites)
    {
        if(activites == null)
        {
            return "null activites";
        }
        return activites.toString();
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.web.activity.IActivity#delete(java.util.List)
     */
    @Override
    public String delete(List<ActivityBean> activites)
    {
        if(activites == null)
        {
            return "null activites";
        }
        return activites.toString();
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.web.activity.IActivity#query(com.road.pitaya.web.activity.PagerBean)
     */
    @Override
    public String query(PagerBean pager)
    {
        if(pager == null)
        {
            return "null pager";
        }
        return new Gson().toJson(pager);
    }
}
