package com.zhangwenan.app1.service;

import com.zhangwenan.app1.bean.Admin;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {

    Admin getAdminById(int id);

    Admin getAdmin(Admin admin);
}
