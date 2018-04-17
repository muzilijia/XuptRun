package com.muzi.xuptrun.bean;

/**
 * Created by muzi on 2018/4/17.
 */

public class UserBean {
    //用户账号
    private String userName;
    //用户密码
    private String passWord;
    //用户邮箱
    private String email;
    //用户身高
    private int height;
    //用户体重
    private int weight;
    //每天的运动目标
    private int target;

    public UserBean(String userName, String passWord, String email, int height, int weight, int target) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.target = target;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
