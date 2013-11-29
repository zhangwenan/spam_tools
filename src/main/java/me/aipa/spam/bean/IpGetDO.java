package me.aipa.spam.bean;

import java.util.Date;

public class IpGetDO {
	private Integer id;					//自增长id
	private String url;						//获取代理ip地址
	private String detailRegular;					//列表页正则表达式
	private String ipRegular;						//ip地址正则表达式
	private String domainName;							//网站域名
	private Date gmtCreated;					//创建时间
	private int del;							//该网站暂时失效不用
	private int isDone;					//近期是否已采集过
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public int getIsDone() {
		return isDone;
	}
	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}
	
	public String getDetailRegular() {
		return detailRegular;
	}
	public void setDetailRegular(String detailRegular) {
		this.detailRegular = detailRegular;
	}
	public String getIpRegular() {
		return ipRegular;
	}
	public void setIpRegular(String ipRegular) {
		this.ipRegular = ipRegular;
	}
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
