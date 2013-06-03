/**
 * Date: 2013-5-14
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.timer;

/**
 * 添加的任务，所有的任务都需实现该接口
 *  任务线程中不能有sleep来阻塞。
 * @author yip
 */
public interface ITask
{
	/**
	 * 
	 * 任务要执行的操作
	 */
	void execute();
}
