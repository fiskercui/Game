/**
 * Date: 2013-5-28
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import java.util.List;

import com.road.bll.PlayerBussiness;
import com.road.dota.component.inf.IUserLoginComponent;
import com.road.entity.SimpleUser;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.web.BaseHandlerServlet;

/**
 * 用户登录第一步：查询玩家角色,获取Player信息,
 * 单个或多个角色
 * @author yip
 */
public class LoginSelectServlet extends BaseHandlerServlet 
{

    /**
     * 序列ID
     */
    private static final long serialVersionUID = 61616921870496530L;

    /* (non-Javadoc)
     * @see com.road.pitaya.web.IHandlerCmd#execute(java.lang.String)
     */
    @Override
    public String execute(String jsonString)
    {
        LoginSelectRecvBean recv = gson.fromJson(jsonString, LoginSelectRecvBean.class);
        if(recv == null) return null;
        return process(recv);
    }

    /**
     * @param recv
     * @return 
     */
    private String process(LoginSelectRecvBean recv)
    {
        //用户名为空
        if(recv == null || recv.getUserName() == null || recv.getUserName().trim().equals("")||recv.getSite() == null || recv.getSite().trim().equals(""))
        {
            return getNotSuccessJson("NULL VALUE NOT ALLOWED");
        }
        
        //玩家是否在正确的步骤：先要通过第一步，通过第一步会有缓存。
        if(!checkLogin(recv.getUserName().concat(".").concat(recv.getSite())))//玩家是否已经通过验证；
        {
            return getNotSuccessJson("Not Right Step:Skip the Step ONE");
        }
        
        List<SimpleUser>  list = PlayerBussiness.getPlayerInfos(recv.getUserName(), recv.getSite());
        LoginSelectResultBean result = new LoginSelectResultBean();
        result.setResult(true);
        result.setPlayerInfos(list);//这里没带注解，未测试能不能生成json文件，经测试，可用
        result.setMessage("SUCCESS");
        return gson.toJson(result);
    }

    /**
     * @param userName
     * @param site
     * @return
     */
    private boolean checkLogin(String userName)
    {
        return ((IUserLoginComponent)ComponentManager.getInstance().getComponent("UserManager")).checkLogin(userName);
    }
    
    public String getNotSuccessJson(String errorMessage)
    {
        LoginSelectResultBean result = new LoginSelectResultBean();
        result.setResult(false);
        result.setMessage(errorMessage);
        return gson.toJson(result);
    }
}
