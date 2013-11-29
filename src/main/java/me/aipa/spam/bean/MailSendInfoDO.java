package me.aipa.spam.bean;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-5 Time: 下午6:47 To
 * change this template use File | Settings | File Templates.
 */
public class MailSendInfoDO {

	/*
	 * 发送邮件的服务器地址
	 */
	private String mailServerHost;
	/*
	 * 发件箱地址
	 */
	private String sendFromAddress;
	/*
	 * 收件箱地址
	 */
	private String sendToAddress;
	/*
	 * 发件箱登陆用户名
	 */
	private String userName;
	/*
	 * 发件箱登陆密码
	 */
	private String password;
	/*
	 * 发送的邮件模板TITLE
	 */
	private String title;

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

	/*
	 * 发送的邮件模板内容
	 */
	private String content;

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getSendFromAddress() {
		return sendFromAddress;
	}

	public void setSendFromAddress(String sendFromAddress) {
		this.sendFromAddress = sendFromAddress;
	}

	public String getSendToAddress() {
		return sendToAddress;
	}

	public void setSendToAddress(String sendToAddress) {
		this.sendToAddress = sendToAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
