/**
 * Date: May 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.game;


/**
 * 
 * @author jiayi.wei
 */
public interface ISequenceTask 
{
    public String getSequenceTaskName();
    
    <T extends Runnable> void addCommandTask(T task);

    void finishOneCommandTask();
}
