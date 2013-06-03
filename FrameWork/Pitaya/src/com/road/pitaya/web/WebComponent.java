/**
 * Date: 2013-5-21
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.component.IComponent;
import com.road.util.ConfigWrapper;

/**
 * 嵌入式http调用使用的连接组件
 * 
 * @author yip
 */
public class WebComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WebComponent.class);

    public static final String NAME = "WebComponent"; 
    /**
     * jetty自带的server
     */
    private Server server;

    /**
     * 触发的处理器
     */
    private ServletContextHandler context;

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        server = new Server(ConfigWrapper.getInt("WebPort"));
        try
        {
            context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            loadServletByXmlConfig(ConfigWrapper.getValue("server.xml"));
            server.start();
        }
        catch (DocumentException e)
        {
            LOGGER.error("load Xml Failed");
            e.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 使用xml加载Servlet类
     * 
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean loadServletByXmlConfig(String xmlPath) throws DocumentException
    {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(xmlPath));
        Element rootElm = document.getRootElement();
        List<Element> nodes = rootElm.elements("Servlet");
        for (Iterator<Element> it = nodes.iterator(); it.hasNext();)
        {
            Element elm = it.next();
            String className = elm.attributeValue("class");
            String path = elm.attributeValue("path");

            try
            {
                Class cls = Class.forName(className);
                Servlet servlet = (Servlet) cls.newInstance();
                context.addServlet(new ServletHolder(servlet), path);
                LOGGER.info("Class:{},Path:{} loaded", className, path);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
                continue;
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
                continue;
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return false;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#destroy()
     */
    @Override
    public void destroy()
    {
        
    }
}
