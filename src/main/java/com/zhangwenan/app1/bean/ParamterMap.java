package com.zhangwenan.app1.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-17
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class ParamterMap {

    private int startId;

    private int length;

    public ParamterMap() {
    }

    public ParamterMap(int startId, int length) {
        this.startId = startId;
        this.length = length;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
