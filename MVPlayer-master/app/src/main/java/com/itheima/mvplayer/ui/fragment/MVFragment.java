package com.itheima.mvplayer.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.itheima.mvplayer.R;
import com.itheima.mvplayer.presenter.MVPresenter;
import com.itheima.mvplayer.presenter.impl.MVPresenterImpl;
import com.itheima.mvplayer.ui.adapter.MVAdapter;
import com.itheima.mvplayer.view.MVView;

import butterknife.BindView;

public class MVFragment extends BaseFragment implements MVView{
    public static final String TAG = "MVFragment";
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private MVPresenter mMVPresenter;

    private MVAdapter mMVAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_mv;
    }

    @Override
    protected void init() {
        super.init();
        mMVPresenter = new MVPresenterImpl(this);
        mMVAdapter = new MVAdapter(getChildFragmentManager(), mMVPresenter.getAreas());
        mViewPager.setAdapter(mMVAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mMVPresenter.loadAreaData();
    }

    @Override
    public void onLoadAreaFailed() {
        toast(R.string.load_data_failed);
    }

    @Override
    public void onLoadAreaSuccess() {
        toast(R.string.load_data_success);


        mMVAdapter.notifyDataSetChanged();
    }
}
