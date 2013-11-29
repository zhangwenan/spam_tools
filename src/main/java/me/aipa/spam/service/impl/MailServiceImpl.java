package me.aipa.spam.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import job.MailSend;
import me.aipa.spam.bean.MailContentDO;
import me.aipa.spam.bean.MailSendFromDO;
import me.aipa.spam.bean.MailSendHistoryDO;
import me.aipa.spam.bean.MailSendInfoDO;
import me.aipa.spam.bean.MailSendToDO;
import me.aipa.spam.service.IpCheckService;
import me.aipa.spam.service.MailService;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-5 Time: 下午7:16 To
 * change this template use File | Settings | File Templates.
 */
public class MailServiceImpl implements MailService {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private IpCheckService ipCheckService;

	@Override
	public List<MailSendToDO> getMailSendToList(int start, int num) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("num", num);
		return sqlSession.selectList("MailSendToMapper.getMailSendToList", map);
	}

	@Override
	public int getMailSendToCount() throws Exception {
		return sqlSession.selectOne("MailSendToMapper.getMailSendToCount");
	}

	@Override
	public List<MailSendToDO> getMailSendToListRand(int start, int num) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("num", num);
		return sqlSession.selectList("MailSendToMapper.getMailSendToListRand", map);
	}

	@Override
	public int insertMailSendTo(String address) throws Exception {
		if (checkMailSendToAddressRepeat(address) == 0) {
			return sqlSession.insert("MailSendToMapper.insertMailSendTo", address);
		} else {
			return 0;
		}
	}

	@Override
	public int checkMailSendToAddressRepeat(String address) throws Exception {
		return sqlSession.selectOne("MailSendToMapper.checkMailSendToAddressRepeat", address);
	}

	@Override
	public List<MailSendToDO> getMailSendToByAddress(String address) throws Exception {
		return sqlSession.selectList("MailSendToMapper.getMailSendToByAddress", address);
	}

	@Override
	public List<MailSendFromDO> getMailSendFromList(int start, int num) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("num", num);
		return sqlSession.selectList("MailSendFromMapper.getMailSendFromList", map);
	}

	@Override
	public int getMailSendFromCount() throws Exception {
		return sqlSession.selectOne("MailSendFromMapper.getMailSendFromCount");
	}

	@Override
	public List<MailSendFromDO> getMailSendFromListRand(int num) throws Exception {
		return sqlSession.selectList("MailSendFromMapper.getMailSendFromListRand", num);
	}

	@Override
	public List<MailSendFromDO> getMailSendFromByAddress(String address) throws Exception {
		return sqlSession.selectList("MailSendFromMapper.getMailSendFromByAddress", address);
	}

	@Override
	public int checkMailSendFromAddressRepeat(String address) throws Exception {
		return sqlSession.selectOne("MailSendFromMapper.checkMailSendFromAddressRepeat", address);
	}

	@Override
	public int insertMailSendFrom(MailSendFromDO mailSendFromDO) throws Exception {
		if (checkMailSendFromAddressRepeat(mailSendFromDO.getAddress()) == 0) {
			return sqlSession.insert("MailSendFromMapper.insertMailSendFrom", mailSendFromDO);
		} else {
			return 0;
		}
	}

	@Override
	public int updateMailSendFromById(MailSendFromDO mailSendFromDO) throws Exception {
		if (checkMailSendFromAddressRepeat(mailSendFromDO.getAddress()) == 1) {
			return sqlSession.update("MailSendFromMapper.updateMailSendFrom", mailSendFromDO);
		} else {
			return 0;
		}
	}

	@Override
	public List<MailSendHistoryDO> getMailSendHistoryList(int num) throws Exception {
		return sqlSession.selectOne("MailSendHistoryMapper.getMailSendHistoryList", num);
	}

	@Override
	public List<MailSendHistoryDO> getMailSendHistoryByState(int state, int num) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("state", state);
		map.put("num", num);
		return sqlSession.selectList("MailSendHistoryMapper.getMailSendHistoryByState", map);
	}

	@Override
	public List<MailContentDO> getContentList(int num) throws Exception {
		return sqlSession.selectList("MailContentMapper.getContentList", num);
	}

	@Override
	public int insertMailContent(MailContentDO mailContentDO) throws Exception {
		return sqlSession.insert("MailContentMapper.insertMailContent", mailContentDO);
	}

	@Override
	public int updateMailContentById(MailContentDO mailContentDO) throws Exception {
		return sqlSession.update("MailContentMapper.updateMailContentById", mailContentDO);
	}

	@Override
	public int deleteMailContentById(int id) throws Exception {
		return sqlSession.update("MailContentMapper.deleteMailContentById", id);
	}

	@Override
	public int delMailSendFromById(int id) throws Exception {
		return sqlSession.update("MailSendFromMapper.delMailSendFromById", id);
	}

	@Override
	public int delMailSendToById(int id) throws Exception {
		return sqlSession.update("MailSendToMapper.delMailSendToById", id);
	}

	@Override
	public int updateMailSendToById(MailSendToDO mailSendToDO) throws Exception {
		return sqlSession.update("MailSendToMapper.updateMailSendToById", mailSendToDO);
	}

	@Override
	public int countMailContent(int id, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("title", title);
		return sqlSession.selectOne("MailContentMapper.countMailContent", map);
	}

	@Override
	public List<MailContentDO> getMailContentList(int page, int limit, int id, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("from", (page - 1) * limit);
		map.put("limit", limit);
		map.put("id", id);
		map.put("title", title);

		return sqlSession.selectList("MailContentMapper.getContentList", map);
	}

	@Override
	public boolean sendMails() throws Exception {

		MailSendInfoDO mailInfo = new MailSendInfoDO();// 邮件信息
		MailSendFromDO sendFromDO = getFromDO();
		MailSendToDO sendToDO = getToDO();

		int fromSuccessSum = sendFromDO.getSuccessCount();// 发件箱成功次数
		int fromFailSum = sendFromDO.getFailCount();// 发件箱失败次数
		int toSuccessSum = sendToDO.getSuccessCount();// 收件箱成功次数
		int toFailSum = sendToDO.getFailCount();// 收件箱失败次数

		mailInfo.setSendFromAddress(sendFromDO.getAddress());

		mailInfo.setUserName(sendFromDO.getUserName());

		mailInfo.setPassword(sendFromDO.getPass());

		MailContentDO mailContent = getContentRand();// 邮件模板

		mailInfo.setTitle(mailContent.getTitle());

		mailInfo.setContent(mailContent.getContent());

		mailInfo.setSendToAddress(sendToDO.getAddress());

		sendToDO.setLastTime(new Date());// 收件箱最近使用时间
		sendFromDO.setLastTime(new Date());// 发件箱最近使用时间

		boolean b = sendMail(mailInfo);// 发送邮件
		System.out.println("发送邮件" + b);

		if (b) {
			toSuccessSum++;// 收件箱成功次数+1
			fromSuccessSum++;// 发件箱成功次数+1
			sendToDO.setSuccessCount(toSuccessSum);
			sendFromDO.setSuccessCount(fromSuccessSum);

			updateSendToLastTimeSuccessById(sendToDO);// 更新 收件箱 最近使用时间
														// 和成功次数

			updateFromLastTimeSuccessById(sendFromDO);// 更新 发件箱
														// 最近使用时间
														// 和成功次数

		} else {
			toFailSum++;
			fromFailSum++;
			sendToDO.setFailCount(toFailSum);
			sendFromDO.setFailCount(fromFailSum);
			updateToLastTimeFailById(sendToDO);
			updateFromLastTimeFailById(sendFromDO);

		}
		System.out.println("fromSuccessSum=" + fromSuccessSum + " time=" + new Date());

		return b;
	}

	/**
	 * 发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendMail(MailSendInfoDO mailInfo) {
		String smtpHostName = "smtp." + (mailInfo.getUserName()).split("@")[1];
		HtmlEmail email = new HtmlEmail();

		try {
			// SMTP发送服务器的域名：
			email.setHostName(smtpHostName);
			// 字符编码集的设置
			email.setCharset("gbk");
			// 收件人的邮箱
			email.addTo(mailInfo.getSendToAddress());// 2784671042@qq.com

			// 发送人的邮箱
			email.setFrom(mailInfo.getSendFromAddress(), "爱啪网");
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mailInfo.getUserName(), mailInfo.getPassword());
			// 邮件主题
			email.setSubject(mailInfo.getTitle());
			// 邮件内容
			email.setMsg(mailInfo.getContent());
			email.setSSLOnConnect(true);

			// 发送

			email.send();

			System.out.println("邮件发送成功!" + mailInfo.getSendToAddress());
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败!");
			return false;
		}

	}

	/**
	 * 随机取一个发件箱
	 * 
	 */

	public MailSendFromDO getFromDO() {
		return sqlSession.selectOne("MailSendFromMapper.getMailSendFromRand");
	}

	/**
	 * 随机取一个收件箱
	 * 
	 */

	public MailSendToDO getToDO() {
		return sqlSession.selectOne("MailSendToMapper.getMailSendToRand");
	}

	/**
	 * 随机产生一个邮件模板DO
	 * 
	 * @param id
	 * 
	 */
	public MailContentDO getContentRand() {
		return sqlSession.selectOne("MailContentMapper.getMailContentDORand");
	}

	/**
	 * 更新 收件箱的最近使用时间 成功次数
	 * 
	 * @param MailSendFromDO
	 * @throws SchedulerException
	 * 
	 */
	public void updateSendToLastTimeSuccessById(MailSendToDO sendToDO) {
		sqlSession.update("MailSendToMapper.updateLastTimeSuccessById", sendToDO);
	}

	/**
	 * 更新 收件箱的最近使用时间 失败次数
	 * 
	 * @param MailSendFromDO
	 * @throws SchedulerException
	 * 
	 */
	public void updateToLastTimeFailById(MailSendToDO sendToDO) {
		sqlSession.update("MailSendToMapper.updateLastTimeFailById", sendToDO);
	}

	/**
	 * 更新发件箱的 最近使用时间 成功次数
	 * 
	 * @param MailSendFromDO
	 * 
	 */
	public void updateFromLastTimeSuccessById(MailSendFromDO sendFromDO) {
		sqlSession.update("MailSendFromMapper.updateLastTimeAndSuccessCountById", sendFromDO);
	}

	/**
	 * 更新发件箱的 最近使用时间 失败次数
	 * 
	 * @param MailSendFromDO
	 * 
	 */
	public void updateFromLastTimeFailById(MailSendFromDO sendFromDO) {
		sqlSession.update("MailSendFromMapper.updateLastTimeAndFailCountById", sendFromDO);
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public void mailSendStart(int interval) {
		int mailSendToCount = sqlSession.selectOne("MailSendToMapper.getMailSendToCount");// 获取收件箱个数
		if (mailSendToCount > 0) {
			mailSendToCount = mailSendToCount - 1;// 定时任务重复次数 （全部收件箱发送一次为一轮）
			long intervals = interval * 1000;// 将时间间隔转换成ms为单位
			Scheduler scheduler;
			try {
				scheduler = StdSchedulerFactory.getDefaultScheduler();
				JobDetail jobDetail = new JobDetailImpl("mailJob", scheduler.DEFAULT_GROUP, MailSend.class);
				SimpleTrigger simpleTrigger = new SimpleTriggerImpl("trigger", "tgroup");

				((SimpleTriggerImpl) simpleTrigger).setStartTime(new Date());
				((SimpleTriggerImpl) simpleTrigger).setRepeatInterval(intervals);
				((SimpleTriggerImpl) simpleTrigger).setRepeatCount(mailSendToCount);

				jobDetail.getJobDataMap().put("service", this);// 注入service 以便调用
				scheduler.scheduleJob(jobDetail, simpleTrigger);

				scheduler.start();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void mailSendDown() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobKey jobKey = new JobKey("mailJob",scheduler.DEFAULT_GROUP);
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();

		}
	}
}
