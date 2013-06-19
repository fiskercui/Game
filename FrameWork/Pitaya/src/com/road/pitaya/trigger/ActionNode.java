/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.pitaya.trigger;


/**
 * @author : cookie
 * @date 2012-11-22
 * @version 
 * 执行结点，不关注返回值
 */
public abstract class ActionNode<M> extends AiTreeNode<M, Void>
{
    public abstract AiType getAiType();
}
