/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.module.inf;

import com.road.dota.task.BaseTask;
import com.road.entity.bean.TaskBean;

/**
 * @author cookie.hu
 * @date 2013-5-23
 * @version 
 * 人物相关的任务模块
 */
public interface ITaskModule
{
    /**
     * 根据任务ID返回任务
     * @param taskID
     * @return
     */
	public BaseTask getTask(int taskID);
	
    public boolean finish(BaseTask baseTask, int selectedItem);
	
	/**
	 * 添加任务
	 * @param taskID
	 * @return
	 */
	public boolean addTask(TaskBean taskInfo);
	
	/**
	 * 添加悬赏任务
	 * @param taskIndex
	 * @return
	 */
	  public boolean addRewardTask(int taskIndex);
	  
	  /**
	   * 添加环任务
	   * @return
	   */
	  public boolean  addLinkTask();
	
	/**
	 * 刷新悬赏任务
	 * @return
	 */
	public boolean refreshRewardTask(boolean useMoney);

    /**
     * 任务刷新
     * @param baseTask
     */
    public void update(BaseTask baseTask);
	
}
