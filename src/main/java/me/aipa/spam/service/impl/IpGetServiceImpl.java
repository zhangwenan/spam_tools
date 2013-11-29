package me.aipa.spam.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.aipa.spam.Util;

import me.aipa.spam.bean.IpGetDO;
import me.aipa.spam.module.screen.json.AbstractJsonScreen;
import me.aipa.spam.service.IpGetService;

/**
 * ip地址获取接口
 * @author Administrator
 *
 */
public class IpGetServiceImpl implements IpGetService{
	@Autowired
    private SqlSession sqlSession;
	
	protected static Logger logger = LoggerFactory.getLogger(AbstractJsonScreen.class); // 日志
	
	@Override
	public IpGetDO getUrl() {
		return sqlSession.selectOne("IpGetMapper.getUrlOne");
	}
	
	//测试使用
	public void getUrlOnly() {

		IpGetDO ipGetDO = getUrl();
//		System.out.println(ipGetDO.getId());
	}
	
	TimerTask task = new TimerTask() {//第一次任务
		public void run() {
			getIpsByUrls();
		}
	};

	Timer timer = new Timer(true); // true

	@Override
	public void checkTimeTisk() {
		long period = 30000;

		timer.schedule(task, new Date(), period);// 从当前时间开始 每隔10S执行一次task任务
	}

	@Override
	public void getIpDown() {
		task.cancel();						//停止当前任务
		task = new TimerTask(){					//重新设定任务
			public void run() {
				getIpsByUrls();
			}
		};
	}

	public  void getIpsByUrls() {
		IpGetDO ipGetDO = sqlSession.selectOne("IpGetMapper.getUrlOne");
		
		if(ipGetDO == null){
			sqlSession.update("IpGetMapper.changeUrlStateUnDone");  //重置网站状态为 0 --未执行
		}else{
			sqlSession.update("IpGetMapper.changeUrlStateDone",ipGetDO.getId());//标示该网站已执行 1
			
			String url = ipGetDO.getUrl();//获取网站地址
			Set<String> detaiUrlSet = getDetailUrlOrIpByUrl(url,ipGetDO.getDetailRegular(),ipGetDO.getId().toString());//获取网站详情页地址
			for(Iterator<String> iter = detaiUrlSet.iterator() ; iter.hasNext();) {
					String detailUrl = iter.next();
	
					Set<String> ips =getDetailUrlOrIpByUrl(detailUrl,ipGetDO.getIpRegular(),"");//获取详情页下ip地址
					
					if(ips.isEmpty()){
							continue;
					}
						
					for(Iterator<String> iter1 = ips.iterator() ; iter1.hasNext();){  //ip插入数据库
							Map<String,Object> map = new HashMap<String,Object>();
							map.put("ip", iter1.next());
							map.put("gmtCreated", new Date());
							
							sqlSession.insert("IpGetMapper.insertIp", map);
					}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> getDetailUrlOrIpByUrl(String url,String regular,String id){
		Document document;
		try {
			document = Jsoup.connect(url).get();
		}
		catch (IOException e) {
			if(!id.equals("")){
				sqlSession.update("IpGetMapper,changeUrlStateDel",id);
			}
			System.out.println("链接错误，skip " + url + "==>" + e);
			logger.warn("url:[" + url + "] connect fail ,may be time out", e);
			return Collections.EMPTY_SET;
		}
		
		String html = document.html();    //获取当前页源代码

		if (Util.isEmpty(html) || regular == null || regular.length() == 0 ) {
			return Collections.EMPTY_SET;
		}
		Set<String> result = new HashSet<String>();
		html = html.replaceAll(" +", "").replaceAll("\n", "");		//替换所有空格和回车
		
		Pattern pt=Pattern.compile(regular);
		Matcher matcher = pt.matcher(html);
		while (matcher.find()) {
			String str = matcher.group(1); //只取第一组
			if (!Util.isEmpty(str)) {
				result.add(str);
			}
		}
		return result;
	}
	
	@Override
	public void insertUrl(IpGetDO ipGetDO){
		sqlSession.insert("IpGetMapper.insertUrl",ipGetDO);
	}
	
	@Override
	public void deleteUrl(String  id){
		sqlSession.delete("IpGetMapper.deleteUrl", id);
	}

	@Override
	public void changeUrl(IpGetDO ipGetDO) {
		sqlSession.update("IpGetMapper.updateUrl", ipGetDO);
	}

	@Override
	public List<IpGetDO> getUrlList(int page, int limit, Integer id, String domainName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("domainName", domainName);
		map.put("limit", limit);
		map.put("from", (page - 1) * limit );
		
		return sqlSession.selectList("IpGetMapper.getUrls", map);
	}

	@Override
	public Integer getUrlCount(Integer id, String domainName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("domainName", domainName);
		return sqlSession.selectOne("IpGetMapper.getUrlsCount", map);
	}

	@Override
	public void test() throws SchedulerException{
//		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
//		TriggerKey triggerKey = new TriggerKey("jobTrigger");
//		CronTriggerBean trigger = (CronTriggerBean)scheduler.getTrigger(triggerKey);
//		String cronException = "";
//		String originCronExpression = trigger.getCronExpression();
//		
//		scheduler.rescheduleJob(triggerKey, newTrigger);
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
		System.out.println(scheduler.getJobGroupNames());
		
	}

}
