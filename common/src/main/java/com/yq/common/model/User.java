package com.yq.common.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class User  {
    @Id
    protected Long id;
    private String name;
    private String accountNumber;
    private String pwd;
    private String token;
    private int state;//状态 1登录中 0退出登录

    @Generated(hash = 798285436)
    public User(Long id, String name, String accountNumber, String pwd,
                String token, int state) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.pwd = pwd;
        this.token = token;
        this.state = state;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    public static UserDao getUerDao(){
//        return GreenDaoManager.getInstance().getmDaoSession().getUserDao();
//    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
