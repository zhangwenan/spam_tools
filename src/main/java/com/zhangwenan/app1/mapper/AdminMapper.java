package com.zhangwenan.app1.mapper;


import com.zhangwenan.app1.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午9:59
 * To change this template use File | Settings | File Templates.
 */
public interface AdminMapper {

    @Select("SELECT id,username,password,salt FROM mil_administrator WHERE id = #{id}")
    Admin getAdminById(@Param("id") int id);
}
