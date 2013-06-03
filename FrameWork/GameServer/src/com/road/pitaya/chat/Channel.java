package com.road.pitaya.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 聊天频道
 * 
 * @author weihua.cui
 */
public class Channel
{

    
    /**
     * userList存储用户Id
     */
    private List<Integer> userList = new ArrayList<Integer>();

    /**
     * 注册用户
     * 
     * @param userId
     */
    public void addUser(int userId)
    {
        if (userList.contains(userId))
            return;
        userList.add(userId);
    }

    /**
     * 移除用户
     * 
     * @param userId
     */
    public void delUser(int userId)
    {
        if (userList.contains(userId))
            userList.remove(userId);
    }

    /**
     * 向频道里所用用户分发消息
     * 
     * @param sendId
     * @param message
     * @return
     */
    public List<Integer> getChannelUserList()
    {
        return userList;
    }


}
