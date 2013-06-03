package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
/**
 * @author : cookie
 * @date : 2012-11-28
 * @version 
 * Ai结点的创建工厂
 */
public interface IAiNodeFactory<M, N>
{
    public AiTreeNode<?, ?> createAiTreeNode(int type, boolean needNew, String args);
}
