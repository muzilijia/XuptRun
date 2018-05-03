package com.muzi.xuptrun.view.fragment.secondFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muzi.xuptrun.R;

/**
 * Created by muzi on 2018/4/18.
 */

public class WalkFragment extends Fragment{

    public static WalkFragment newInstance() {
        WalkFragment fragment = new WalkFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk, container, false);
        return view;
    }
}
