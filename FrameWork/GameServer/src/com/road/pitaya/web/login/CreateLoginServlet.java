/**
 * Date: 2013-5-28
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import java.security.MessageDigest;

import com.road.pitaya.web.BaseHandlerServlet;
import com.road.util.ConfigWrapper;
import com.road.util.HashUtil;

/**
 * 用户登录第零步：使用客户端的临时密码，以作二次验证
 * 
 * @author yip
 */
public class CreateLoginServlet extends BaseHandlerServlet
{

    /**
     * 序列ID
     */
    private static final long serialVersionUID = 61616921870496530L;

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.web.IHandlerCmd#execute(java.lang.String)
     */
    @Override
    public String execute(String jsonString)
    {
        CreateLoginRecvBean recv = gson.fromJson(jsonString, CreateLoginRecvBean.class);
        return process(recv);
    }

    /**
     * @param recv
     * @return
     */
    private String process(CreateLoginRecvBean recv)
    {
//        检查元素非空
        if (!checkParams(recv))
        {
            return getResultJson(CreateLoginResultType.NULL_ELEMENT_EXIST);
        }
        String content = recv.getContent();
        String site = recv.getSite();
        //查询该区密钥数据
        String loginKey = loadKey(site);
        if(loginKey == null || loginKey.trim().equals(""))
        {
            return getResultJson(CreateLoginResultType.NOT_SITE_EXIST);
        }
        //检查content是不是按照格式发送
        String [] dataStr = checkContent(content); 
        if(dataStr == null) 
        {
            return getResultJson(CreateLoginResultType.ERROR_CONTENT);
        }
        //检查登录密钥是否正确
        if(!checkLoginKey(dataStr,loginKey))
        {
            return getResultJson(CreateLoginResultType.ERROR_LOGIN_KEY);
        }
//        发送创建成功消息
        return getResultJson(CreateLoginResultType.SUCCESS);
    }

    /**
     * 检查是否正确加密
     * @param dataStr
     * @param loginKey
     * @return
     */
    private boolean checkLoginKey(String[] dataStr, String loginKey)
    {
        String source = dataStr[0] + dataStr[1] + dataStr[2] + loginKey;
        String result = HashUtil.md5(source);
        return result.equalsIgnoreCase(dataStr[3]);

    }

    /**
     * 检查内容是否合理
     * @param content
     * @return
     */
    private String[] checkContent(String content)
    {
        String [] strs = content.split("\\|");
        if(content.length() != 4)
        {
            return null;
        }
        return strs;
    }

    /**
     * 加载该区所用的密钥（公钥）
     * 
     * @param site
     * @return
     */
    private String loadKey(String site)
    {
//        TODO:从某个缓存管理器取出该部分数据
        return ConfigWrapper.getValue("PublicKey.".concat(site));
    }

    /**
     * 检查收包是不是符合要求1：所有元素非空且包含有意义的字符
     * 
     * @param recv
     */
    private boolean checkParams(CreateLoginRecvBean recv)
    {
        if (recv == null || recv.getSite() == null || recv.getContent() == null || recv.getContent().trim().equals("")
                || recv.getSite().trim().equals(""))
        {
            return false;
        }
        return true;
    }

    public String getResultJson(int resultType)
    {
        CreateLoginResultBean result = new CreateLoginResultBean();
        result.setResult(resultType);
        return gson.toJson(result);
    }

    /**
     * 创建登录的回馈类型枚举
     * 
     */
    public interface CreateLoginResultType
    {
        /**
         * 成功
         */
        public static final int SUCCESS = 0;
        /**
         * 存在非空数据
         */
        public static final int NULL_ELEMENT_EXIST = 1;
        /**
         * 不存在该区
         */
        public static final int NOT_SITE_EXIST = 2;
        /**
         * 错误的登录密钥
         */
        public static final int ERROR_LOGIN_KEY = 3;
        /**
         * 错误的content
         */
        public static final int ERROR_CONTENT = 4;
    }
}
