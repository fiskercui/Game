package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/

import java.util.List;

import com.road.pitaya.event.EventArg;
import com.road.util.ThreadSafeRandom;


/**
 * @author : cookie
 * @date 2012-11-22
 * @version 
 * action列表，所有的子结点都要执行
 */
public class SequenceActionNode extends AiTreeNode<EventArg, Void>
{
    private LogicType logicType = LogicType.AND;
    
    private static final ThreadSafeRandom RANDOM = new ThreadSafeRandom();
    
    public SequenceActionNode(LogicType logicType)
    {
        super();
        this.logicType = logicType;
    }
    
    @SuppressWarnings("unchecked")
    public SequenceActionNode(ActionInfo actionInfo, IAiNodeFactory<EventArg, Void> nodeFactory)
    {
        super();
        this.logicType = actionInfo.getLogicType();
        List<ActionInfo> sonActionInfos = actionInfo.getSonActions();
        if (sonActionInfos != null && sonActionInfos.size() > 0)
        {
            for (ActionInfo sonAction : sonActionInfos)
            {
                if (sonAction.getSonActions() != null)
                {
                    SequenceActionNode sonNode = new SequenceActionNode(
                            sonAction, nodeFactory);
                    this.addSonNode(sonNode);
                }
                else
                {
                    boolean isNeedNew = sonAction.isNeedNew();
                    ActionNode<EventArg> sonNode = (ActionNode<EventArg>) nodeFactory.createAiTreeNode(sonAction.getActionID(),
                                                                                           isNeedNew,
                                                                                           sonAction.getParams());
                    this.addSonNode(sonNode);
                }
            }
        }
    }
    
    @Override
    public Void actionExecut(EventArg context)
    {
        if (sonNodes == null || sonNodes.size() == 0)
        {
            return null;
        }
        
        switch (logicType)
        {
            case AND:
                sequenceNode(context);
                break;
            case RANDOM:
                randomNode(context);
            default:
                sequenceNode(context);
                break;
        }
        return null;
    }

    /**
     * 随机执行一个action
     * @param context
     */
    private void randomNode(EventArg context)
    {
        int index = RANDOM.next(0, sonNodes.size());
        AiTreeNode<EventArg, Void> sonNode = sonNodes.get(index);
        sonNode.actionExecut(context);
    }
    
    /**
     * 顺序执行每个action
     * @param context
     */
    private void sequenceNode(EventArg context)
    {
        for (AiTreeNode<EventArg, Void> sonNode : sonNodes)
        {
            sonNode.actionExecut(context);
        }
    }

}
