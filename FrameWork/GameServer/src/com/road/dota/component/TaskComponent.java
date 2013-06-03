/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.component;

import java.util.List;

import com.road.dota.component.inf.ITaskComponent;
import com.road.dota.task.condition.BaseTaskCondition;
import com.road.entity.bean.LinkTaskBean;
import com.road.entity.bean.TaskBean;

/**
 * @author cookie.hu
 * @date 2013-5-21
 * @version 
 *
 */
public class TaskComponent implements ITaskComponent
{

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#destroy()
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.ITaskComponent#getTaskCondition(int)
     */
    @Override
    public List<BaseTaskCondition> getTaskCondition(int taskID)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.ITaskComponent#getRewardTask()
     */
    @Override
    public List<TaskBean> getRewardTask()
    {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see com.road.dota.component.inf.ITaskComponent#getLinkTaskByType(int, int)
     */
    @Override
    public List<TaskBean> getLinkTaskByType(int taskType, int level)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.road.dota.component.inf.ITaskComponent#getLinkTaskBean(int)
     */
    @Override
    public LinkTaskBean getLinkTaskBean(int node)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
}
