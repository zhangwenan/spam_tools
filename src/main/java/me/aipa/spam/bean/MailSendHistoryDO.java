package me.aipa.spam.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-7 Time: 下午1:53 To
 * change this template use File | Settings | File Templates.
 */
public class MailSendHistoryDO {
	private int id;
	/*
	 * 发件邮箱
	 */
	private String sendFrom;
	/*
	 * 收件邮箱
	 */
	private String sendTo;
	/*
	 * 发送时间
	 */
	private Date sendTime;
	/*
	 * 发送状态
	 */
	private int state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
