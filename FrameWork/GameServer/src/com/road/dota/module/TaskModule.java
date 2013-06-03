/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.dota.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.bll.properties.GameProperties;
import com.road.dota.component.TaskComponent;
import com.road.dota.component.inf.ITaskComponent;
import com.road.dota.module.inf.ITaskModule;
import com.road.dota.object.inf.IGamePlayer;
import com.road.dota.task.BaseTask;
import com.road.dota.type.ModuleType;
import com.road.dota.type.RewardTaskQualityType;
import com.road.dota.type.TaskType;
import com.road.entity.bean.LinkTaskBean;
import com.road.entity.bean.TaskBean;
import com.road.entity.info.DataObject.DataOption;
import com.road.entity.info.TaskDataInfo;
import com.road.pitaya.component.ComponentManager;
import com.road.util.ThreadSafeRandom;
import com.road.util.TimeUtil;

/**
 * @author yutao.chen
 * @date 2013-5-28
 * @version 用户任务模块
 */
public class TaskModule extends PlayerModule implements ITaskModule
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TaskModule.class.getName());

    private static final int REWARD_TASK_COUNT = 4;
    private static final int MAX_LINK=30;//可累加的环任务上限
    private static final int REWARD_INTERVAL = 30;// 悬赏任务刷新间隔，分钟
    private static final int FREE_REFRESH_COUNT = 3;
    /**
     *环任务环数上限
     *
     */
    private static final int LINK_NODE_MAX = 10;
    private static final int REFRESH_MONEY = 100;
    private AtomicInteger changeCount;
    private byte[] taskStates;
    private List<BaseTask> userTaskList;
    private List<BaseTask> clearList;// 已完成或放弃的任务
    private List<BaseTask> changeList;
    private List<TaskBean> rewardTask;
    private int currentRewardTaskID;
    private ThreadSafeRandom rand;
   // 本日剩余的悬赏任务个数
    private int rewardTaskCount = 10;
    //本日刷新的悬赏任务次数
    private int refreshRewradCount = 0;
    private Date lastRefreshDate;// 悬赏任务刷新时间
    private RewardTaskQualityType[] rewardTaskQuality;
    private Date rewardCompleteDate;// 上次完成悬赏任务时间
    private Date linkDate;// 上次环任务完成时间
    private int linkNode;// 当前环数
    private int linkCompleteCount= 10;// 当日环任务完成数
    private int currentLinkTaskID;
    private Object lock;

    /**
     * @param player
     * @param moduleType
     */
    public TaskModule(IGamePlayer player)
    {
        super(player, ModuleType.TASK);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean init()
    {
        if (player.getPlayerInfo().getTaskState() == null
                || player.getPlayerInfo().getTaskState().length == 0)
            player.getPlayerInfo().setTaskState(initTaskState());
        taskStates = player.getPlayerInfo().getTaskState().clone();
        clearList = new ArrayList<BaseTask>();
        changeCount = new AtomicInteger();
        changeList = new ArrayList<BaseTask>();
        rewardTask = new ArrayList<TaskBean>();
        return true;
    }

    /**
     * initialize task state whether is completed.
     */
    private byte[] initTaskState()
    {
        byte[] tempByte = new byte[500];
        for (int i = 0; i < 500; i++)
        {
            tempByte[i] = 0;
        }
        return tempByte;
    }

    /**
     * @param baseTask
     */
    private void onTaskChanged(BaseTask task)
    {
        if (task == null)
            return;

        if (!changeList.contains(task))
        {
            synchronized (changeList)
            {
                changeList.add(task);
            }
        }

        if (changeCount.get() <= 0 && changeList.size() > 0)
        {
            updateChanges();
        }
    }

    /**
     * 
     */
    private void beginChange()
    {
        changeCount.incrementAndGet();
    }

    /**
     * submit changes.
     */
    private void commitChange()
    {
        int change = changeCount.decrementAndGet();
        if (change < 0)
        {
            LOGGER.error("do you forget to invoke the beginChange() method");
            changeCount.set(0);
        }

        if (change <= 0 && changeList.size() > 0)
        {
            updateChanges();
        }
    }

    /**
     * 
     */
    private void updateChanges()
    {
        if (changeList.size() > 0)
        {
            List<BaseTask> list = new ArrayList<BaseTask>();
            byte[] states;
            synchronized (changeList)
            {
                list.addAll(changeList);
                changeList.clear();
                states = taskStates.clone();
            }

            // if (list.size() > 0 && states != null)
            // player.getOut().sendQuestChanges(states, list);
        }
    }

    @Override
    public boolean loadFromDB()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean saveIntoDB()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.ITaskModule#getTask(int)
     */
    @Override
    public BaseTask getTask(int taskID)
    {
        for (BaseTask task : userTaskList)
            if (task.getTaskTemplate().getTaskID() == taskID)
                return task;
        return null;
    }

    @Override
    public boolean finish(BaseTask baseTask, int selectedItem)
    {
        if(baseTask.canComplete() == false)
            return false;
        beginChange();
        // 从游戏中领取奖品
//        gamePlayer.beginAllChanges();
        try
        {
            // 固定奖励&选择奖励
//            List<TaskItemBean> awards = QuestMgr.getQuestGoods(baseTask.getUserTaskDataInfo());
//            List<ItemBean> propBg = new ArrayList<ItemBean>();
//            String msg = "";
            float rewardRate =1;
            if(baseTask.getTaskTemplate().getTaskType() == TaskType.REWARD)
            {
                rewardRate = getRewardTaskRate(baseTask.getUserTaskDataInfo().getTaskQuality());
            }
            TaskBean taskInfo = baseTask.getTaskTemplate();
            if(baseTask.finish())
            {
                if(taskInfo.getRewardGold() >= 0)
                {
                    int rewardGold = (int) (taskInfo.getRewardGold()*rewardRate);
                    rewardGold = player.addGold(rewardGold);
//                    msg = msg
//                            + LanguageMgr.getTranslation("Game.Server.Quests.FinishQuest.RewardGold",
//                                                         rewardGold) + " ";
                }
                if(taskInfo.getRewardExp() > 0)
                {
                    int exp = (int) (taskInfo.getRewardExp()*rewardRate);
                    exp = player.addExp(exp);
//                    msg = msg
//                            + LanguageMgr.getTranslation("Game.Server.Quests.FinishQuest.RewardReputation",
//                                                         popular) + " ";
                }
                //发送消息
//                player.getOut().SendMessage(MessageType.ALERT, msg);
                // 移除任务
                removeTask(baseTask);
                onFinish(baseTask);
                setTaskFinish(baseTask.getTaskTemplate().getTaskID());
                player.getPlayerInfo().setTaskState(taskStates.clone());
                onTaskChanged(baseTask);
            }
        }
        finally
        {
//            gamePlayer.commitAllChanges();
            commitChange();
        }

        return false;
    }

    private void onFinish(BaseTask baseTask)
    {
        // 移除任务
        removeTask(baseTask);
        // 悬赏任务完成
        if (baseTask == getTask(currentRewardTaskID))
        {
            currentRewardTaskID = -1;
            if (TimeUtil.dateCompare(rewardCompleteDate))
            {
                rewardTaskCount --;
            }
            else
            {
                rewardTaskCount = GameProperties.REWARD_TASK_MAX - 1;
            }
            rewardCompleteDate = TimeUtil.getSysteCurTime();
        }
        else if (baseTask == getTask(currentLinkTaskID))// 环任务完成
        {
            currentLinkTaskID = -1;
            linkNode = linkNode>=LINK_NODE_MAX? 1:++linkNode;
        }
        setTaskFinish(baseTask.getTaskTemplate().getTaskID());
    }

    /**
     * 设置一个任务完成
     * 
     * @param taskId
     * @return
     */
    private boolean setTaskFinish(int taskId)
    {
        if (taskId > taskStates.length * 8 || taskId < 1)
            return false;
        taskId--;
        int index = taskId / 8;
        int offset = taskId % 8;
        taskStates[index] |= (byte) (0x01 << offset);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.ITaskModule#addTask(int)
     */
    @Override
    public boolean addTask(TaskBean taskInfo)
    {
        String checkMsg = checkAddTask(taskInfo);

        BaseTask oldData = getTask(taskInfo.getTaskID());

        if ("".equals(checkMsg))
        {
            beginChange();
            if (oldData == null)
            {
                oldData = new BaseTask(new TaskDataInfo(), taskInfo);
                oldData.getUserTaskDataInfo().setOp(DataOption.INSERT);
            }
            oldData.reset();
            addTask(oldData);
            commitChange();
            return true;
        }
        else
        {
            // checkMsg = LanguageMgr.getTranslation(msg);
            return false;
        }
    }

    public boolean addRewardTask(int taskIndex)
    {
        if (rewardTask.size() < taskIndex)
            return false;
        if(TimeUtil.dateCompare(rewardCompleteDate) == false)
        {
            rewardTaskCount = GameProperties.REWARD_TASK_MAX;
        }
        if (rewardTaskCount <= 0)
            return false;
        if (rewardTaskQuality[taskIndex] == RewardTaskQualityType.NULL)
            return false;
        TaskBean taskBean = rewardTask.get(taskIndex);
        if (addTask(taskBean))
        {
            BaseTask oldData = getTask(taskBean.getTaskID());
            oldData.getUserTaskDataInfo().setTaskQuality(
                    rewardTaskQuality[taskIndex].getValue());
            rewardTaskQuality[taskIndex] = RewardTaskQualityType.NULL;// 清0，表示已接收
            return true;
        }
        return false;
    }

    /**
     * @param oldData
     */
    private void addTask(BaseTask oldData)
    {
        synchronized (userTaskList)
        {
            if (!userTaskList.contains(oldData))
            {
                userTaskList.add(oldData);
            }
        }
        oldData.getUserTaskDataInfo().setIsExist(true);
        oldData.addToPlayer(player);

        onTaskChanged(oldData);
    }

    /**
     * 检测是否可以添加任务
     * 
     * @param taskInfo
     * @return
     */
    protected String checkAddTask(TaskBean taskInfo)
    {
        String msg = "";

        // 当前任务是否存在
        if (taskInfo == null)
        {
            msg = "Game.Server.Quests.NoQuest";
            return msg;
        }
        // 判断用户已开始的当前任务
        BaseTask oldData = getTask(taskInfo.getTaskID());
        // 任务是否开始
        if (taskInfo.getBeginDate().after(TimeUtil.getSysteCurTime()))
            msg = "Game.Server.Quests.NoTime";

        // 任务是否结束
        else if (taskInfo.getEndDate().before(TimeUtil.getSysteCurTime()))
            msg = "Game.Server.Quests.TimeOver";

        // 任务是否未达到用户最小等级
        else if (player.getPlayerInfo().getLevel() < taskInfo.getNeedMinLevel())
            msg = "Game.Server.Quests.LevelLow";

        // 任务是否超出用户最高等级
        else if (player.getPlayerInfo().getLevel() > taskInfo.getNeedMaxLevel())
            msg = "Game.Server.Quests.LevelTop";

        // 前置任务是否完成
        else if (!taskInfo.getPreTaskID().equals("0,"))
        {
            String[] tempArry = taskInfo.getPreTaskID().split("\\,");
            for (int i = 0; i < tempArry.length; i++)
            {
                if (!isTaskFinish(Integer.valueOf(tempArry[i])))
                {
                    msg = "Game.Server.Quests.NoFinish";
                }
            }
        }

        // 存在相同,且未完成的任务
        else if ((oldData != null)
                && (!oldData.getUserTaskDataInfo().getIsComplete()))
            msg = "Game.Server.Quests.Have";
        // 任务已经完成,但是不可重复
        else if (isTaskFinish(taskInfo.getTaskID()) && !taskInfo.isCanRepeat())
        {
            msg = "Game.Server.Quests.NoRepeat";
        }

        else if (taskInfo.getTaskType() == TaskType.REWARD)
        {
            BaseTask task = getTask(currentRewardTaskID);
            if (task != null)
                msg = "Game.Server.Quests.Have";
        }
//        else if ((oldData != null)
//                && TimeUtil.dateCompare(oldData.getUserTaskDataInfo()
//                        .getCompletedDate(), TimeUtil.getSysteCurTime()) < taskInfo
//                        .getRepeatInterval()
//                && (oldData.getUserTaskDataInfo().getRepeatCount() <= 0))
//        {
//            msg = "Game.Server.Quests.Reset";
//        }
        return msg;
    }

    /**
     * @param isFinish
     * @return
     */
    private boolean isTaskFinish(int taskID)
    {
        if (taskID < 1 || taskStates == null || taskID > taskStates.length * 8)
            return false;

        taskID--;
        int index = taskID / 8;
        int offset = taskID % 8;
        int result = taskStates[index] & (0x01 << offset);
        return result != 0;
    }

    public boolean removeTask(BaseTask task)
    {
        boolean result = false;
        synchronized (lock)
        {
            if (userTaskList.remove(task))
            {
                if (task.getTaskTemplate().isCanRepeat())
                {
                    userTaskList.add(task);

                }
                else
                {
                    clearList.add(task);
                    task.getUserTaskDataInfo().setIsExist(false);
                }
                result = true;
            }
        }

        if (result)
        {
            task.removeFromPlayer(player);
            onTaskChanged(task);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.ITaskModule#refreshRewardTask()
     */
    @Override
    public boolean refreshRewardTask(boolean useMoney)
    {
        try
        {
            if (TimeUtil.dateCompare(lastRefreshDate,
                    TimeUtil.getSysteCurTime()) > REWARD_INTERVAL)
            {
                refreshRewardTask();
            }
            else if (refreshRewradCount < FREE_REFRESH_COUNT)
            {
                refreshRewradCount++;
                refreshRewardTask();
            }
            else if (useMoney
                    && player.getPlayerInfo().getMoney() > REFRESH_MONEY)
            {
                // 使用点券
                player.removeMoney(REFRESH_MONEY);
                refreshRewardTask();
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        // 发送更新的悬赏任务
        refreshRewardTask();
        return true;
    }

    /**
     * 悬赏任务刷新算法
     */
    private void refreshRewardTask()
    {
        lastRefreshDate = TimeUtil.getSysteCurTime();
        List<TaskBean> rewardTemp = ((TaskComponent) ComponentManager
                .getInstance().getComponent("Task")).getRewardTask();
        List<TaskBean> randTask = new ArrayList<TaskBean>();
        if (rewardTemp.size() <= 0)
            return;
        String[] qualityStr =GameProperties.REWARD_TASK__QUALITY_RATE.split("//|");
        for (int i = 0; i < REWARD_TASK_COUNT; i++)
        {
            int index = rand.next(rewardTemp.size());
            randTask.add(rewardTemp.get(index));
            //任务品质
            int qualityRand = rand.next(GameProperties.REWARD_TASK_MAX);
            for(int j=0; j < qualityStr.length;j++)
            {
                int rate = Integer.valueOf(qualityStr[i]);
                if(qualityRand  < rate)
                {
                    rewardTaskQuality[i] = RewardTaskQualityType.parse(j);
                    break;
                }
            }
        }
         rewardTask = randTask;
        // send rewardTask
    }
    
    
    private float getRewardTaskRate(byte taskQuality)
    {
        float taskRate = 1;
        String[] qualityStr =GameProperties.REWARD_TASK_BONUS_RATE.split("//|");
        if(qualityStr.length > taskQuality )
        {
            taskRate =Float.valueOf( qualityStr[taskQuality]);
        }
        return taskRate;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.module.inf.ITaskModule#addLinkTask()
     */
    @Override
    public boolean addLinkTask()
    {
        //过了一天，更新环任务次数
        if(TimeUtil.dateCompare(linkDate) == false)
        {
            int dayCount = TimeUtil.dateCompare(linkDate,TimeUtil.getSysteCurTime());
            int count = linkCompleteCount+LINK_NODE_MAX*dayCount;
            linkCompleteCount = count > 30 ? MAX_LINK : count;
            linkDate = TimeUtil.getSysteCurTime();
        }
        if(TimeUtil.dateCompare(linkDate)  && linkCompleteCount <= 0)
            return false;
        int randNum = rand.next(GameProperties.TASK_RANDOM_MAX); 
        ITaskComponent component= (ITaskComponent) ComponentManager
                .getInstance().getComponent("Task");
        LinkTaskBean linkTemplate = component .getLinkTaskBean(linkNode);
        int taskType= 0;
        //随机出一个任务类型
        if(randNum <= linkTemplate.getTaskSubRrobability1())
            taskType = linkTemplate.getTaskSubType1();
        else if(randNum <= linkTemplate.getTaskSubRrobability2())
            taskType = linkTemplate.getTaskSubType2();
        else if(randNum <= linkTemplate.getTaskSubRrobability3())
            taskType = linkTemplate.getTaskSubType3();
        else if(randNum <= linkTemplate.getTaskSubRrobability4())
            taskType = linkTemplate.getTaskSubType4();
        else if(randNum <= linkTemplate.getTaskSubRrobability5())
            taskType = linkTemplate.getTaskSubType5();
        //获得满足条件的任务
        List<TaskBean> taskList =  component.getLinkTaskByType(taskType,player.getPlayerInfo().getLevel());
        if(taskList.size() <= 0)
            return false;
        int randTaskNum = rand.next(taskList.size() );
        TaskBean taskBean = taskList.get(randTaskNum);
////        linkComLeteDate
//        TimeUtil.dateCompare(startDate, linkComLeteDate)
//        completeCount
        return addTask(taskBean);
    }

    @Override
    public void update(BaseTask baseTask)
    {
        if (baseTask != null)
        {
            onTaskChanged(baseTask);
        }
    }
}
