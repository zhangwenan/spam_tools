package me.aipa.spam.service;

import java.util.List;

import me.aipa.spam.bean.MailContentDO;
import me.aipa.spam.bean.MailSendFromDO;
import me.aipa.spam.bean.MailSendHistoryDO;
import me.aipa.spam.bean.MailSendToDO;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-5 Time: 下午7:16 To
 * change this template use File | Settings | File Templates.
 */
public interface MailService {

	/**
	 * getMailSendList
	 * 
	 * @return
	 * @throws Exception
	 */
	List<MailSendToDO> getMailSendToList(int start, int num) throws Exception;

	/**
	 * getMailSendToCount 获取所有可用收件箱地址
	 * 
	 * @return
	 * @throws Exception
	 */
	int getMailSendToCount() throws Exception;

	/**
	 * getMailSendToListRand 随机分页
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<MailSendToDO> getMailSendToListRand(int start, int num) throws Exception;

	/**
	 * insertMailSendTo
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	int insertMailSendTo(String address) throws Exception;

	/**
	 * checkAddressRepeat
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	int checkMailSendToAddressRepeat(String address) throws Exception;

	/**
	 * getMailSendToByAddress
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	List<MailSendToDO> getMailSendToByAddress(String address) throws Exception;

	/**
	 * getMailSendFromList
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<MailSendFromDO> getMailSendFromList(int start, int num) throws Exception;

	/**
	 * getMailSendFromCount
	 * 
	 * @return
	 * @throws Exception
	 */
	int getMailSendFromCount() throws Exception;

	/**
	 * getMailSendFromListRand
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<MailSendFromDO> getMailSendFromListRand(int num) throws Exception;

	/**
	 * getMailSendFromByAddress
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	List<MailSendFromDO> getMailSendFromByAddress(String address) throws Exception;

	/**
	 * checkMailSendFromAddressRepeat
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	int checkMailSendFromAddressRepeat(String address) throws Exception;

	/**
	 * insertMailSendFrom
	 * 
	 * @param mailSendFromDO
	 * @return
	 * @throws Exception
	 */
	int insertMailSendFrom(MailSendFromDO mailSendFromDO) throws Exception;

	/**
	 * updateMailSendFromById
	 * 
	 * @param mailSendFromDO
	 * @return
	 * @throws Exception
	 */
	int updateMailSendFromById(MailSendFromDO mailSendFromDO) throws Exception;

	/**
	 * getMailSendHistoryList
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<MailSendHistoryDO> getMailSendHistoryList(int num) throws Exception;

	/**
	 * getMailSendHistoryByState
	 * 
	 * @param state
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<MailSendHistoryDO> getMailSendHistoryByState(int state, int num) throws Exception;

	/**
	 * getContentList
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<MailContentDO> getContentList(int num) throws Exception;

	/**
	 * insertMailContent
	 * 
	 * @param mailContentDO
	 * @return
	 * @throws Exception
	 */
	int insertMailContent(MailContentDO mailContentDO) throws Exception;

	/**
	 * updateMailContentById
	 * 
	 * @param mailContentDO
	 * @return
	 * @throws Exception
	 */
	int updateMailContentById(MailContentDO mailContentDO) throws Exception;

	/**
	 * deleteMailContentById
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteMailContentById(int id) throws Exception;

	/**
	 * delMailSendFromById
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delMailSendFromById(int id) throws Exception;

	/**
	 * delMailSendToById
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delMailSendToById(int id) throws Exception;

	/**
	 * updateMailSendToById
	 * 
	 * @param mailSendToDO
	 * @return
	 * @throws Exception
	 */
	int updateMailSendToById(MailSendToDO mailSendToDO) throws Exception;

	/**
	 * 统计邮件模板个数
	 * 
	 * @return
	 */
	int countMailContent(int id, String title);

	/**
	 * 根据条件，获取邮件模板列表
	 * 
	 * @param page
	 * @param limit
	 * @param id
	 * @param title
	 * @return
	 */
	List<MailContentDO> getMailContentList(int page, int limit, int id, String title);

	/**
	 * 根据条件 发送邮件
	 * 
	 * @param page
	 * @param limit
	 * @param id
	 * @param title
	 * @return
	 */
	boolean sendMails() throws Exception;

	/**
	 * 开始发送邮件调用接口
	 * @param interval
	 */
	void mailSendStart(int interval);

	/**
	 * 结束邮件发送
	 */
	void mailSendDown();

}
