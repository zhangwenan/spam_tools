package me.aipa.spam.service;

import java.util.List;

import me.aipa.spam.bean.IpCheckDO;

/**
 * 
 * User: lil Date: 13-11-13 Time: 下午14:55 To change this template use File |
 * Settings | File Templates.
 */
public interface IpCheckService {
	/**
	 * 
	 * 获得一个综合情况最优的IP
	 */
	String ipGet();

	/**
	 * 定时任务
	 */
	void checkTimeTisk(long intervalTime);

	/**
	 * 中断检测
	 */
	void interruptCheck();

	/**
	 * 根据条件搜索IP
	 */
	List<IpCheckDO> selectIpList(int page, int limit, int id, String ip);

	/**
	 * 删除IP
	 */
	public Boolean delIpByid(String qqNumber);

	/**
	 * 获得全部的IP数量
	 */
	public int countIpAll(int id, String ip);

	/**
	 * 更新IP最近使用时间
	 */
	public int updateLastTimeByIp(IpCheckDO ipCheckDO);

}
