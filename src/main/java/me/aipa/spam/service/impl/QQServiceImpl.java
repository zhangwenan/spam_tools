package me.aipa.spam.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.aipa.spam.bean.QQAccount;
import me.aipa.spam.service.QQService;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: wenlie Date: 13-11-5 Time: 下午5:01 To change
 * this template use File | Settings | File Templates.
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
	public Boolean batchAddQQ(List<QQAccount> qqAccountList) {
		sqlSession.insert("QQ.batchAddQQ", qqAccountList);
		return true;
	}

	@Override
	public Boolean delQQById(int id) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public Boolean delQQByQQNumber(String qqNumber) {
		sqlSession.delete("QQ.delQQByQQNumber", qqNumber);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateQQ(QQAccount qqAccount) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public QQAccount getQQById(int id) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public QQAccount getQQByQQNumber(String qqNumber) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public List<QQAccount> getQQList(int page, int limit, int id, String qq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("from", (page - 1) * limit);
		map.put("limit", limit);
		map.put("id", id);
		map.put("qq", qq);

		return sqlSession.selectList("QQ.getQQList", map);
	}

	@Override
	public int countQQ(int id, String qq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("qq", qq);
		return sqlSession.selectOne("QQ.countQQ", map);
	}
}
