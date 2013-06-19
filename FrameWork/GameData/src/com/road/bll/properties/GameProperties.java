/**
 * Date: 2013-5-31
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.bll.properties;

/**
 * 参数配置
 * @author yutao.chen
 */
public class GameProperties
{
    
    @ConfigPropertyAnnotation(defaultValue = "30|60|85|100", description = "悬赏任务品质获取比例", key = "rewardTaskQualityRate", type = String.class)
    public static String REWARD_TASK__QUALITY_RATE;
    
    @ConfigPropertyAnnotation(defaultValue = "1|1.5|2|2.5", description = "悬赏任务品质奖励比例", key = "rewardTaskBonusRate", type = String.class)
    public static String REWARD_TASK_BONUS_RATE;
    
    @ConfigPropertyAnnotation(defaultValue = "20", description = "悬赏任务每日上限", key = "rewardTaskMax", type = Integer.class)
    public static int REWARD_TASK_MAX =20;
    
    @ConfigPropertyAnnotation(defaultValue = "20", description = "任务事件随机数基值", key = "taskRandomMax", type = Integer.class)
    public static int TASK_RANDOM_MAX = 100;
}
