package me.aipa.spam.bean;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-7 Time: 下午2:49 To
 * change this template use File | Settings | File Templates.
 */
public class MailContentDO {
	private int id;
	/*
	 * 邮件标题
	 */
	private String title;
	/*
	 * 邮件内容
	 */
	private String content;
	/*
	 * 创建时间
	 */
	private Date addTime;
	/*
	 * 是否删除
	 */
	private int del;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddTime() {
		return DateFormat.getDateInstance().format(addTime);
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
