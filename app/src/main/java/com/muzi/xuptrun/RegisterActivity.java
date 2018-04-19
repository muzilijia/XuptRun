package com.muzi.xuptrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        userName = ed_username.getText().toString();
        passWord = ed_password.getText().toString();
        email = ed_email.getText().toString();
        but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                RegisterActivity.this.finish();
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
