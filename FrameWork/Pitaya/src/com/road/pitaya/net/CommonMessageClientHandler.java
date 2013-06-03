package com.road.pitaya.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.command.ICommand;
import com.road.pitaya.component.AbstractCommandComponent;
import com.road.pitaya.component.ComponentManager;

public class CommonMessageClientHandler implements IClientPacketHandler
{
    private static Logger LOGGER = LoggerFactory.getLogger(CommonMessageClientHandler.class);

    @Override
    public void process(IClientConnection conn, Object packet)
    {
        short code = ((CommonMessage) packet).getCode();
        // 从组件管理器中间调用
        AbstractCommandComponent<?> cm = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent("command");
        if (cm == null)
        {
            LOGGER.error("CommandModule not found");
            return;
        }

        ICommand cmd = cm.getCommand(code);
        if (cmd == null)
        {
            LOGGER.warn(" Can not found code = " + code + ",drop this packet.");
            return;
        }

        try
        {
            cmd.execute(conn, (CommonMessage) packet);
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }

    }
}
