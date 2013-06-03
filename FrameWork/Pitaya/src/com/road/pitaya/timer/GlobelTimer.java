/**
 * Date: 2013-5-14
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.timer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.road.pitaya.component.IComponent;

/**
 * 全局定时器实现
 * @author yip
 */
public class GlobelTimer implements IComponent
{
    public static final String NAME = "GlobelTimerComponent"; 
	/**
	 * 检查队列任务的最小间隔
	 */
	public static final long MIN_INTERVAL = 10L;
	/**
	 * 只执行一次的任务
	 */
	private Queue<TaskWrapper> allOnceTask;
	
	/**
	 * 执行任务的线程池
	 */
	private ExecutorService pool;
	
	/**
	 * 全局管理定时重复任务的线程池。任意一个线程异常不会影响另一个线程
	 */
	private static ScheduledExecutorService  executorService;
	
	/**
	 * 全局的时钟任务
	 */
	private TimerTask controlHandle;
	
    /**         任务队列的比较器                   */
    Comparator<TaskWrapper> taskComparator = null;
	
	/**
	 * 队列读写锁
	 */
	private Object objLock;
	
	/**
	 * 添加可重复定时任务（这种任务一般较少，直接新建一个Timer就可以了，不需要对其进行管理）
	 * @param task     执行的任务
	 * @param delay    延时多久
	 * @param period   周期
	 */
	public void addRepeatTask(ITask task, long delay, long period)
	{
		new RepeatTaskWrapper(task, delay, period);
	}

	/**
	 * 添加执行一定次数后取消的任务（这种任务一般较少，直接新建一个Timer就可以了，不需要对其进行管理）
	 * @param task     执行的任务
	 * @param delay    延时多久执行(相对于添加的时间)
	 * @param period   周期
	 * @param times    执行多少次后取消
	 */
	public void addScheduleTask(ITask task, long delay, long period, int repeatTimes)
	{
		if(repeatTimes == 1)//仅执行一次任务，放到线程池
		{
			addTask(task, delay);
			return;
		}
		new RepeatTaskWrapper(task, delay, period, repeatTimes);
	}

	/**
	 * 添加一次定时任务
	 * @param task 执行的任务
	 * @param delay 延迟多久执行
	 */
	public void addTask(ITask task, long delay)
	{
		TaskWrapper taskWrapper = new TaskWrapper(task, delay);
		synchronized (objLock)
		{
			this.allOnceTask.add(taskWrapper);
		}
	}
	
	/**
	 * 获取全局静态线程池
	 * @return
	 */
	public static ScheduledExecutorService getClock()
	{
		return GlobelTimer.executorService;
	}
	
	/**
	 * 获取时钟
	 * @return
	 */
	public static long getTickCount()
	{
		return System.currentTimeMillis();
	}


    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }


    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        this.taskComparator = new Comparator<TaskWrapper>()
        {
            @Override
            public int compare(TaskWrapper o1, TaskWrapper o2)
            {
                if(o1.getExecuteTick() < o2.getExecuteTick())
                {
                    return -1;
                }
                else if(o1.getExecuteTick() > o2.getExecuteTick())
                {
                    return 1;
                }
                return 0;
            }
        };
        executorService = Executors.newScheduledThreadPool(1);
        allOnceTask = new PriorityQueue<TaskWrapper>(100, taskComparator);//TODO:队列初始大小的设置，同一时间未到达运行时间的任务
        pool = Executors.newCachedThreadPool();//TODO:设定池的大小，参考弹弹堂游戏中BaseGame，但是池不够会有影响吗
        //考虑到任务是到时间后才放入线程池。所以池中的大小最多是同一时间仍然未完成的任务
        objLock = new Object();
        controlHandle = new TimerTask()//每个minInterVal执行的操作
        {
            @Override
            public void run()
            {
                synchronized(objLock)
                {
                    while(allOnceTask.size()>0 && allOnceTask.peek().canExecute())
                    {
                        pool.submit(allOnceTask.remove());
                    }
                }
            }
        };
 
        return true;
    }


    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        executorService.scheduleAtFixedRate(controlHandle, 0, GlobelTimer.MIN_INTERVAL, TimeUnit.MILLISECONDS);
        return true;
    }


    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
//        TODO:添加停止线程池的方式
    }


    /* (non-Javadoc)
     * @see com.road.pitaya.component.IComponent#destroy()
     */
    @Override
    public void destroy()
    {
//        TODO:添加移除组件的方式
    }
	
}
