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
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.muzi.xuptrun.R;

/**
 * Created by muzi on 2018/4/19.
 */

public class ForgetActivity extends AppCompatActivity {
    private Button but_find;
    private EditText ed_email;
    private String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        but_find = (Button)findViewById(R.id.but_find);
        ed_email = (EditText) findViewById(R.id.ed_email);
        but_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = ed_email.getText().toString();
                AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            Toast.makeText(ForgetActivity.this,"重置密码已下发邮箱~",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetActivity.this, LoginActivity.class));
                            ForgetActivity.this.finish();
                        } else {
                            e.printStackTrace();
                            Toast.makeText(ForgetActivity.this,"邮箱错误~",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
