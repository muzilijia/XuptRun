package com.muzi.xuptrun.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.muzi.xuptrun.R;
import com.muzi.xuptrun.view.fragment.FirstFragment.FirstFragment;
import com.muzi.xuptrun.view.fragment.ThirdFragment.ThirdFragment;
import com.muzi.xuptrun.view.fragment.secondFragment.SecondFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muzi on 2018/4/18.
 */

public class MainActivity extends AppCompatActivity {

    private List<TabItem> mFragmentList;
    private FragmentTabHost mFragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabItemData();
    }
    /**
     * 初始化 Tab 数据
     */
    private void initTabItemData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TabItem(
                R.drawable.n_1,
                R.drawable.y_1, "记录",
                FirstFragment.class
        ));

        mFragmentList.add(new TabItem(
                R.drawable.n_2,
                R.drawable.y_2, "运动",
                SecondFragment.class
        ));

        mFragmentList.add(new TabItem(
                R.drawable.n_3,
                R.drawable.y_3, "我的",
                ThirdFragment.class
        ));
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        // 绑定 FragmentManager
        mFragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        for (int i = 0; i < mFragmentList.size(); i++) {
            TabItem tabItem = mFragmentList.get(i);
            // 创建 tab
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(
                    tabItem.getTabText()).
                    setIndicator(tabItem.getTabView(MainActivity.this));
            // 将创建的 tab 添加到底部 tab 栏中（ @android:id/tabs ）
            // 将 Fragment 添加到页面中（ @android:id/tabcontent ）
            mFragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);
            // 底部 tab 栏设置背景图片/颜色
            mFragmentTabHost.getTabWidget().setBackgroundResource(R.color.white);
            // 默认选中第一个 tab
            if (i == 0) {
                tabItem.setChecked(true);
            } else {
                tabItem.setChecked(false);
            }
        }
        // 切换 tab 时，回调此方法
        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < mFragmentList.size(); i++) {
                    TabItem tabItem = mFragmentList.get(i);
                    // 通过 tag 检查用户点击的是哪个 tab
                    if (tabId.equals(tabItem.getTabText())) {
                        tabItem.setChecked(true);
                    } else {
                        tabItem.setChecked(false);
                    }
                }
            }
        });
    }
}
