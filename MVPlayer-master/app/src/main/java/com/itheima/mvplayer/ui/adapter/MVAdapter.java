package com.itheima.mvplayer.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.itheima.mvplayer.model.AreaBean;
import com.itheima.mvplayer.ui.fragment.MVPageFragment;

import java.util.List;

public class MVAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "MVAdapter";

    private List<AreaBean> mAreas;

    public MVAdapter(FragmentManager fm) {
        super(fm);
    }

    public MVAdapter(FragmentManager fm, List<AreaBean> areas) {
        this(fm);
        mAreas = areas;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: " + position);
        return MVPageFragment.newInstance(mAreas.get(position).getCode());
    }

    @Override
    public int getCount() {
        return mAreas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mAreas.get(position).getName();
    }
}
