/**
 * Date: 2013-5-14
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.timer;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于测试的任务
 * @author yip
 */
public class DemoTask implements ITask
{

	public static final Logger LOGGER = LoggerFactory.getLogger(DemoTask.class);
	
	public DemoTask()
	{
		this.inner = "default";
	}
	public DemoTask(String inner)
	{
		this.inner = inner;
	}
	/* (non-Javadoc)
	 * @see com.road.pitaya.ITask#execute()
	 */
	private String inner;
	@Override
	public void execute()
	{

		LOGGER.debug("{}\t|{}",inner,GlobelTimer.getTickCount());

	}

	
    /**
     * 测试方法
     */
    public static void main(String args[])
    {
//    	GlobelTimer.getInstance();
        GlobelTimer timer = new GlobelTimer();
        timer.initialize();
        timer.start();
    	LOGGER.debug("{}",GlobelTimer.getTickCount());
    	
    	//测试schedule和Repeat的任务
//    	DemoTask task1 = new DemoTask("Schedule");
    	DemoTask task = new DemoTask("outTer");
    	DemoTask task2 = new DemoTask("Clock");
//    	GlobelTimer.getInstance().addScheduleTask(task1, 0, 1000, 2);
    	timer.addRepeatTask(task2, 0, 1000);
//    	
//    	GlobelTimer.getInstance().addTask(task, 5000);
//    	
//    	try
//    	{
//			TimeUnit.SECONDS.sleep(5);
//		}
//    	catch (InterruptedException e)
//    	{
//			e.printStackTrace();
//		}
//    	
    	//测试两个不同时间的任务
//    	DemoTask task3 = new DemoTask("Ten");
//    	GlobelTimer.getInstance().addTask(task3, 5000);
    	try
    	{
			TimeUnit.SECONDS.sleep(3);
		}
    	catch (InterruptedException e)
    	{
			e.printStackTrace();
		}
    	DemoTask task4 = new DemoTask("last");
    	timer.addTask(task4, 0);
    	while(true)
    	{
    		timer.addTask(task, 10000);
    		try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
    	}
    }

}
