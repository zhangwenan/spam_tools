package me.aipa.spam.bean;

import java.util.Date;

/**
 * 检测IP User: lil Date: 13-11-13 Time: 下午2:49
 */
public class IpCheckDO {

	private int id; // 自增ID
	private String ip; // IP地址
	private Date lastUsed; // 该IP最后使用时间

	private Date gmtCreated; // IP入库时间
	private Integer speed; // IP的响应速度
	private int isCheck;// 已检测 该字段为1，未检测 该字段为0

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public int getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public void setId(int id) {
		this.id = id;
	}

}
