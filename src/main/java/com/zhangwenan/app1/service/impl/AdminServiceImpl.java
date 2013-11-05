package com.zhangwenan.app1.service.impl;

import com.zhangwenan.app1.bean.Admin;
import com.zhangwenan.app1.mapper.AdminMapper;
import com.zhangwenan.app1.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Admin getAdminById(int id) {

        return sqlSession.selectOne("AdminMapper.getAdminById", id);
    }

    @Override
    public Admin getAdmin(Admin admin) {
        return sqlSession.selectOne("AdminMapper.getAdmin", admin);
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
