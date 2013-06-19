/**
 * Date: 2013-5-23
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 处理params为json String基本的Servlet 继承该Servlet去实现，也可以参考这个来对应实现
 * 
 * @author yip
 */
public abstract class BaseHandlerServlet extends HttpServlet implements IHandlerCmd
{
    /**
     * 
     */
    private static final long serialVersionUID = -7337936262053988938L;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHandlerServlet.class);

    /**
     * 用于处理实体类的Gson实例
     */
    public final Gson gson = new Gson();
    
    public BaseHandlerServlet()
    {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        String jsonString = request.getParameter("params");
        String result = execute(jsonString);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 获取客户端IP
     * 
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 26)
        {
            LOGGER.error("不明请求IP" + ip);
            int index = ip.indexOf(",");
            if (index != -1)
            {
                ip = ip.substring(0, index);
            }
            else
            {
                ip = ip.substring(0, 25);
            }
        }
        if (ip != null)
        {
            ip = ip.trim();
        }
        return ip;
    }

    /**
     * 默认不处理，可以重写doPost,和execute(String,String [])方法来实现调用
     */
    @Override
    public String execute(String jsonString, String... strings)
    {
        return null;
    }

    /**
     * 转换utf-8编码
     * 
     * @param value
     * @return
     */
    public static String getUTF8(String value)
    {
        if (value != null)
            try
            {
                if (!checkGBK(value))
                {
                    return new String(value.getBytes("ISO-8859-1"), "UTF-8");
                }
                else
                {
                    return value;
                }
            }
            catch (UnsupportedEncodingException e)
            {
                LOGGER.error("获取字符转换成utf-8出错", e);
            }
        return "";
    }

    public static boolean checkGBK(String value)
    {
        return java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(value);
    }
}
