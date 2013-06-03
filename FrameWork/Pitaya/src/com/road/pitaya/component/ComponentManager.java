/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.util.StringUtil;

/**
 * 组件管理器。使用单例模式。 <br>
 * 组件采用总线服务的形式提供单例的实例，通过getComponent方法获取组件实例。
 * 
 * @author jiayi.wei
 */
public class ComponentManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentManager.class);

    private ClassLoader loader = null;

    /** 组件集合 */
    private Map<String, IComponent> modules = null;
    
    /** 组件线程池*/
    private ExecutorService userCmdThreadPool = Executors.newFixedThreadPool(4);

    private ComponentManager()
    {
        loader = Thread.currentThread().getContextClassLoader();
        modules = new ConcurrentHashMap<String, IComponent>();
    }

    private static class LazyHolder
    {
        private static final ComponentManager INSTANCE = new ComponentManager();
    }

    /**
     * 获取ComponentManager实例。
     * 
     * @return
     */
    public static ComponentManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    /**
     * 添加组件。
     * 
     * @param className
     *            组件类名
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public boolean addComponent(String className)
    {
        try
        {
            Class<?> cls = loader.loadClass(className);
            IComponent module = (IComponent) cls.newInstance();
            if (StringUtil.isNullOrEmpty(module.getName()))
            {
                return false;
            }
            this.modules.put(module.getName(), module);

            return true;
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.error("", e);
            return false;
        }
        catch (InstantiationException e)
        {
            LOGGER.error("", e);
            return false;
        }
        catch (IllegalAccessException e)
        {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 获取组件实例。
     * 
     * @param componentName
     *            组件名称
     * @return
     */
    public IComponent getComponent(String componentName)
    {
        return modules.get(componentName);
    }

    /**
     * 获取所有组件。
     * 
     * @return 所有组件
     */
    public Map<String, IComponent> getComponents()
    {
        return modules;
    }

    /**
     * 初始化组件管理器。
     * 
     * @return
     */
    public boolean init()
    {
        return true;
    }

    /**
     * 启动组件管理器。
     * 
     * @return
     */
    public boolean start()
    {
        if (initModules() == false)
        {
            return false;
        }

        if (startModules() == false)
        {
            return false;
        }

        return true;
    }

    /**
     * 关闭组件管理器。
     */
    public void stop()
    {
        stopModules();
    }

    /**
     * 初始化所有组件。
     */
    private boolean initModules()
    {
        for (IComponent module : modules.values())
        {
            try
            {
                if (module.initialize() == false)
                {
                    return false;
                }
            }
            catch (Exception e)
            {
                this.modules.remove(module.getClass());
                module.stop();
                module.destroy();
                LOGGER.error("", e);
                return false;
            }
        }

        return true;
    }

    /**
     * 启动所有组件。
     * 
     * @return
     */
    private boolean startModules()
    {
        for (IComponent module : modules.values())
        {
            try
            {
                LOGGER.info("Component {} is starting...", module.getName());
                if (module.start() == false)
                {
                    LOGGER.info("Component {} has started failed.", module.getName());
                    return false;
                }
                LOGGER.info("Component {} has started successfully.", module.getName());
            }
            catch (Exception e)
            {
                module.stop();
                module.destroy();
                LOGGER.error("", e);
                return false;
            }
        }

        return true;
    }

    /**
     * 停止并销毁所有组件。
     */
    private void stopModules()
    {
        for (IComponent module : modules.values())
        {
            module.stop();
            module.destroy();
        }
    }
    
    /**
     * 
     */
    public ExecutorService getUserCmdThreadPool()
    {
        return userCmdThreadPool;
    }
}
