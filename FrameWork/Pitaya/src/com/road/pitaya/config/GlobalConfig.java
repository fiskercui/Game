/**
 * Date: May 31, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.util.StringUtil;

/**
 * 全局统一XML配置
 * 
 * @author jiayi.wei
 */
public class GlobalConfig
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GlobalConfig.class);

    private static final String XPATH_DATABASE = "/config/database/db";

    /** 配置文件根Document */
    private Document documentRoot = null;

    private GlobalConfig()
    {

    }

    private static final class LazyLoader
    {
        private static final GlobalConfig INSTANCE = new GlobalConfig();
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static GlobalConfig getInstance()
    {
        return LazyLoader.INSTANCE;
    }

    /**
     * 初始化配置，使用ConfigWrapper前必须先初始化。
     * 
     * @param path
     *            配置文件路径
     * @return
     */
    public boolean init(String path)
    {
        if (StringUtil.isNullOrEmpty(path))
        {
            return false;
        }

        try
        {
            SAXReader reader = new SAXReader();
            documentRoot = reader.read(path);
            return true;
        }
        catch (DocumentException e)
        {
            LOGGER.error("init config error.", e);
            return false;
        }
    }

    /**
     * 获取指定xpath的节点集合
     * 
     * @param xpath
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Element> getElements(String xpath)
    {
        return documentRoot.selectNodes(xpath);
    }

    /**
     * 获取单个节点
     * 
     * @param xpath
     * @return
     */
    public Element getElement(String xpath)
    {
        return (Element) documentRoot.selectSingleNode(xpath);
    }

    /**
     * 获取单个节点
     * 
     * @param nodePath
     *            节点路径
     * @param attributeName
     *            属性名
     * @param attributeValue
     *            属性值
     * @return
     */
    public Element getElement(String nodePath, String attributeName,
            String attributeValue)
    {
        String x = String.format("%s[@%s='%s']", nodePath, attributeName,
                attributeValue);
        return (Element) documentRoot.selectSingleNode(x);
    }

    /**
     * 获取数据库配置
     * 
     * @return
     */
    public List<DatabaseConfig> getDatabaseConfigs()
    {
        List<DatabaseConfig> configs = new ArrayList<DatabaseConfig>();
        List<Element> elements = getElements(XPATH_DATABASE);
        for (Element item : elements)
        {
            DatabaseConfig config = new DatabaseConfig();
            config.setName(item.attributeValue("name"));
            config.setUrl(item.element("url").getTextTrim());
            config.setUsername(item.element("username").getTextTrim());
            config.setPassword(item.element("password").getTextTrim());
            configs.add(config);
        }

        return configs;
    }
}
