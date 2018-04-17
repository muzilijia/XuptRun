package com.muzi.xuptrun.model;

import com.muzi.xuptrun.bean.UserBean;

/**
 * Created by muzi on 2018/4/17.
 */

public interface IUserModel {
    void setUserName(String userName);
    void setPassWord(String passWord);
    void setEmail(String email);
    void setHeight(int height);
    void setWeight(int weight);
    void setTarget(int target);
    //通过userName读取user信息，返回一个UserBean
    UserBean load(String userName);
}
