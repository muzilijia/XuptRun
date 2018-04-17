package com.muzi.xuptrun.model;

import android.util.SparseArray;

import com.muzi.xuptrun.bean.UserBean;

/**
 * Created by muzi on 2018/4/17.
 */

public class UserModel implements IUserModel {

    //用户账号
    private String mUserName;
    //用户密码
    private String mPassWord;
    //用户邮箱
    private String mEmail;
    //用户身高
    private int mHeight;
    //用户体重
    private int mWeight;
    //每天的运动目标
    private int mTarget;

    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public void setPassWord(String passWord) {
        mPassWord = passWord;
    }

    @Override
    public void setEmail(String email) {
        mEmail = email ;
    }

    @Override
    public void setHeight(int height) {
        mHeight = height;
    }

    @Override
    public void setWeight(int weight) {
        mWeight = weight;
    }

    @Override
    public void setTarget(int target) {
        mTarget = target;
    }

    @Override
    public UserBean load(String userName) {
        return null;
    }
}