package me.aipa.spam.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-5 Time: 下午6:47 To
 * change this template use File | Settings | File Templates.
 */
public class MailSendToDO {
	private int id;
	/*
	 * 收件邮箱地址
	 */
	private String address;
	/*
	 * 成功次数
	 */
	private int successCount;
	/*
	 * 失败次数
	 */
	private int failCount;
	/*
	 * 上次使用时间（接收）
	 */
	private Date lastTime;
	/*
	 * 是否删除（逻辑）
	 */
	private int del;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
