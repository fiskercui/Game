package com.road.pitaya.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.road.dota.object.inf.IGamePlayer;

/**
 * 玩家ID管理器
 * 暂时
 * @author weihua.cui
 */
public class GamePlayerMgr
{
    
    private Map<Integer, IGamePlayer> mapGamePlayer = new ConcurrentHashMap<Integer, IGamePlayer>();

    public void addGamePlayer(Integer id, IGamePlayer player)
    {
        mapGamePlayer.put(id, player);
    }
    
    public void delGamePlayer(Integer id)
    {
        mapGamePlayer.remove(id);
    } 
    
    public IGamePlayer getGamePlayer(Integer id)
    {
        return mapGamePlayer.get(id);
    }
    
    private static class LazyHolder
    {
        private static GamePlayerMgr INSTANCE = new GamePlayerMgr();
    }

    public static GamePlayerMgr getInstance()
    {
        return LazyHolder.INSTANCE;
    }
    
    public static void main(String[] args)
    {  
        Map<Integer, String> mapTest = new ConcurrentHashMap<Integer, String>();
        mapTest.put(1, "123");
        mapTest.put(1,"456");
        System.out.println(mapTest.toString());

        
    }
   
}
