package com.muzi.xuptrun.view.fragment.ThirdFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.muzi.xuptrun.R;
import com.muzi.xuptrun.presenter.UserPresenter;
import com.muzi.xuptrun.view.activity.LoginActivity;

/**
 * Created by muzi on 2018/4/18.
 */

public class ThirdFragment extends Fragment implements View.OnClickListener, IUserView {
    private TextView t_username,t_email,t_height,t_weight,t_target;
    private Button edit;
    private UserPresenter mUserPresenter;

    public ThirdFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        initView(view);
        mUserPresenter = new UserPresenter(this);
        edit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_edit:

                break;
            default:
                break;
        }
    }

    @Override
    public String getUserName() {
        return t_username.getText().toString();
    }

    @Override
    public void setUserName(String userName) {
        t_username.setText(userName);
    }

    @Override
    public String getEmail() {
        return t_email.getText().toString();
    }

    @Override
    public void setEmail(String email) {
        t_email.setText(email);
    }

    @Override
    public int getHeight() {
        return Integer.parseInt(t_height.getText().toString());
    }

    @Override
    public void setHeight(int height) {
        t_height.setText(height);
    }

    @Override
    public double getWeight() {
        return Double.parseDouble(t_weight.getText().toString());
    }

    @Override
    public void setWeight(double weight) {
        t_weight.setText(weight+"");
    }

    @Override
    public double getTarget() {
        return Double.parseDouble(t_target.getText().toString());
    }

    @Override
    public void setTarget(double target) {
        t_target.setText(target+"");
    }

    void initView(View view) {
        edit = (Button) view.findViewById(R.id.but_edit);
        t_username = (TextView) view.findViewById(R.id.t_username);
        t_email = (TextView) view.findViewById(R.id.t_email);
        t_height = (TextView) view.findViewById(R.id.t_height);
        t_weight = (TextView) view.findViewById(R.id.t_weight);
        t_target = (TextView) view.findViewById(R.id.t_target);
    }
}
