package com.zhangwenan.app1.mapper;

import com.zhangwenan.app1.bean.Keyword;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-12
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public interface KeywordMapper {


    @Select("SELECT id,name,alias FROM mil_word WHERE id >= #{id} limit #{length} ORDER BY id ASC")
    List<Keyword> getKeywordListById(int id, int length);
}
