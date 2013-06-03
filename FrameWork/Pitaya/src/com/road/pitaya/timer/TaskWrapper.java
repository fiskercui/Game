/**
 * Date: 2013-5-14
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务封装
 *   通过线程池管理
 * 
 * @author yip
 */
public class TaskWrapper implements Runnable
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskWrapper.class);
	/**
	 * 执行任务的时间
	 */
	private long executeTick;
	
	/**
	 * 执行的任务
	 */
	private ITask task;
	
	/**
	 * 创建一个执行一次的任务
	 */
	public TaskWrapper(ITask task,long delay)
	{
		this.task = task;
		this.executeTick = GlobelTimer.getTickCount() + delay;
		LOGGER.info(" init | {}",executeTick);
	}
	
	/**
	 * 任务能否执行
	 * @return
	 */
	public boolean canExecute()
	{
		return this.executeTick <= GlobelTimer.getTickCount();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		this.task.execute();
	}

	public long getExecuteTick()
	{
		return this.executeTick;
	}
}
