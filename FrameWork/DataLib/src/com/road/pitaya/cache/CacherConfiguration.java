/**
 * Date: 2013-5-27
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.cache;

import org.dom4j.Element;

/**
 * 
 * @author jinjin.chen
 */
public class CacherConfiguration
{
    // ----------------abcacher-----------------------
    public static final long DELAY_TIME = 0;
    public static final long REPEAT_PERIOD = 60 * 1000;
    // ----------------ehcacher-----------------------
    public static final int MAX_CACHER_IN_CACHERMANAGER = 0;// 0表示没有限制
    public static final int MAX_WRITE_DELAY = 2;
    public static final int RATE_LIMIT_PER_SECOND = 5;
    public static final boolean WRITE_COALESCING = true;
    public static final boolean WRITE_BATCHING = true;
    public static final int WRITE_BATCH_SIZE = 1000;
    public static final int RETRY_ATTEMPTS = 20;
    public static final int RETRY_ATTEMPT_DELAY_SECONDS = 2;
    public static final boolean IS_PERSISTENT = true;
    // ----------------end-----------------------

    // ----------------common-----------------------
    private String cacheName;
    // ----------------common-----------------------

    // ----------------abcacher-----------------------
    private long delayTime = DELAY_TIME;
    private long repeatPeriod = REPEAT_PERIOD;

    // ----------------ehcacher-----------------------
    private int maxCacherInCacheManager = MAX_CACHER_IN_CACHERMANAGER;

    private int maxWriteDelay = MAX_WRITE_DELAY;
    private int rateLimitPerSecond = RATE_LIMIT_PER_SECOND;
    private boolean writeCoalescing = WRITE_COALESCING;
    private boolean writeBatching = WRITE_BATCHING;
    private int writeBatchSize = WRITE_BATCH_SIZE;
    private int retryAttempts = RETRY_ATTEMPTS;
    private int retryAttemptDelaySeconds = RETRY_ATTEMPT_DELAY_SECONDS;

    private boolean isPersistent = IS_PERSISTENT;

    // ----------------end-----------------------

    public CacherConfiguration(String cacheName)
    {
        this.cacheName = cacheName;
    }

    public CacherConfiguration(Element element)
    {
        this.cacheName = element.getName();

        if (element.elementText("DelayTime") != null)
        {
            setDelayTime(Long.parseLong(element.elementText("DelayTime")));
        }
        if (element.elementText("RepeatPeriod") != null)
        {
            setRepeatPeriod(Long.parseLong(element.elementText("RepeatPeriod")));
        }
        if (element.elementText("MaxWriteDelay") != null)
        {
            this.setMaxWriteDelay(Integer.parseInt(element
                    .elementText("MaxWriteDelay")));
        }
        if (element.elementText("RateLimitPerSecond") != null)
        {
            this.setRateLimitPerSecond(Integer.parseInt(element
                    .elementText("RateLimitPerSecond")));
        }
        if (element.elementText("WriteCoalescing") != null)
        {
            this.setWriteCoalescing(Boolean.parseBoolean(element
                    .elementText("WriteCoalescing")));
        }
        if (element.elementText("WriteBatching") != null)
        {
            this.setWriteBatching(Boolean.parseBoolean(element
                    .elementText("WriteBatching")));
        }
        if (element.elementText("WriteBatchSize") != null)
        {
            this.setWriteBatchSize(Integer.parseInt(element
                    .elementText("WriteBatchSize")));
        }

        if (element.elementText("RetryAttempts") != null)
        {
            this.setRetryAttempts(Integer.parseInt("RetryAttempts"));
        }

        if (element.elementText("RetryAttemptDelaySeconds") != null)
        {
            this.setRetryAttemptDelaySeconds(Integer
                    .parseInt("RetryAttemptDelaySeconds"));
        }

        if (element.elementText("IsPersistent") != null)
        {
            this.setIsPersistent(Boolean.parseBoolean("IsPersistent"));
        }
    }

    public long getDelayTime()
    {
        return delayTime;
    }

    public void setDelayTime(long delayTime)
    {
        this.delayTime = delayTime;
    }

    public long getRepeatPeriod()
    {
        return repeatPeriod;
    }

    public void setRepeatPeriod(long repeatPeriod)
    {
        this.repeatPeriod = repeatPeriod;
    }

    public String getCacheName()
    {
        return cacheName;
    }

    public int getMaxWriteDelay()
    {
        return maxWriteDelay;
    }

    public void setMaxWriteDelay(int maxWriteDelay)
    {
        this.maxWriteDelay = maxWriteDelay;
    }

    public int getRateLimitPerSecond()
    {
        return rateLimitPerSecond;
    }

    public void setRateLimitPerSecond(int rateLimitPerSecond)
    {
        this.rateLimitPerSecond = rateLimitPerSecond;
    }

    public boolean isWriteCoalescing()
    {
        return writeCoalescing;
    }

    public void setWriteCoalescing(boolean writeCoalescing)
    {
        this.writeCoalescing = writeCoalescing;
    }

    public boolean isWriteBatching()
    {
        return writeBatching;
    }

    public void setWriteBatching(boolean writeBatching)
    {
        this.writeBatching = writeBatching;
    }

    public int getWriteBatchSize()
    {
        return writeBatchSize;
    }

    public void setWriteBatchSize(int writeBatchSize)
    {
        this.writeBatchSize = writeBatchSize;
    }

    public int getRetryAttempts()
    {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts)
    {
        this.retryAttempts = retryAttempts;
    }

    public int getRetryAttemptDelaySeconds()
    {
        return retryAttemptDelaySeconds;
    }

    public void setRetryAttemptDelaySeconds(int retryAttemptDelaySeconds)
    {
        this.retryAttemptDelaySeconds = retryAttemptDelaySeconds;
    }

    public int getMaxCacherInCacheManager()
    {
        return maxCacherInCacheManager;
    }

    public void setMaxCacherInCacheManager(int maxCacherInCacheManager)
    {
        this.maxCacherInCacheManager = maxCacherInCacheManager;
    }

    public boolean isPersistent()
    {
        return isPersistent;
    }

    public void setIsPersistent(boolean isPersistent)
    {
        this.isPersistent = isPersistent;
    }

}
