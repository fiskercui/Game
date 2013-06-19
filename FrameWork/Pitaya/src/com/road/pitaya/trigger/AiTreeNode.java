/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.pitaya.trigger;

import java.util.ArrayList;

/**
 * @author : cookie
 * @date 2012-11-5
 * @version 
 * @param <M> 当前结点执行时的上下文
 * @param <N> 为执行的返回结果
 * 行为树的结点
 */
public abstract class AiTreeNode<M, N>
{
    private String desc;        //当前结点的描述
    private String name;        //当前结点的名字
    private String errMsg;      //执行结果的描述
    
    protected ArrayList<AiTreeNode<M, N>> sonNodes;             //子结点，可能有，也可能没有
    
    public abstract N actionExecut(M context);                  //当前结点执行,并返回
    
    public AiTreeNode()
    {
        super();
    }
    /**
     * 构造行为结点
     * @param weight
     * @param desc
     * @param name
     */
    public AiTreeNode(String name, String desc, String errMsg)
    {
        super();
        this.desc = desc;
        this.name = name;
        this.errMsg = errMsg;
    }

    /**
     * 添加儿子结点
     * @param sonNode
     *          添加儿子结点
     */
    public boolean addSonNode(AiTreeNode<M, N> sonNode)
    {
        if (sonNode == null)
        {
            return false;
        }
        if (sonNodes == null)
        {
            sonNodes = new ArrayList<AiTreeNode<M, N>>();
        }
        return sonNodes.add(sonNode);
    }
    
    /**
     * 移除一个儿子结点
     * @param sonNode
     * @return
     */
    public boolean removeSonNode(AiTreeNode<M, N> sonNode)
    {
        if (sonNode == null || sonNodes == null)
        {
            return false;
        }
        
        return sonNodes.remove(sonNode);
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the sonNodes
     */
    public ArrayList<AiTreeNode<M, N>> getSonNodes()
    {
        return sonNodes;
    }

    /**
     * @param sonNodes the sonNodes to set
     */
    public void setSonNodes(ArrayList<AiTreeNode<M, N>> sonNodes)
    {
        this.sonNodes = sonNodes;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the errMsg
     */
    public String getErrMsg()
    {
        return errMsg;
    }

    /**
     * @param errMsg the errMsg to set
     */
    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }
}
