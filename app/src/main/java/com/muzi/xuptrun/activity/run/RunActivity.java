package com.muzi.xuptrun.activity.run;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.muzi.xuptrun.R;
import com.muzi.xuptrun.activity.run.fragments.AMapShowFragment;
import com.muzi.xuptrun.activity.run.fragments.RunDataShowFragment;
import com.muzi.xuptrun.map.IDataUpdateCallback;
import com.muzi.xuptrun.utils.PermissionUtil;

/**
 * Created by muzi on 2018/5/5.
 */

public class RunActivity extends AppCompatActivity implements View.OnClickListener,IDataUpdateCallback {
    private Button but_changemap, but_pause, but_end;
    private boolean isShow = false;
    public RunDataShowFragment runDataShowFragment;
    public AMapShowFragment aMapShowFragment;

    /**
     * 判断权限
     */
    PermissionUtil.PermissionGrant permissionGrant = new PermissionUtil.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtil.CODE_CAMERA:
//                    Toast.makeText(RuningActivity.this, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtil.CODE_ACCESS_FINE_LOCATION:
//                    Toast.makeText(RuningActivity.this, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtil.CODE_ACCESS_COARSE_LOCATION:
//                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_run);

        initView();
        initData();
        setOnClickListener();

        //默认展示地图界面
        showMapFragment();

        //定位权限申请
        PermissionUtil.requestPermission(this, PermissionUtil.CODE_ACCESS_FINE_LOCATION, permissionGrant);
    }

    /**
     * 设置OnClickListener
     */
    private void setOnClickListener() {
        but_changemap.setOnClickListener(this);
        but_pause.setOnClickListener(this);
        but_end.setOnClickListener(this);
    }

    /**
     * 展示地图界面（aMapShowFragment）
     *
     */
    void showMapFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(aMapShowFragment);
        transaction.hide(runDataShowFragment);
        transaction.commit();
    }

    /**
     * 展示数据界面（runDataShowFragment）
     */
    void showDataFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(runDataShowFragment);
        transaction.hide(aMapShowFragment);
        transaction.commit();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        runDataShowFragment = RunDataShowFragment.getInstance();
        //默认展示AMapFragment,即地图页面
        aMapShowFragment = new AMapShowFragment();
        if (!aMapShowFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.layout, aMapShowFragment);
            transaction.commit();
        }
        if (!runDataShowFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.layout, runDataShowFragment);
            transaction.commit();
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        but_changemap = (Button) findViewById(R.id.but_changemap);
        but_pause = (Button) findViewById(R.id.but_pause);
        but_end = (Button) findViewById(R.id.but_end);
    }

    /**
     * 点击事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_pause:
                if (but_pause.getText().equals("暂停")) {
                    pauseLocation();
                    but_pause.setText("继续");
                } else {
                    startLocation();
                    but_pause.setText("暂停");
                }
                break;
            case R.id.but_end:
                closeLocationWithSave();
                break;
            case R.id.but_changemap:
                if (isShow) {
                    showMapFragment();
                    isShow = !isShow;
                } else {
                    showDataFragment();
                    isShow = !isShow;
                }
                break;
            default:
        }
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        aMapShowFragment.startLocation();
    }

    /**
     * 暂停定位
     */
    public void pauseLocation() {
        aMapShowFragment.pauseLocation();
    }

    /**
     * 结束定位
     */
    public void closeLocationWithoutSave( ) {
        aMapShowFragment.closeLocationWithoutSaveRunData();
        this.finish();
    }


    public void closeLocationWithSave( ) {
        aMapShowFragment.closeLocationWithSaveRunData();
        this.finish();
    }

    /**
     * 重写返回按钮，在Activity销毁之前询问是否需要保存数据
     */
    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(RunActivity.this);
        dialog.setTitle("结束运动");

        LayoutInflater inflater = LayoutInflater.from(RunActivity.this);
        View view = inflater.inflate(R.layout.dlg_content, null);

        dialog.setView(view);
        dialog.setCancelable(true);
        dialog.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //结束运动并保存数据
                closeLocationWithSave();
                RunActivity.this.finish();
            }
        });
        dialog.setNegativeButton("不保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //结束运动
                closeLocationWithoutSave();
            }
        });
        dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    /**
     * 请求权限返回值
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtil.requestPermissionsResult(this, requestCode, permissions, grantResults, permissionGrant);
    }

    /**
     * 更新速度
     * @param vector      更新的值
     * @param defaultShow 默认的值
     *                    下同
     */
    @Override
    public void upVector(String vector, String defaultShow) {
        if (runDataShowFragment != null) {
            runDataShowFragment.upVector(vector);
        } else if (defaultShow != null) {
            runDataShowFragment.upVector(defaultShow);
        }
    }

    /**
     * 更新里程
     * @param distance
     * @param defaultShow
     */
    @Override
    public void upDistance(String distance, String defaultShow) {
        if (runDataShowFragment != null) {
            runDataShowFragment.upDistance(distance);
        } else if (defaultShow != null) {
            runDataShowFragment.upDistance(defaultShow);
        }
    }

    /**
     * 更新时长
     * @param duration
     * @param defaultShow
     */
    @Override
    public void upDuration(String duration, String defaultShow) {
        if (runDataShowFragment != null) {
            runDataShowFragment.upDuration(duration);
        } else if (defaultShow != null) {
            runDataShowFragment.upDuration(defaultShow);
        }
    }

}
