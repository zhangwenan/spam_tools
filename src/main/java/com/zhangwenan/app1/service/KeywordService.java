package com.zhangwenan.app1.service;


import com.zhangwenan.app1.bean.Keyword;
import com.zhangwenan.app1.bean.ParamterMap;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface KeywordService {

    List<Keyword> getKeywordList(ParamterMap paramterMap);

    Boolean prevEnough(ParamterMap paramterMap);

    Boolean nextEnough(ParamterMap paramterMap);

    int getFirstKeywordId();

    int getLastKeywordId();
}
