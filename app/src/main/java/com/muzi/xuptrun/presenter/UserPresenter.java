package com.muzi.xuptrun.presenter;

import com.muzi.xuptrun.bean.UserBean;
import com.muzi.xuptrun.model.IUserModel;
import com.muzi.xuptrun.model.UserModel;
import com.muzi.xuptrun.view.fragment.ThirdFragment.IUserView;

/**
 * Created by muzi on 2018/4/17.
 */

public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter (IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    public void saveUser(String userName, String email) {
        mUserModel.setUserName(userName);
        mUserModel.setEmail(email);
    }

    public void loadUser(String userName) {
        UserBean userBean = mUserModel.load(userName);
        mUserView.setUserName(userName);
        mUserView.setEmail(userBean.getEmail());
    }
}
