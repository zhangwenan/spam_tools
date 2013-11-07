package me.aipa.spam.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-5
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class QQAccount extends BaseDO{

    private String qq;

    private String password;

    public QQAccount() {
    }

    public QQAccount(String qq, String password) {
        this.qq = qq;
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
