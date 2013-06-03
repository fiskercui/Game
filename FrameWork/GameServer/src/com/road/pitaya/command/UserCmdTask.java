package com.road.pitaya.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.dota.object.inf.IGamePlayer;
import com.road.pitaya.net.CommonMessage;

/**
 * 用户任务
 * @author weihua.cui
 *
 */
public class UserCmdTask extends AbstractCmdTask
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCmdTask.class);

    protected IGamePlayer player;


    public UserCmdTask(AbstractUserCmd cmd, CommonMessage message, IGamePlayer player)
    {
        super(cmd, message);
        this.player =  player;
    }

    @Override
    protected void execute()
    {
        try
        {
            this.cmd.execute(this.player, this.message);
        }
        catch (Exception e)
        {
            LOGGER.error(e.toString());
        }
        finally
        {
            this.player.finishOneCommandTask();
        }
        
    }

}
