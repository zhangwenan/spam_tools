package com.zhangwenan.app1.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-17
 * Time: 下午1:58
 * To change this template use File | Settings | File Templates.
 */
public class SiteConfig {

    private int id;

    private String domain;

    private String siteName;

    private String siteDesc;

    private String listTitleTemplate;

    private String listDescTemplate;

    private Date lastUpdatedTime;

    private int lastUpdatedId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
    }

    public String getListTitleTemplate() {
        return listTitleTemplate;
    }

    public void setListTitleTemplate(String listTitleTemplate) {
        this.listTitleTemplate = listTitleTemplate;
    }

    public String getListDescTemplate() {
        return listDescTemplate;
    }

    public void setListDescTemplate(String listDescTemplate) {
        this.listDescTemplate = listDescTemplate;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getLastUpdatedId() {
        return lastUpdatedId;
    }

    public void setLastUpdatedId(int lastUpdatedId) {
        this.lastUpdatedId = lastUpdatedId;
    }
}
