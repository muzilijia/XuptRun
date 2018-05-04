package com.muzi.xuptrun.view.fragment.ThirdFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.muzi.xuptrun.R;
import com.muzi.xuptrun.view.activity.LoginActivity;

import java.util.List;

/**
 * Created by muzi on 2018/4/18.
 */

public class ThirdFragment extends Fragment implements View.OnClickListener {
    private EditText t_username,t_email,t_height,t_weight,t_target;
    private String username,email,height,weight,target,userdataId;
    private Button edit,exit;

    public ThirdFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third, container, false);
        initView(view);
        initData();

        edit.setOnClickListener(this);
        exit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_edit:
                height = t_height.getText().toString();
                weight = t_weight.getText().toString();
                target = t_target.getText().toString();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //userId持久化，昵称邮箱不可更改
                        AVObject userdata = AVObject.createWithoutData("UserData", userdataId);
                        userdata.put("height", height);
                        userdata.put("weight", weight);
                        userdata.put("target", target);
                        userdata.saveInBackground();
                        t_height.setText(height);
                        t_weight.setText(weight);
                        t_target.setText(target);
                    }
                });
                break;

            case R.id.but_exit:
                AVUser.logOut();// 清除缓存用户对象
                AVUser currentUser = AVUser.getCurrentUser();// 现在的 currentUser 是 null 了
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;

            default:
                break;
        }
    }

    void initView(View view) {
        edit = (Button) view.findViewById(R.id.but_edit);
        exit = (Button) view.findViewById(R.id.but_exit);
        t_username = (EditText) view.findViewById(R.id.t_username);
        t_email = (EditText) view.findViewById(R.id.t_email);
        t_height = (EditText) view.findViewById(R.id.t_height);
        t_weight = (EditText) view.findViewById(R.id.t_weight);
        t_target = (EditText) view.findViewById(R.id.t_target);
    }

    void initData() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            // 获取用户信息
            username = currentUser.getUsername();
            email = currentUser.getEmail();
            t_username.setText(username);
            t_email.setText(email);
        }
        //根据用户查到关联的userdata
        AVQuery<AVObject> userdata = new AVQuery<>("UserData");
        userdata.include("username");
        userdata.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null && list.size() > 0) {
                    AVObject avObject = list.get(0);
                    height = avObject.getString("height");
                    weight = avObject.getString("weight");
                    target = avObject.getString("target");
                    t_height.setText(height);
                    t_weight.setText(weight);
                    t_target.setText(target);
                } else {
                    AVObject userdata = new AVObject("UserData");
                    userdata.put("username", AVUser.getCurrentUser());
                    userdata.put("height", "170");
                    userdata.put("weight", "60");
                    userdata.put("target", "3");
                    userdata.saveInBackground();
                    userdataId = userdata.getObjectId();
                    t_height.setText("170");
                    t_weight.setText("60");
                    t_target.setText("3");
                }
            }
        });
    }
}
