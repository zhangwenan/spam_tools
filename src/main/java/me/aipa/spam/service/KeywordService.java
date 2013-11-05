package me.aipa.spam.service;


import me.aipa.spam.bean.Keyword;
import me.aipa.spam.bean.ParamterMap;

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
