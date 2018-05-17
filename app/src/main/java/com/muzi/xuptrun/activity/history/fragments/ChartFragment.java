package com.muzi.xuptrun.activity.history.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muzi.xuptrun.R;
import com.muzi.xuptrun.bean.RunData;
import com.muzi.xuptrun.utils.DBUtil;
import com.muzi.xuptrun.utils.RunDataModelUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ChartFragment extends Fragment {

    private TextView text_chart_distance, text_chart_duration, text_chart_calorie, text_chart_vector;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat fm_mouth = new SimpleDateFormat(
            "yyyy-MM");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat fm_date = new SimpleDateFormat(
            "yyyy-MM-dd");

    private String mouth = fm_mouth.format(new Date());
    private String date = fm_date.format(new Date());


    public final static String[] daily = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "9",
            "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24","25", "26", "27", "28", "29","30","31"};

    private ColumnChartView columnChart;
    private ColumnChartData columnData;

    public static ChartFragment newInstance() {
        ChartFragment fragment = new ChartFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_history_chart, container, false);

        initView(view);

        generateColumnData();

        readDataFromDB();

        return view;
    }


    public void readDataFromDB() {

        DBUtil DbHepler = new DBUtil(getActivity());
        DbHepler.open();

        List<RunData> list = DbHepler.queryRunDataByDate(date);
        if (list.size() > 0) {

            upDateTextData(list.get(0));
        }
        DbHepler.close();

    }


    public void upDateTextData(RunData data) {

        DecimalFormat df = new DecimalFormat("######0.00");
        String distance = df.format(data.getDistance() / 1000.0) + "km";
        String duration = (RunDataModelUtil.timeFormat((long) data.getDuration()));
        String calorie = df.format(data.getCalorie() / 1000) + "k ca";
        String vector = df.format(data.getVector()) + "m/s";

        text_chart_distance.setText(distance);
        text_chart_duration.setText(duration);
        text_chart_calorie.setText(calorie);
        text_chart_vector.setText(vector);

    }


    private void initView(View view) {

        columnChart = (ColumnChartView) view.findViewById(R.id.columnchart);
        text_chart_distance = (TextView) view.findViewById(R.id.text_chart_distance);
        text_chart_duration = (TextView) view.findViewById(R.id.text_char_alltime);
        text_chart_calorie = (TextView) view.findViewById(R.id.text_chart_calorie);
        text_chart_vector = (TextView) view.findViewById(R.id.text_chart_vector);

    }


    private void generateColumnData() {

        DBUtil DbHepler = new DBUtil(getActivity());
        DbHepler.open();
        List<RunData> datalist = DbHepler.queryRundataWithMouth(mouth);

        int numColumns = daily.length;

        List<AxisValue> axisValues = new ArrayList<>();
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<>();

            float value =0;

            for(RunData data :datalist){

                int d =Integer.valueOf(data.getDate().substring(8,10));
                if(d==i+1){
                    value = data.getDistance();
                }

            }

            values.add(new SubcolumnValue(value, ChartUtils.pickColor()));


            axisValues.add(new AxisValue(i).setLabel(daily[i]));

            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true).setName("日期(天)"));
        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2).setName("里程(米)"));

        columnChart.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        columnChart.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        columnChart.setValueSelectionEnabled(true);

//        columnChart.setZoomType(ZoomType.HORIZONTAL);
        columnChart.setZoomEnabled(false);


    }


    /**
     * 柱状图监听器
     *
     * @author 1017
     */
    private class ValueTouchListener implements
            ColumnChartOnValueSelectListener {
        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex,
                                    SubcolumnValue value) {
            // generateLineData(value.getColor(), 100);
        }

        @Override
        public void onValueDeselected() {
            // generateLineData(ChartUtils.COLOR_GREEN, 0);
        }
    }


}