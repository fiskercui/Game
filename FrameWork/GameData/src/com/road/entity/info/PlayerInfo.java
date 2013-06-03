package com.road.entity.info;

import java.io.Serializable;

import com.road.entity.DataObject;


/**
 * @author volcano
 * @date 2013-5-27
 * @version
 * 
 */
public class PlayerInfo extends DataObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userID; // 玩家ID

	private String nickname;  //昵称
	
	private byte sex; // 角色性别

	private short career; // 角色职业

	private short level; // 角色等级

	private long gold; // 玩家金币
	
	private int exp; // 当前经验

	private int money; // 玩家的钻石(RMB等值物)

	private int guildID; // 公会ID

	private byte[] taskState;//任务状态
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public short getCareer() {
		return career;
	}

	public void setCareer(short career) {
		this.career = career;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public long getGold() {
		return gold;
	}

	public void setGold(long gold) {
		this.gold = gold;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getGuildID() {
		return guildID;
	}

	public void setGuildID(int guildID) {
		this.guildID = guildID;
	}

    /**
     * @return the taskState
     */
    public byte[] getTaskState()
    {
        return taskState;
    }

    /**
     * @param taskState the taskState to set
     */
    public void setTaskState(byte[] taskState)
    {
        this.taskState = taskState;
    }
	
}
