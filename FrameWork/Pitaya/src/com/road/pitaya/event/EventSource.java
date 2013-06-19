/**
 * Date: Mar 19, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.event;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事件源
 * 
 * @author jiayi.wei
 */
public class EventSource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSource.class);

    /**
     * 监听都列表。同一事件，可能存在多个相同的监听者。
     */
    private Map<Integer, Collection<IEventListener>> listeners;

    private ReadWriteLock lock;

    public EventSource()
    {
        this.listeners = new ConcurrentHashMap<Integer, Collection<IEventListener>>();
        this.lock = new ReentrantReadWriteLock();
    }

    public void addListener(int eventType, IEventListener listener)
    {
        Collection<IEventListener> lstns = this.listeners.get(eventType);
        if (lstns == null)
        {
            try
            {
                // 加写锁确保没有并发问题。
                this.lock.writeLock().lock();

                lstns = this.listeners.get(eventType);
                if (lstns == null)
                {
                    // lstns = new ConcurrentSkipListSet<IEventListener>();
                    lstns = new LinkedList<IEventListener>();
                    lstns.add(listener);
                    this.listeners.put(eventType, lstns);
                }
                else
                {
                    lstns.add(listener);
                }
            }
            catch (Exception e)
            {
                LOGGER.error("", e);
            }
            finally
            {
                this.lock.writeLock().unlock();
            }
        }
        else
        {
            lstns.add(listener);
        }

        LOGGER.debug("Added a listener: {}, {}", eventType, listener);
    }

    public void removeListener(int eventType, IEventListener listener)
    {
        try
        {
            this.lock.writeLock().lock();

            Collection<IEventListener> lstns = this.listeners.get(eventType);
            if (lstns != null)
            {
                lstns.remove(listener);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
        finally
        {
            this.lock.writeLock().unlock();
        }

        LOGGER.debug("Removed a listener: {}, {}", eventType, listener);
    }

    public void notifyListners(EventArg arg)
    {
        try
        {
            this.lock.readLock().lock();

            Collection<IEventListener> lstns = this.listeners.get(arg.getEventType());
            for (IEventListener item : lstns)
            {
                item.onEvent(arg);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    public void notifyListners(int eventType)
    {
        try
        {
            this.lock.readLock().lock();

            Collection<IEventListener> lstns = this.listeners.get(eventType);
            for (IEventListener item : lstns)
            {
                item.onEvent(new EventArg(this, eventType));
            }
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }
}
