/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota;

/**
 * @author cookie.hu
 * @date 2013-5-21
 * @version 
 * 游戏服务器
 */
public class GameServer
{
    private static final GameServer GAME_SERVER = new GameServer();
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // System.out.println(System.getProperty("user.dir"));
        long time = System.currentTimeMillis();
        if (args.length <= 0)
        {
            System.err.println("请输入配置文件地址路径...");
            return;
        }

        System.err.println("启动耗时: " + (System.currentTimeMillis() - time));
    }

    public static GameServer getInstance()
    {
        return GAME_SERVER;
    }
}
