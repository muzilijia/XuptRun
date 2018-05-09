package com.muzi.xuptrun.activity.main.FirstFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.muzi.xuptrun.R;
import com.muzi.xuptrun.activity.history.HistoryShowActivity;
import com.muzi.xuptrun.bean.PathRecord;
import com.muzi.xuptrun.utils.DBUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by muzi on 2018/4/18.
 */

public class FirstFragment extends Fragment implements AdapterView.OnItemClickListener {
    private RecordAdapter mAdapter;
    private ListView mAllRecordListView;
    private DBUtil mDataBaseHelper;
    private List<PathRecord> mAllRecord = new ArrayList<>();
    public static final String RECORD_ID = "record_id";

    private Button btn_date;

    private int mYear = Calendar.getInstance().get(Calendar.YEAR);
    private int mMonth = Calendar.getInstance().get(Calendar.MONTH);
    private int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    private SwipeRefreshLayout swipeRefresh;
    private RelativeLayout empty_layout;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat fm_mouth = new SimpleDateFormat(
            "yyyy-MM-dd");
    private String date = fm_mouth.format(new Date());

    public FirstFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initFindView(view);
        setOnClickListener();

        mDataBaseHelper = new DBUtil(getActivity());
        mDataBaseHelper.open();

        searchRecordByDate(date);

        mAllRecordListView.setAdapter(mAdapter);
        mAllRecordListView.setOnItemClickListener(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        return view;
    }

    public void refreshData() {

        searchRecordByDate(date);

    }

    private void initFindView(View view) {
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mAllRecordListView = (ListView) view.findViewById(R.id.recordlist);
        btn_date = (Button) view.findViewById(R.id.btn_date);
        empty_layout = (RelativeLayout) view.findViewById(R.id.empty_layout);
    }


    public void setOnClickListener() {

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                now.set(mYear, mMonth, mDay);

                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        Calendar temp = Calendar.getInstance();
                        temp.clear();
                        temp.set(year, monthOfYear, dayOfMonth);

                        temp.getTime();

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        String date = formatter.format(temp.getTime());

                        searchRecordByDate(date);
                        Log.i("RecordListActivity", date);

                    }

                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

                dialog.setMaxDate(Calendar.getInstance());
                Calendar minDate = Calendar.getInstance();
                /**
                 * 在java Calendar类中，使用基于0的索引：1月是月0，12月是11月。
                 * 这个约定在java世界中广泛使用，例如原生Android DatePicker。
                 */
                minDate.set(2017, 0, 1);
                dialog.setMinDate(minDate);

                //设置是否在进行选择时对话框振动设备。默认为true。
                dialog.vibrate(false);

                dialog.show(getActivity().getFragmentManager(), "DatePickerDialog");


            }
        });
    }


    private void searchAllRecordFromDB() {

        mAllRecord = mDataBaseHelper.queryRecordAll();
        mAdapter = new RecordAdapter(getActivity(), mAllRecord);
        mAllRecordListView.setAdapter(mAdapter);
        swipeRefresh.setRefreshing(false);

        if (mAllRecord.size() == 0) {
            empty_layout.setVisibility(View.VISIBLE);
        } else {
            empty_layout.setVisibility(View.GONE);
        }

    }


    private void searchRecordByDate(String date) {

        mAllRecord = mDataBaseHelper.queryRecordByDate(date);

        mAdapter = new RecordAdapter(getActivity(), mAllRecord);
        mAllRecordListView.setAdapter(mAdapter);
        swipeRefresh.setRefreshing(false);

        if (mAllRecord.size() == 0) {
            empty_layout.setVisibility(View.VISIBLE);
        } else {
            empty_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        PathRecord recorditem = (PathRecord) parent.getAdapter().getItem(
                position);
        //从fragment传递数据到activity
        Intent intent = new Intent(getActivity(),
                HistoryShowActivity.class);
        intent.putExtra(RECORD_ID, recorditem.getId());
        startActivity(intent);
    }
}
