package me.aipa.spam.service.impl;

import me.aipa.spam.bean.QQAccount;
import me.aipa.spam.service.QQService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-5
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class QQServiceImpl implements QQService {
    @Autowired
    private SqlSession sqlSession;


    @Override
    public Boolean addQQ(QQAccount qqAccount) {
        sqlSession.insert("QQ.addQQ", qqAccount);
        return true;
    }

    @Override
    public Boolean delQQById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delQQByQQNumber(String qqNumber) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean updateQQ(QQAccount qqAccount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QQAccount getQQById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QQAccount getQQByQQNumber(String qqNumber) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<QQAccount> getQQListByPage(int page, int limit) {
        Map < String, Integer> map = new HashMap<String, Integer>();
        map.put("from", (page-1) * limit);
        map.put("limit", limit);
        return sqlSession.selectList("QQ.getQQList", map);
    }

    @Override
    public int countQQ() {
        return sqlSession.selectOne("QQ.countQQ");
    }
}
