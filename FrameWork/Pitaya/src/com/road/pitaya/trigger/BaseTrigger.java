package com.road.pitaya.trigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.pitaya.event.EventArg;
import com.road.pitaya.event.EventSource;
import com.road.pitaya.event.IEventListener;

/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
/**
 * @author : cookie
 * @date 2012-11-21
 * @version 触发器基类,一个触发器可以注册任意多个事件
 */
public class BaseTrigger<M extends EventSource> implements
        IEventListener, ITrigger<M,EventArg>
{
    private static final Logger logger = LoggerFactory.getLogger(BaseTrigger.class);

    private SequenceConditionNode conditions;

    /*yes执行结点必须有*/
    private SequenceActionNode yesActions;

    /*no执行结点可能没有*/
    private SequenceActionNode noActions;
    
    // 触发器类型，是注册游戏事件，还是注册玩家事件
    private AiType aiType;

    public BaseTrigger(TriggerInfo triggerInfo,
            IAiNodeFactory<EventArg, Boolean> conNodeFactory,
            IAiNodeFactory<EventArg, Void> actNodeFactory, AiType aiType)
    {
        super();
        this.yesActions = new SequenceActionNode(triggerInfo.getYesActionInfo(),
                actNodeFactory);
        if (triggerInfo.getNoActionInfo() != null )
        {
            this.noActions = new SequenceActionNode(triggerInfo.getNoActionInfo(),
                    actNodeFactory);
        }

        this.conditions = new SequenceConditionNode(
                triggerInfo.getConditionInfo(), conNodeFactory);
        this.aiType = aiType;
    }

    @Override
    public void onEvent(EventArg arg)
    {
        try
        {
            // 事件作为上下文参数
            if (conditions.actionExecut(arg))
            {
                yesActions.actionExecut(arg);
            }
            else
            {
                if (noActions != null)
                {
                    noActions.actionExecut(arg);
                }
                logger.info(conditions.getErrMsg());
            }
        }
        catch (Exception e)
        {
            logger.error("trigger respond err:", e);
        }
    }

    @Override
    public void addConditionNode(ConditionNode<EventArg> condition)
    {
        if (condition.getAiType() == this.aiType)
        {
            conditions.addSonNode(condition);
        }
        else
        {
            // 不能将不同AI类型的条件结点添加
            logger.error("can not add " + condition.getAiType()
                    + "conditionNode to " + aiType + " trigger.");
        }
    }

    @Override
    public void addYesActionNode(ActionNode<EventArg> actionNode)
    {
        if (actionNode.getAiType() == this.aiType)
        {
            yesActions.addSonNode(actionNode);
        }
        else
        {
            // 不能将不同AI类型的action结点添加
            logger.error("can not add " + actionNode.getAiType()
                    + "actionNode to " + aiType + " trigger.");
        }
    }


    /* (non-Javadoc)
     * @see com.road.dota.ai.trigger.ITrigger#addNoActionNode(com.road.dota.util.bt.ActionNode)
     */
    @Override
    public void addNoActionNode(ActionNode<EventArg> actionNode)
    {
        if (noActions != null && actionNode.getAiType() == this.aiType)
        {
            noActions.addSonNode(actionNode);
        }
        else
        {
            // 不能将不同AI类型的action结点添加
            logger.error("can not add " + actionNode.getAiType()
                    + "actionNode to " + aiType + " trigger.");
        }
    }
    
    @Override
    public void mount(M host, int eventType)
    {
        if (host != null)
        {
            host.addListener(eventType, this);
        }
    }

    @Override
    public void unMount(M host, int eventType)
    {
        if (host != null)
        {
            host.removeListener(eventType, this);
        }
    }
    
    /**
     * @param type
     *            the type to set
     */
    public void setType(AiType type)
    {
        this.aiType = type;
    }

    /**
     * @return the type
     */
    public AiType getType()
    {
        return aiType;
    }
}
