/**
 * Date: 2013-5-28
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.register;

import com.road.pitaya.web.BaseHandlerServlet;

/**
 * 
 * @author yip
 */
public class VisualRegisterServlet extends BaseHandlerServlet
{

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 9107116213422181828L;

    /* (non-Javadoc)
     * @see com.road.pitaya.web.IHandlerCmd#execute(java.lang.String)
     */
    @Override
    public String execute(String jsonString)
    {
        VisualRegisterRecvBean vb = gson.fromJson(jsonString,VisualRegisterRecvBean.class);
        return process(vb);
    }

    /**
     * 处理注册信息的详细方式
     * @param vb
     * @return
     */
    private String process(VisualRegisterRecvBean vb)
    {
        return null;
    }
    
    public String getVisualRegisterResult(boolean result,int type,String message)
    {
        return gson.toJson(new VisualRegisterResultBean(result,type,message));
    }
    
    /**
     * 帐号注册反馈信息
     * @author yip
     */
    public interface VisualizeRegisterType
    {
        public static int SUCCEED=0;//角色激活成功
        public static int CAMP_ERROR=2;//参数阵营异常
        public static int NICKNAME_ERROR=3;//参数昵称不合法
        public static int ICON_ERROR=4;//用户头像不正确
        public static int USERID_ERROR=5;//用户ID与用户名不匹配
        public static int NICKNAMEEXISTS_ERROR=6;//当前昵称已存在
        public static int PLAYID_ERROR=7;//角色已激活
        public static int LOGIN_INVALID=8;//用户登陆已失效
        public static int ACTIVE_INVALID=9;//角色激活失败
        public static int ACTIVEHERO_INVALID=10;//英雄激活失败
        public static int LOGINWAITE_MAX=11;//服务器人数达上限
    }
}
