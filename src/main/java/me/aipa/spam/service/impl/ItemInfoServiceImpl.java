package me.aipa.spam.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import job.GetItemJob;
import me.aipa.spam.Util;
import me.aipa.spam.bean.InfoDO;
import me.aipa.spam.bean.ItemDO;
import me.aipa.spam.module.screen.json.AbstractJsonScreen;
import me.aipa.spam.service.ItemInfoService;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Q币
 * 
 * @author Administrator
 * 
 */
public class ItemInfoServiceImpl implements ItemInfoService {

	@Autowired
	private SqlSession sqlSession;

	protected static Logger logger = LoggerFactory.getLogger(AbstractJsonScreen.class); // 日志

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void getItemInfoStart(String itemId) {
		ItemDO itemDO = sqlSession.selectOne("ItemInfoMapper.getOneItemDOForTask", itemId);// 获取要开始抓取的商品任务信息

		int time = itemDO.getIntervalTime();

		long intervals = time * 1000;// 将时间间隔转换成ms为单位
		String jobName = itemDO.getItemId() + "Name";
		String triggerName = itemDO.getItemId() + "Trigger";
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobDetail jobDetail = new JobDetailImpl(jobName, scheduler.DEFAULT_GROUP, GetItemJob.class);
			SimpleTrigger simpleTrigger = new SimpleTriggerImpl(triggerName, "tgroup");

			((SimpleTriggerImpl) simpleTrigger).setStartTime(new Date());// 开始时间
			((SimpleTriggerImpl) simpleTrigger).setRepeatInterval(intervals);// 间隔时间
			((SimpleTriggerImpl) simpleTrigger).setRepeatCount(10000);// 重复次数

			jobDetail.getJobDataMap().put("service", this);// 注入service 以便调用
			jobDetail.getJobDataMap().put("itemId", itemId);
			jobDetail.getJobDataMap().put("regprice", itemDO.getRegprice());
			jobDetail.getJobDataMap().put("regsell", itemDO.getRegsell());
			scheduler.scheduleJob(jobDetail, simpleTrigger);
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void shutDown(String itemId) {
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			String jobName = itemId + "Name";
			JobKey jobKey = new JobKey(jobName,scheduler.DEFAULT_GROUP);
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void handleInfo(String itemId, String regprice, String regsell) {
		// 获取价格
		String priceStr = getPrice(itemId, regprice);
		Double price = null;
		if (!priceStr.equals("") && null != priceStr) {
			price = Double.valueOf(priceStr);
		}
		//获取销量
		String sellNum = "";
		try {
			sellNum = getSellNum(itemId, regsell);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (null != price || null != sellNum || !sellNum.equals("")) {
			InfoDO infoDO = new InfoDO();
			infoDO.setGetTime(new Date());
			infoDO.setItemId(itemId);
			infoDO.setSellnum(sellNum);
			infoDO.setPrice(price);

			sqlSession.insert("ItemInfoMapper.insertInfo", infoDO);
		}

	}

	/**
	 * 获取Q币价格
	 * @param itemId
	 * @param regular
	 * @return
	 */
	public String getPrice(String itemId, String regular) {
		String url = "http://item.taobao.com/item.htm?id=" + itemId; // 商品链接

		Document document;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			logger.warn("url:[" + url + "] connect fail ,may be time out", e);
			return null;
		}
		
		String html = document.html(); // 获取当前页源代码  
		
		if (Util.isEmpty(html) || regular == null || regular.length() == 0) {
			return null;
		}
		String resultStr = "";

		Pattern pt = Pattern.compile(regular);
		Matcher matcher = pt.matcher(html);
		while (matcher.find()) {
			String str = matcher.group(1); // 只取第一组
			if (!Util.isEmpty(str)) {
				resultStr = str;
			}
		}
		return resultStr;
	}
	
	/**
	 * 获取Q币销量
	 * @param itemId
	 * @param regular
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getSellNum(String itemId, String regular) throws ClientProtocolException, IOException{
		//请求头部
		Header[] headers = new BasicHeader[1];
		Header header = new BasicHeader("referer","http://detail.tmall.com/item.htm");
		headers[0] = header;
		
		//实例化一个Httpclient的 
		DefaultHttpClient client = new DefaultHttpClient();
		//实例化一个post请求 
		HttpPost post = new HttpPost("http://mdskip.taobao.com/core/initItemDetail.htm?itemId="+itemId); 
		//设置头部 
		post.setHeaders(headers); 
		
		//实行请求并返回 
		 HttpResponse response = client.execute(post);         
		 HttpEntity  entity = response.getEntity(); 
		//取得返回的字符串
		 String html=EntityUtils.toString(entity);
		 
		 //正则匹配
		 if (Util.isEmpty(html) || regular == null || regular.length() == 0) {
				return null;
			}
			String resultStr = "";

			Pattern pt = Pattern.compile(regular);
			Matcher matcher = pt.matcher(html);
			while (matcher.find()) {
				String str = matcher.group(1); // 只取第一组
				if (!Util.isEmpty(str)) {
					resultStr = str;
				}
			}
			return resultStr;
	}

	@Override
	public void deleteInfo(String itemId, Date timeStart, Date timeEnd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("timeStart", timeStart);
		map.put("timeEnd", timeEnd);
		sqlSession.delete("ItemInfoMapper.deleteItems", map);
	}

	@Override
	public List<InfoDO> getInfoList(int page, int limit, String itemId, Date timeStart, Date timeEnd, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("timeStart", timeStart);
		map.put("timeEnd", timeEnd);
		map.put("title", title);
		map.put("limit", limit);
		map.put("from", (page - 1) * limit);

		List<InfoDO> infoList = sqlSession.selectList("ItemInfoMapper.getInfoList", map);
		return infoList;
	}

	@Override
	public Integer infoCount(int page, int limit, String itemId, Date timeStart,
			Date timeEnd, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("timeStart", timeStart);
		map.put("timeEnd", timeEnd);
		map.put("title", title);
		map.put("limit", limit);
		map.put("from", (page - 1) * limit);
		return sqlSession.selectOne("ItemInfoMapper.getInfoListCount", map);
	}

	@Override
	public boolean getItemTask(String itemId, String regprice, String regsell) {
		System.out.println("抓取的商品为：" + itemId);
		System.out.println("价格正则表达式为" + regprice);
		System.out.println("销量正则表达式为" + regsell);
		return true;
	}

	@Override
	public void saveItemTask(ItemDO itemDO) {
		sqlSession.insert("ItemInfoMapper.insertItem", itemDO);

	}

	@Override
	public boolean deleteItemTask(String itemId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		int i = sqlSession.delete("ItemInfoMapper.deleteItemTask", map);
		return true;

	}

	@Override
	public List<ItemDO> getItemTaskList(int page, int limit, String itemId, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("title", title);
		map.put("limit", limit);
		map.put("from", (page - 1) * limit);
		List<ItemDO> itemList = sqlSession.selectList("ItemInfoMapper.getItemList", map);
		return itemList;
	}

	@Override
	public boolean updateItemTask(ItemDO itemDO) {
		int i = sqlSession.update("IpCheckMapper.upateLastTimeByIp", itemDO);
		return true;

	}

	@Override
	public int countItemTask(int page, int limit, String itemId, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("title", title);
		map.put("limit", limit);
		map.put("from", (page - 1) * limit);
		int i = sqlSession.selectOne("ItemInfoMapper.countItemTask", map);
		return sqlSession.selectOne("ItemInfoMapper.countItemTask", map);

	}

	@Override
	public ItemDO selectDOByItemId(String itemId) {
		return sqlSession.selectOne("ItemInfoMapper.getOneItemDOForTask", itemId);
	}

}
