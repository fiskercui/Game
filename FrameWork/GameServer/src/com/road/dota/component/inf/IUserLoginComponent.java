/**
 * Date: 2013-5-29
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.dota.component.inf;

import com.road.entity.AccountInfo;

/**
 * 
 * @author yip 用户组件
 * 
 */
public interface IUserLoginComponent
{
    /**
     * 添加用户为登录状态
     * 
     * @param userName
     *            包含Site拼接的用户名
     * @param tempPassWord
     *            临时密码
     * @return
     */
    public boolean addLogin(String userName, String tempPassWord);

    /**
     * 检查用户登录状态,是不是在正确的步骤
     * 
     * @param userName
     *            拼接Site的用户名
     * @return 查找成功
     */
    public boolean checkLogin(String userName);

    /**
     * 检查用户登录状态，密码是不是和临时密码匹配
     * 
     * @param userName
     * @param passwd
     * @return
     */
    public boolean checkLogin(String userName, String passwd);

    /**
     * 移除对应的登录状态的用户
     * 
     * @param userName
     *            拼接了Site的用户名
     */
    public void removeUser(String userName);

    /**
     * 更新登录玩家（数据库信息）
     * @param info
     */
    public void updateLoginUser(AccountInfo info);
    
    /**
     * 创建在线人物缓存 （只是客户端没有获得人物）
     * @param userId       
     * @param password
     * @return
     */
    public boolean createOnline(int userId,String password);
}
