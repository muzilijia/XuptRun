package com.muzi.xuptrun.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muzi.xuptrun.R;

/**
 * Created by muzi on 2018/4/18.
 */

public class SecondFragment extends Fragment{
    public SecondFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }
}
