package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/


/**
 * @author : cookie
 * @date 2012-11-5
 * @version 
 * @param <M>
 * @param <N>
 * 条件结点，用来作为AI树的叶子结点
 */
public abstract class ConditionNode<M> extends AiTreeNode<M, Boolean>
{
    //当前条件结点适用于哪种AI类型
    public abstract AiType getAiType();
    
    /**
     * @param name
     * @param desc
     * @param errMsg
     */
    public ConditionNode(String name, String desc, String errMsg)
    {
        super(name, desc, errMsg);
    }

    /**
     * 
     */
    public ConditionNode()
    {
        super();
    }
    
    
}
