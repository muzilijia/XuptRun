package com.muzi.xuptrun.map;


import android.app.Activity;

import com.amap.api.maps.AMap;
import com.muzi.xuptrun.activity.run.RunActivity;
import com.muzi.xuptrun.utils.RunDataSaveUtil;

/**
 * 高德地图的实现类
 */
public class AmapImpl extends SubMap {


    private long mStartTime;

    private RunActivity activity;


    private Trace trace;

    private Location location;

    /**
     * 用于显示轨迹
     *
     * @param
     */
    public AmapImpl(Trace trace) {

        this.trace = trace;
        showTraceRecord();

    }


    /**
     * 用于跑步界面
     *
     * @param activity
     * @param aMap
     */
    public AmapImpl(Activity activity, AMap aMap) {

        this.activity = (RunActivity) activity;
        this.location = new Location(aMap, activity);
        startLocation();

    }


    @Override
    public void startLocation() {

        location.startLocation();
        mStartTime = System.currentTimeMillis();
    }

    @Override
    public void pauseLocation() {

        location.pauseLocation();
    }


    @Override
    public void closeLocation() {

        location.closeLocation();

    }

    //保存跑步数据
    public void saveRunningData() {
        RunDataSaveUtil.getInstance(activity)
                .saveRecord(location
                        .getRecord()
                        .getPathline(),mStartTime, location.getDuration());
    }

    @Override
    public void lookHistory() {

        showTraceRecord();
    }

    /**
     * 显示轨迹
     */
    public void showTraceRecord() {

        if (trace != null) {
            trace.setupRecord();
        }
    }



}
