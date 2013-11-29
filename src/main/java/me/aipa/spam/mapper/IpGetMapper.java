package me.aipa.spam.mapper;

/**
 * 调用 代理IP接口 User: lil Date: 13-11-13 Time: 下午14:52
 * 
 */
public interface IpGetMapper {
	/**
	 * 调用一个综合情况最优的IP
	 */

	String ipGet();

	// /**
	// * 检测IP是否可用
	// */
	// void ipCheck();

	/**
	 * 终止检测
	 */
	void interruptCheck();
}
