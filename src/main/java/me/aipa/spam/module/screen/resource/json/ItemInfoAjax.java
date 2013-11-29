package me.aipa.spam.module.screen.resource.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import me.aipa.spam.bean.InfoDO;
import me.aipa.spam.bean.ItemDO;
import me.aipa.spam.service.ItemInfoService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;

/**
 * Q币信息抓取
 * 
 * @author Administrator
 * 
 */
public class ItemInfoAjax {

	@Autowired
	private ItemInfoService itemInfoService;
	@Autowired
	private HttpServletResponse httpServletResponse;

	/**
	 * 删除商品信息
	 * 
	 * @param itemId
	 * @param timeStart
	 * @param timeEnd
	 * @throws Exception
	 */
	public void doDeleteInfo(@Param("itemId") String itemId, @Param("timeStart") Date timeStart,
			@Param("timeEnd") Date timeEnd) throws Exception {
		itemInfoService.deleteInfo(itemId, timeStart, timeEnd);

		httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
	}

	/**
	 * 获取info页面
	 * 
	 * @param itemId
	 * @param timeStart
	 * @param timeEnd
	 * @param title
	 * @throws Exception
	 */
	public void doGetInfoList(@Param("pageIndex") int pageIndex, @Param("limit") int limit,
			@Param("itemId") String itemId, @Param("timeStart") Date timeStart, @Param("timeEnd") Date timeEnd,
			@Param("title") String title) throws Exception {
		int page = pageIndex + 1;
		httpServletResponse.setContentType("application/json");

		List<InfoDO> infoList = new ArrayList<InfoDO>();
		infoList = itemInfoService.getInfoList(page, limit, itemId, timeStart, timeEnd, title);
		Integer count = itemInfoService.infoCount(page, limit, itemId, timeStart, timeEnd, title);

		JSONObject jsonObject = new JSONObject();
		 jsonObject.put("rows", infoList);
         jsonObject.put("results", count);
		httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
	}

	public void doTest() throws Exception {
		itemInfoService.handleInfo("17250184058", "reservePrice' : '(.*)',\\s+'spuId", "sellCount\":(.*)},\"spe");
	}

	/**
	 * 抓取商品信息任务 开始
	 */
	public void doGetItemStart(@Param("itemId") String itemId) throws Exception {
		if ((!itemId.equals(null)) || (!itemId.equals(" "))) {
			itemInfoService.getItemInfoStart(itemId);
			httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");

		}
	}

	/**
	 * 根据页码，每页条数来获取 商品任务信息
	 * 
	 * @param pageIndex
	 * @param limit
	 * 
	 *            itemId,title 为额外的搜索条件
	 * @param itemId
	 * @param title
	 */
	public void doGetItemTask(@Param("pageIndex") int pageIndex, @Param("limit") int limit,
			@Param("itemId") String itemId, @Param("title") String title) {
		int page = pageIndex + 1;
		httpServletResponse.setContentType("application/json");

		List<ItemDO> itemList = itemInfoService.getItemTaskList(page, limit, itemId, title);
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", itemList);
			jsonObject.put("results", itemInfoService.countItemTask(page, limit, itemId, title));
			httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));

		} catch (IOException e) {

		} finally {
			try {
				httpServletResponse.getWriter().close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 添加或编辑ItemTask
	 * 
	 * @param isNew
	 * @param qq
	 * @param password
	 */
	public void doEditItemTask(@Param("itemId") String itemId, @Param("title") String title,
			@Param("intervalTime") int intervalTime, @Param("regprice") String regprice,
			@Param("regsell") String regsell) {
		try {

			ItemDO itemDO = new ItemDO();
			itemDO.setIntervalTime(intervalTime);
			itemDO.setItemId(itemId);
			itemDO.setTitle(title);
			itemDO.setRegprice(regprice);
			itemDO.setRegsell(regsell);

			// 判断该商品是否存在 任务表中
			ItemDO item = itemInfoService.selectDOByItemId(itemId);

			if (item == null) {// 保存
				itemInfoService.saveItemTask(itemDO);
				httpServletResponse.getWriter().write("保存商品任务信息" + "{\"success\":true}");
			} else {// 编辑
				boolean b = itemInfoService.updateItemTask(itemDO);
				if (b) {
					httpServletResponse.getWriter().write("编辑商品任务信息" + "{\"success\":true}");
				} else {
					httpServletResponse.getWriter().write("编辑商品任务信息" + "{\"fail\":true}");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除ItemTask
	 * 
	 * @param itemIds
	 */
	public void doDelItemTask(@Param("itemIds") String itemIds) {
		String[] itemTaskArr = itemIds.split(",");
		boolean b = false;
		if (itemTaskArr.length > 0) {
			for (int i = 0; i < itemTaskArr.length; i++) {
				b = itemInfoService.deleteItemTask(itemTaskArr[i]);
			}
			JSONObject r = new JSONObject();
			r.put("success", b);
			try {
				httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 停止商品检索
	 * @param itemIds
	 */
	public void doShutdown(@Param("itemId") String itemId) {
		itemInfoService.shutDown(itemId);
	}

}
