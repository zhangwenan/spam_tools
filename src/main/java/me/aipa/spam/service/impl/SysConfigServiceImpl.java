package me.aipa.spam.service.impl;

import me.aipa.spam.bean.SysConfig;
import me.aipa.spam.service.SysConfigService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-16
 * Time: 下午10:55
 * To change this template use File | Settings | File Templates.
 */
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public SysConfig getSysConfigByName(String name) {
        return sqlSession.selectOne("SysConfigMapper.getSysConfigByName", name);
    }
}
