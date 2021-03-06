package com.muzi.xuptrun.activity.run.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.muzi.xuptrun.R;
import com.muzi.xuptrun.map.AmapImpl;


public class AMapShowFragment extends Fragment {

    private AMap aMap;
    private MapView bMapView;
    private AmapImpl aMapImpl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        bMapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        bMapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {

            aMap = bMapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        }
        aMapImpl = new AmapImpl(getActivity(), aMap);

        startLocation();
        return view;

    }


    public void startLocation(){

        aMapImpl.startLocation();

    }

    public void pauseLocation(){

        aMapImpl.pauseLocation();

    }

    public void closeLocationWithoutSaveRunData(){

        aMapImpl.closeLocation();

    }

    public void closeLocationWithSaveRunData(){

        //获取运动类型
        String type = null;
        Intent recordIntent = getActivity().getIntent();
        if (recordIntent != null) {
            type = recordIntent.getStringExtra("type");
        }
        aMapImpl.saveRunningData(type);
        aMapImpl.closeLocation();

    }


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        bMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        bMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        bMapView.onDestroy();

    }

}
