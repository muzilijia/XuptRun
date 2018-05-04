package com.muzi.xuptrun.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.muzi.xuptrun.R;

/**
 * Created by muzi on 2018/4/19.
 */

public class RegisterActivity extends AppCompatActivity{
    private EditText ed_username;
    private EditText ed_password;
    private EditText ed_email;
    private Button but_register;
    private String userName;
    private String passWord;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = ed_username.getText().toString();
                passWord = ed_password.getText().toString();
                email = ed_email.getText().toString();
                AVUser user = new AVUser();// 新建 AVUser 对象实例
                user.setUsername(userName);// 设置用户名
                user.setPassword(passWord);// 设置密码
                user.setEmail(email);// 设置邮箱
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            // 注册成功
                            Toast.makeText(RegisterActivity.this,"注册成功~",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            RegisterActivity.this.finish();
                        } else {
                            // 失败的原因可能有多种，常见的是用户名已经存在。
                            Toast.makeText(RegisterActivity.this,"注册失败~",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    void initView() {
        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_email = (EditText) findViewById(R.id.ed_email);
        but_register = (Button) findViewById(R.id.but_register);
    }
}
