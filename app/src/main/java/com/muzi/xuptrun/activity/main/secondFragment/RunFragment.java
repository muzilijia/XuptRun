package com.muzi.xuptrun.activity.main.secondFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.muzi.xuptrun.R;
import com.muzi.xuptrun.activity.run.RunActivity;

/**
 * Created by muzi on 2018/4/18.
 */

public class RunFragment extends Fragment{
    private Button but_begin;

    public static RunFragment newInstance() {
        RunFragment fragment = new RunFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run, container, false);
        but_begin = (Button) view.findViewById(R.id.but_begin);
        but_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RunActivity.class));
            }
        });
        return view;
    }
}
