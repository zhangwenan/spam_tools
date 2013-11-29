package me.aipa.spam.module.screen.resource.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import me.aipa.spam.bean.IpCheckDO;
import me.aipa.spam.module.screen.json.AbstractJsonScreen;
import me.aipa.spam.service.IpCheckService;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-7 Time: 下午4:11 To
 * change this template use File | Settings | File Templates.
 */
public class IpMonitorAjax extends AbstractJsonScreen {
	@Autowired
	private IpCheckService ipCheckService;
	@Autowired
	private HttpServletResponse httpServletResponse;

	/**
	 * 检测IP 定时任务
	 * 
	 */
	public void doIpMonitor(@Param("intervalTime") long intervalTime) throws Exception {
		ipCheckService.checkTimeTisk(intervalTime);
		// List<IpCheckDO> ipList = ipCheckService.getIpList();
		// ipCheckService.checkIp(ipList);
		httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
	}

	/**
	 * 终止IP检测定时任务
	 * 
	 */
	public void doEndIpMonitor() throws Exception {
		ipCheckService.interruptCheck();

		httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
	}

	/**
	 * 获取一个最优的IP
	 * 
	 */
	public void doGetBetterIp() throws Exception {
		String ip = ipCheckService.ipGet();
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ip", ip);
			httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
			// httpServletResponse.getWriter().write(jsonObject);

		} catch (IOException e) {

		} finally {
			try {
				httpServletResponse.getWriter().close();
			} catch (IOException e) {

			}
		}

	}

	/**
	 * 根据页码，每页条数来获取Ip
	 * 
	 * @param pageIndex
	 * @param limit
	 * 
	 *            id,IP 为额外的搜索条件
	 * @param id
	 * @param IP
	 */
	public void doGetIpList(@Param("pageIndex") int pageIndex, @Param("limit") int limit, @Param("id") int id,
			@Param("ip") String ip) {
		int page = pageIndex + 1;
		httpServletResponse.setContentType("application/json");

		List<IpCheckDO> ipList = ipCheckService.selectIpList(page, limit, id, ip);
		int total = ipCheckService.countIpAll(id, ip);
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", ipList);
			jsonObject.put("results", total);
			httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
			// httpServletResponse.getWriter().write(jsonObject);

		} catch (IOException e) {

		} finally {
			try {
				httpServletResponse.getWriter().close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 删除ip
	 * 
	 * @param ids
	 */
	public void doDelIp(@Param("ids") String ids) {
		String[] idArr = ids.split(",");
		if (idArr.length > 0) {
			for (int i = 0; i < idArr.length; i++) {
				ipCheckService.delIpByid(idArr[i]);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", Boolean.TRUE);
			try {
				httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
