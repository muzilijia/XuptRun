package com.muzi.xuptrun.activity.main.secondFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.muzi.xuptrun.R;
import com.muzi.xuptrun.activity.run.RunActivity;
import com.muzi.xuptrun.utils.DBUtil;

/**
 * Created by muzi on 2018/4/18.
 */

public class WalkFragment extends Fragment{
    private DBUtil mDataBaseHelper;
    private Button but_begin;
    private TextView tv_dis;

    public static WalkFragment newInstance() {
        WalkFragment fragment = new WalkFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk, container, false);

        but_begin = (Button) view.findViewById(R.id.but_begin);
        tv_dis = (TextView) view.findViewById(R.id.tv_walkdis);

        mDataBaseHelper = new DBUtil(getActivity());
        mDataBaseHelper.open();

        float dis = mDataBaseHelper.queryDistanceByType(mDataBaseHelper.queryRecordAll(), "健走");
        tv_dis.setText(dis+"");

        but_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从fragment传递数据到activity
                Intent intent = new Intent(getActivity(),
                        RunActivity.class);
                intent.putExtra("type", "健走");
                startActivity(intent);
            }
        });
        return view;
    }
}
