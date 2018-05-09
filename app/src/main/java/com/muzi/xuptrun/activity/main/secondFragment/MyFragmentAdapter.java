package com.muzi.xuptrun.activity.main.secondFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitles ;

    public MyFragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);

        this.fragmentList.add(RunFragment.newInstance());
        this.fragmentList.add(WalkFragment.newInstance());
        this.fragmentList.add(RideFragment.newInstance());
        mTitles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
