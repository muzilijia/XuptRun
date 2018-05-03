package com.muzi.xuptrun.bean;


/**
 * Created by muzi on 2018/4/17.
 */

public class UserBean{
    //用户名
    private String userName;
    //用户密码
    private String passWord;
    //用户邮箱
    private String email;
    //用户身高cm
    private int height;
    //用户体重kg
    private double weight;
    //每天的运动目标km
    private double target;

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }
}
