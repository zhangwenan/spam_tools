package me.aipa.spam.service;

import me.aipa.spam.bean.*;
import me.aipa.spam.bean.MailSendFromDO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午7:16
 * To change this template use File | Settings | File Templates.
 */
public interface MailService {

    /**
     *  getMailSendList
     *
     * @return
     * @throws Exception
     */
    List<MailSendToDO> getMailSendToList(int start,int num)throws Exception;

    /**
     * getMailSendToCount
     *
     * @return
     * @throws Exception
     */
    int getMailSendToCount() throws Exception;

    /**
     *    getMailSendToListRand
     *
     * @param num
     * @return
     * @throws Exception
     */
    List<MailSendToDO>getMailSendToListRand(int num)throws  Exception;

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
    List<MailSendFromDO> getMailSendFromList(int start,int num) throws Exception;

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
    int insertMailSendFrom(MailSendFromDO mailSendFromDO) throws  Exception;

    /**
     * updateMailSendFromByAddress
     *
      * @param mailSendFromDO
     * @return
     * @throws Exception
     */
    int updateMailSendFromByAddress(MailSendFromDO mailSendFromDO) throws  Exception;

    /**
     * getMailSendHistoryList
     *
     * @param num
     * @return
     * @throws Exception
     */
    List<MailSendHistoryDO>getMailSendHistoryList(int num) throws Exception;

    /**
     * getMailSendHistoryByState
     *
     * @param state
     * @param num
     * @return
     * @throws Exception
     */
    List<MailSendHistoryDO>getMailSendHistoryByState(int state,int num) throws Exception;

    /**
     * getContentList
     *
     * @param num
     * @return
     * @throws Exception
     */
    List<MailContentDO>getContentList(int num) throws Exception;

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
     * delMailSendFromByAddress
     *
     * @param id
     * @return
     * @throws Exception
     */
    int delMailSendFromByAddress(int id) throws  Exception;
}
