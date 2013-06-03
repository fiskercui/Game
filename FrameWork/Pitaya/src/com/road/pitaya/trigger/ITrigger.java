package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/


/**
 * @author : cookie
 * @date 2012-11-22
 * @version 
 * 触发器接口
 */
public interface ITrigger<M, N>
{
    //添加条件子结点
    public void addConditionNode(ConditionNode<N> condition);
    
    //添加条件结点为true时执行子结点
    public void addYesActionNode(ActionNode<N> action);
    
    //添加条件结点为false时执行子结点
    public void addNoActionNode(ActionNode<N> action);
    
    //挂载此触发器到host
    public void mount(M host, int eventType);
    
    //取消挂载触发器
    public void unMount(M host, int eventType);
}
