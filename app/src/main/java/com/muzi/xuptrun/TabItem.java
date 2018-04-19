package com.muzi.xuptrun;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.muzi.xuptrun.R;

/**
 * Created by muzi on 2018/4/18.
 */

public class TabItem {
    //图片设置
    private ImageView mIvTab;
    //文字设置
    private TextView mTvTab;
    //正常状态的图片
    private int imageNormal;
    //选中状态的图片
    private int imageSelected;
    //文字内容
    private String tabText;
    //Fragment
    private Class<? extends Fragment> fragmentClass;

    private View mTabView;

    public TabItem(int imageNormal, int imageSelected, String text, Class<? extends Fragment> fragmentClass) {
        this.imageNormal = imageNormal;
        this.imageSelected = imageSelected;
        this.tabText = text;
        this.fragmentClass = fragmentClass;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }
    /**
     * 获取 tab 上的文字
     *
     * @return tab 上的文字
     */
    public String getTabText() {
        return tabText;
    }

    /**
     * 设置选中
     *
     * @param checked 是否选中
     */
    public void setChecked(boolean checked) {
        if (checked) {
            //被选中时显示的文字颜色
            mTvTab.setTextColor(0xFFFF9933);
            mIvTab.setImageResource(imageSelected);
        } else {
            mTvTab.setTextColor(0xFF8A8A8A);
            mIvTab.setImageResource(imageNormal);
        }
    }
    public View getTabView(Context context) {
        mTabView = View.inflate(context, R.layout.view_tab, null);
        mIvTab = (ImageView) mTabView.findViewById(R.id.iv_tab);
        mTvTab = (TextView) mTabView.findViewById(R.id.tv_tab);
        mIvTab.setImageResource(imageNormal);
        mTvTab.setText(tabText);
        return mTabView;
    }
}
