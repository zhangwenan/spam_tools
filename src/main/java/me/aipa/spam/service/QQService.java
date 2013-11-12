package me.aipa.spam.service;

import me.aipa.spam.bean.QQAccount;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-5
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public interface QQService {

    /**
     * 添加一个QQ账号
     * @param qqAccount
     * @return
     */
    Boolean addQQ(QQAccount qqAccount);


    /**
     * 根据自增长id，来删除QQ账号
     * @param id
     * @return
     */
    Boolean delQQById(int id);


    /**
     * 删除某个QQ号码
     * @param qqNumber
     * @return
     */
    Boolean delQQByQQNumber(String qqNumber);


    /**
     * 更新某个QQ
     * @param qqAccount
     * @return
     */
    Boolean updateQQ(QQAccount qqAccount);



    /**
     * 根据自增长的id，来获取QQ
     * @param id
     * @return
     */
    QQAccount getQQById(int id);



    /**
     * 根据QQ号码，来获取QQ
     * @param qqNumber
     * @return
     */
    QQAccount getQQByQQNumber(String qqNumber);


    /**
     * 根据条件，获取QQ列表
     * @param page
     * @param limit
     * @param id
     * @param qq
     * @return
     */
    List<QQAccount> getQQList(int page, int limit, int id, String qq);


    /**
     * 统计QQ个数
     * @return
     */
    int countQQ();
}
