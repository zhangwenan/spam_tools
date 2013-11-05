package com.zhangwenan.app1.service.impl;

import com.zhangwenan.app1.bean.Admin;
import com.zhangwenan.app1.bean.Keyword;
import com.zhangwenan.app1.bean.ParamterMap;
import com.zhangwenan.app1.mapper.AdminMapper;
import com.zhangwenan.app1.mapper.KeywordMapper;
import com.zhangwenan.app1.service.KeywordService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Keyword> getKeywordList(ParamterMap paramterMap) {
        return sqlSession.selectList("KeywordMapper.getKeywordList", paramterMap);
    }

    @Override
    public Boolean prevEnough(ParamterMap paramterMap) {
        List<Integer> idList = sqlSession.selectList("KeywordMapper.getPrevIdList", paramterMap);
        if(idList.size() < paramterMap.getLength()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean nextEnough(ParamterMap paramterMap) {
        List<Integer> idList = sqlSession.selectList("KeywordMapper.getNextIdList", paramterMap);
        if(idList.size() < paramterMap.getLength()){
            return false;
        }
        return true;
    }

    @Override
    public int getFirstKeywordId() {
        return sqlSession.selectOne("KeywordMapper.getFirstId");
    }

    @Override
    public int getLastKeywordId() {
        return sqlSession.selectOne("KeywordMapper.getLastId");
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
