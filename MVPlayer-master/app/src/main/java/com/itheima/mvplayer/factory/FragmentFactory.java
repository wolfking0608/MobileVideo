package com.itheima.mvplayer.factory;

import android.support.v4.app.Fragment;

import com.itheima.mvplayer.R;
import com.itheima.mvplayer.ui.fragment.HomeFragment;
import com.itheima.mvplayer.ui.fragment.MVFragment;
import com.itheima.mvplayer.ui.fragment.VBangFragment;
import com.itheima.mvplayer.ui.fragment.YueDanFragment;

public class FragmentFactory {
    public static final String TAG = "FragmentFactory";

    private static FragmentFactory sFragmentFactory;
    private Fragment mHomeFragment;
    private Fragment mMVFragment;
    private Fragment mVListFragment;
    private Fragment mYueDanFragment;

    private FragmentFactory(){}

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    public Fragment getFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                return getHomeFragment();
            case R.id.tab_mv:
                return getMVFragment();
            case R.id.tab_vlist:
                return getVListFragment();
            case R.id.tab_yue_dan:
                return getYueListFragment();
        }
        return null;
    }

    private Fragment getHomeFragment() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        return mHomeFragment;
    }

    private Fragment getMVFragment() {
        if (mMVFragment == null) {
            mMVFragment = new MVFragment();
        }
        return mMVFragment;
    }

    private Fragment getVListFragment() {
       if (mVListFragment == null) {
            mVListFragment = new VBangFragment();
        }
        return mVListFragment;
    }

    private Fragment getYueListFragment() {
        if (mYueDanFragment == null) {
            mYueDanFragment = new YueDanFragment();
        }
        return mYueDanFragment;
    }
}
