package com.road;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.component.DefaultMinaComponent;
import com.road.pitaya.web.WebComponent;
import com.road.util.ConfigWrapper;

public class TestPbServer  
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestPbServer.class);
    
    /**
     * 单例加载器
     */
    private static class LazyHolder
    {
        private static final TestPbServer INSTANCE = new TestPbServer();
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static TestPbServer getInstance()
    {
        return LazyHolder.INSTANCE;
    }
    
    public boolean start(String configPath)
    {
        ConfigWrapper.init(configPath);
        return InitComponents();
    }
    
    protected boolean InitComponents()
    {
        try
        {
            ComponentManager.getInstance().init();
            ComponentManager.getInstance().addComponent(DefaultMinaComponent.class.getName());
            ComponentManager.getInstance().addComponent(CommandComponent.class.getName());
            ComponentManager.getInstance().addComponent(WebComponent.class.getName());
            ComponentManager.getInstance().start();
        }
        catch (Exception e)
        {
            LOGGER.debug("", e);
            return false;
        }

        return true;
    }
    
    
    public static void main(String[] args)
    {
        if(args.length <= 0)
        {
            return ;
        }
        String configPath = args[0];
        TestPbServer.getInstance().start(configPath);
        
    }

}
