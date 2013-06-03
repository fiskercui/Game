/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.component.inf;

import java.util.List;

import com.road.dota.task.condition.BaseTaskCondition;
import com.road.entity.bean.LinkTaskBean;
import com.road.entity.bean.TaskBean;
import com.road.pitaya.component.IComponent;

/**
 * @author cookie.hu
 * @date 2013-5-21
 * @version 
 * 任务组件
 */
public interface ITaskComponent extends IComponent
{
    List<BaseTaskCondition> getTaskCondition(int taskID);
    
    List<TaskBean> getRewardTask();
    
    /**
     * 根据环数获取对应的环任务配置信息
     * @param node
     * @return
     */
    LinkTaskBean getLinkTaskBean(int node);

    /**
     * 获取环任务,根据玩家等级，以及任务子类型
     * @param taskType
     * @param level
     * @return
     */
    List<TaskBean> getLinkTaskByType(int taskSubType,int level);
}
