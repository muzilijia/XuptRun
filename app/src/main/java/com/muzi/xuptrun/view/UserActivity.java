package com.muzi.xuptrun.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muzi.xuptrun.R;

public class UserActivity extends AppCompatActivity implements IUserView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void setUserName(String userName) {

    }

    @Override
    public String getPassWord() {
        return null;
    }

    @Override
    public void setPassWord(String passWord) {

    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public int getTarget() {
        return 0;
    }

    @Override
    public void setTarget(int target) {

    }
}
