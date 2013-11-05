package com.zhangwenan.app1.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-12
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public class Keyword {

    private int id;

    // 关键词名称
    private String name;


    // 关键词别名
    private String alias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
