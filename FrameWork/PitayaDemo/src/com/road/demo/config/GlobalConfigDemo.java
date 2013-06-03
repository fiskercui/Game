/**
 * Date: May 31, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.demo.config;

import java.util.List;

import com.road.pitaya.config.DatabaseConfig;
import com.road.pitaya.config.GlobalConfig;

/**
 * 
 * @author jiayi.wei
 */
public class GlobalConfigDemo
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String configPatp = "G:/SVNRepo/MServer/trunk/framework/CommonLib/config/global.xml";
        if (GlobalConfig.getInstance().init(configPatp))
        {
            List<DatabaseConfig> configs = GlobalConfig.getInstance()
                    .getDatabaseConfigs();
            for (DatabaseConfig item : configs)
            {
                System.out.println(item.toString());
            }
        }
    }

}
