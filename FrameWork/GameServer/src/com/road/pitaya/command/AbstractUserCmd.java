package com.road.pitaya.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.object.inf.IGamePlayer;
import com.road.pitaya.command.ICommand;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.game.ISequenceTask;
import com.road.pitaya.net.CommonMessage;
import com.road.pitaya.net.IClientConnection;

/**
 * 抽象用户命令调度器
 * @author weihua.cui
 * 
 */

public abstract class AbstractUserCmd implements ICommand
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserCmd.class);

    /**
     * server excute全局调用使用(还没有玩家的)
     * 
     * @param
     */
    public void executeConnect(IClientConnection conn, CommonMessage packet)
    {

    }
    /**
     * 用户任务的调度分配， Handler调用
     */
    @Override
    public final void execute(IClientConnection conn, CommonMessage packet)
    {
        if(conn == null)
        {
            LOGGER.error("connect null error");
        }
        IGamePlayer player = (IGamePlayer) conn.getHolder();
        ISequenceTask sequenceTask = (ISequenceTask) player;
        if(player == null)
        {
            ServerCmdTask task = new ServerCmdTask(this, packet, conn);
            // 没有创建IGamePlayer对象前的客户端请求
            ComponentManager.getInstance().getUserCmdThreadPool().submit(task);
        }
        else
        {
            if (sequenceTask == null)
            {
                LOGGER.error("sequenceTask is null, userName: {}.", player.toString());
                return;
            }
            else
            {
                UserCmdTask task = new UserCmdTask(this, packet, player);
                sequenceTask.addCommandTask(task);
            }
        }
    } 

    public abstract void execute(IGamePlayer player, CommonMessage packet);

}
