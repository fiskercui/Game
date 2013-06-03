/**
 * Date: 2013-5-14
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.timer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可重复的任务封装 
 * 
 * @author yip
 */
public class RepeatTaskWrapper
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RepeatTaskWrapper.class);
	/**
	 * 执行的任务
	 */
	ITask task;

	/**
	 * 重复执行的次数
	 */
	long repeatTimes;

	/**
	 * 添加的任务
	 */
	private Thread repeatTask;
	
	/**
	 * 用于控制程序停止
	 */
	private ScheduledFuture<?>  future;

	/**
	 * 创建一个可重复指定次数的任务
	 * 
	 * @param task
	 * @param delay
	 * @param period
	 */
	public RepeatTaskWrapper(ITask task, long delay, long period, long repeatTimes)
	{
		this.task = task;
		this.repeatTimes = repeatTimes;
		repeatTask = new Thread()
		{
			@Override
			public void run()
			{
				if(RepeatTaskWrapper.this.repeatTimes > 0)
				{
					try
					{
						--RepeatTaskWrapper.this.repeatTimes;// 防止因为异常而没有设置重复次数
						RepeatTaskWrapper.this.task.execute();
					}
					catch(Exception e)
					{
						LOGGER.error(e.toString());
					}
				}
				else
				{
					future.cancel(false);
				}
			}
		};
		future = GlobelTimer.getClock().scheduleAtFixedRate(repeatTask, delay, period, TimeUnit.MILLISECONDS);
	}

	/**
	 * 添加循环执行的任务
	 * 
	 * @param task2
	 * @param delay
	 * @param period
	 */
	public RepeatTaskWrapper(ITask task, long delay, long period)
	{
		this.task = task;
		repeatTask = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					RepeatTaskWrapper.this.task.execute();
				}
				catch(Exception e)
				{
					LOGGER.error(e.toString());
				}
			}
		};
		future = GlobelTimer.getClock().scheduleAtFixedRate(repeatTask, delay, period, TimeUnit.MILLISECONDS);
	}

}
