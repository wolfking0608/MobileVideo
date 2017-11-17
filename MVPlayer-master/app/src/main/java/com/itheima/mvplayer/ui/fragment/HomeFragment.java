package com.itheima.mvplayer.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.itheima.mvplayer.presenter.BaseListPresenter;
import com.itheima.mvplayer.presenter.impl.HomePresenterImpl;
import com.itheima.mvplayer.ui.adapter.HomeListAdapter;
import com.itheima.mvplayer.view.BaseListView;

public class HomeFragment extends BaseListFragment {

    private HomePresenterImpl mBaseListPresenter;

    @Override
    public RecyclerView.Adapter getListAdapter() {
        return new HomeListAdapter(getContext(), mBaseListPresenter.getListData());
    }

    @Override
    public BaseListPresenter getPresenter(BaseListView view) {
        mBaseListPresenter = new HomePresenterImpl(view);
        return mBaseListPresenter;
    }
}
