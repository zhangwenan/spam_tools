package me.aipa.spam.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: danlan Date: 13-11-7 Time: 上午1:39 To change
 * this template use File | Settings | File Templates.
 */
public class MailSendFromDO {
	private int id;
	/**
	 * 发送邮箱地址
	 */
	private String address;
	/**
	 * 邮箱登陆用户名
	 */
	private String userName;
	/**
	 * 邮箱登陆密码
	 */
	private String pass;
	/**
	 * SMTP服务器地址
	 */
	private String host;
	/**
	 * 成功次数
	 */
	private int successCount;
	/**
	 * 失败次数
	 */
	private int failCount;
	/**
	 * 上次发送时间
	 */
	private Date lastTime;
	/**
	 * 是否删除
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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
