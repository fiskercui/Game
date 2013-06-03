/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm.activity;

import java.util.List;

import com.google.gson.Gson;
import com.road.pitaya.web.BaseHandlerServlet;
import com.road.pitaya.web.gm.PagerBean;

/**
 * GM活动指令的抽象类，兼HTTP实现
 * @author yip
 * @date 2013-5-21
 * @version 
 */
public abstract class AbstractActivityGMServlet extends BaseHandlerServlet implements IActivity
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 这里进行流程控制
     */
    @Override
    public String execute(String jsonString)
    {
        Gson gson = new Gson();
        ActivityRecvPacketBean recv = gson.fromJson(jsonString, ActivityRecvPacketBean.class);

        if(recv == null)
        {
            return sendError("null recvPacket");
        }
        int op = recv.getOp();
        switch (op)
        {

            case 0:// 查询
            {
                PagerBean pager = recv.getPager();
                return this.query(pager);
            }
            case 1://添加
            {
                List<ActivityBean> activites = recv.getActivities();
                return this.add(activites);
            }
            case 2://删除
            {
                List<ActivityBean> activites =  recv.getActivities();
                return this.delete(activites);
            }
            case 3://修改
            {
                List<ActivityBean> activites =  recv.getActivities();
                return this.change(activites);
            }
            case 4://激活或停用
            {
                List<ActivityBean> activites =  recv.getActivities();
                return this.activeOrStopActivities(activites);
            }
        }
        return null;
    }
    
    public String sendError(String message)
    {
        ActivitySendPacketBean sendBean = new ActivitySendPacketBean();
        sendBean.setStatus(0);
        sendBean.setMes(message);
        return gson.toJson(sendBean);
    }
}
