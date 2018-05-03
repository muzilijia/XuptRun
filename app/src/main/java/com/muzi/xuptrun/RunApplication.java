package com.muzi.xuptrun;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by muzi on 2018/4/20.
 */

public class RunApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"oi0ustwKxpDa0O89cUmp9IHY-gzGzoHsz","H1xcjgGtuDAwO15tjoasN2ln");
        //调试日志
        AVOSCloud.setDebugLogEnabled(true);
    }
}
