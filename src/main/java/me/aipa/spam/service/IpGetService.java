package me.aipa.spam.service;

import java.util.List;

import org.quartz.SchedulerException;

import me.aipa.spam.bean.IpGetDO;
/**
 * 
 * @author Administrator
 *
 */
public interface IpGetService {
	
	/**
	 * 获取ip接口开始
	 */
	void checkTimeTisk();
	
	/**
	 * 获取接口结束
	 */
	void getIpDown();
	
	/**
	 * 添加一个获取ip的网站
	 * @param ipGetDO
	 */
	void insertUrl(IpGetDO ipGetDO);
	
	/**
	 * 删除一个获取ip的网站
	 * @param id
	 */
	void deleteUrl(String id);
	
	/**
	 * 修改网站信息
	 * @param ipGetDO
	 */
	void changeUrl(IpGetDO ipGetDO);
	
	/**
	 * 获取网站列表
	 * @param page
	 * @param limit
	 * @param id
	 * @param domainName
	 */
	List<IpGetDO> getUrlList(int page, int limit, Integer id,String domainName);
	
	/**
	 * 获取数据总条数
	 * @param id
	 * @param domainName
	 * @return
	 */
	Integer getUrlCount(Integer id,String domainName);
	
	IpGetDO getUrl();
	
	void test() throws SchedulerException;

}
