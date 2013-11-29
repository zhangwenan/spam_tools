package me.aipa.spam.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.aipa.spam.bean.IpCheckDO;
import me.aipa.spam.service.IpCheckService;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: lil Date: 13-11-5 Time: 下午5:01 To change this template use File |
 * Settings | File Templates.
 */
public class IpCheckServiceImpl implements IpCheckService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String ipGet() {

		return sqlSession.selectOne("IpCheckMapper.getBestIp");
	}

	public List<IpCheckDO> getIpListForCheck() {

		return sqlSession.selectList("IpCheckMapper.getIpList");
	}

	TimerTask task = new TimerTask() {
		public void run() {
			long i = sqlSession.selectOne("IpCheckMapper.countIpCheck");
			System.out.println("i=" + i);
			if (i == 0) {
				sqlSession.update("IpCheckMapper.upateAllIsCheck");

			}
			checkTimeTisk1();
		}
	};

	Timer timer = new Timer(true); // true

	@Override
	public void interruptCheck() {
		task.cancel();
		task = new TimerTask() {
			public void run() {
				long i = sqlSession.selectOne("IpCheckMapper.countIpCheck");
				System.out.println("i=" + i);
				if (i == 0) {
					sqlSession.update("IpCheckMapper.upateAllIsCheck");

				}
				checkTimeTisk1();
			}
		};

	}

	@Override
	public void checkTimeTisk(long intervalTime) {
		long period = intervalTime * 1000 * 60;
		timer.schedule(task, new Date(), period);// 从当前时间开始
													//
	}

	public void checkTimeTisk1() {
		List<IpCheckDO> list = getIpListForCheck();
		for (IpCheckDO ipCheck : list) {

			Long speed = checkWithPing(ipCheck.getIp());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			System.out.println(ipCheck.getIp() + " " + "speed=" + speed + (new Date()).toString());
			// speed 不为空 即为有回应 此时更新该IP
			if (speed != null) {
				IpCheckDO ipCheckDO = new IpCheckDO();
				ipCheckDO.setIp(ipCheck.getIp());
				ipCheckDO.setSpeed(Integer.valueOf(String.valueOf(speed)));
				ipCheckDO.setIsCheck(1);// 已检测 设置isCheck为1

				sqlSession.update("IpCheckMapper.upateSpeedByIp", ipCheckDO);
			} else {// 否则 删除该IP
				IpCheckDO ipCheckDO = new IpCheckDO();
				ipCheckDO.setIp(ipCheck.getIp());
				sqlSession.delete("IpCheckMapper.deleteByIp", ipCheckDO);
			}
		}

	}

	public Long checkWithPing(String address) {
		IpCheckDO ip = new IpCheckDO();
		String host;
		int port = 80;
		if (address.indexOf(":") == -1) {
			host = address;
		} else {
			String[] strings = address.split(":");
			host = strings[0];
			port = Integer.parseInt(strings[1]);
		}
		URLConnection conn;
		try {
			URL url = new URL("http://baidu.com");
			// 创建代理服务器
			InetSocketAddress addr = new InetSocketAddress(host, port);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
			long start = System.currentTimeMillis();
			conn = url.openConnection(proxy);
			InputStream in;
			in = conn.getInputStream();
			String s = IOUtils.toString(in);
			IOUtils.closeQuietly(in);

			long speed = System.currentTimeMillis() - start;
			if (s.indexOf("baidu") > 0) {
				ip.setSpeed(Integer.valueOf(String.valueOf(speed)));
				return speed;
			} else {
				return null;
			}
		} catch (java.net.ConnectException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public List<IpCheckDO> selectIpList(int page, int limit, int id, String ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("from", (page - 1) * limit);
		map.put("limit", limit);
		map.put("id", id);
		map.put("ip", ip);
		return sqlSession.selectList("IpCheckMapper.selectIpList", map);
	}

	@Override
	public Boolean delIpByid(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		sqlSession.delete("IpCheckMapper.deleteById", map);
		return Boolean.TRUE;
	}

	@Override
	public int countIpAll(int id, String ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("ip", ip);
		return sqlSession.selectOne("IpCheckMapper.countIpAll", map);
	}

	@Override
	public int updateLastTimeByIp(IpCheckDO ipCheckDO) {

		return sqlSession.update("IpCheckMapper.upateLastTimeByIp", ipCheckDO);
	}
}
