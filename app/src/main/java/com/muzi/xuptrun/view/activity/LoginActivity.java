package com.muzi.xuptrun.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.muzi.xuptrun.R;

/**
 * Created by muzi on 2018/4/19.
 */

public class LoginActivity extends AppCompatActivity{
    private EditText ed_username;
    private EditText ed_password;
    private Button but_login;
    private TextView tv_register;
    private TextView tv_forget;
    private String userName;
    private String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = ed_username.getText().toString();
                passWord = ed_password.getText().toString();
                AVUser.logInInBackground(userName, passWord, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            // 成功
                            Toast.makeText(LoginActivity.this,"成功~",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        } else {
                            //
                            Toast.makeText(LoginActivity.this,"失败~",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                LoginActivity.this.finish();
            }
        });

        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
                LoginActivity.this.finish();
            }
        });
    }

    void initView() {
        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_password = (EditText) findViewById(R.id.ed_password);
        but_login = (Button) findViewById(R.id.but_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
    }
}
