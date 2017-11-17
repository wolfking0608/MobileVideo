package com.itheima.mvplayer.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.itheima.mvplayer.presenter.BaseListPresenter;
import com.itheima.mvplayer.presenter.impl.YueDanPresenterImpl;
import com.itheima.mvplayer.ui.adapter.YueDanListAdapter;
import com.itheima.mvplayer.view.BaseListView;

public class YueDanFragment extends BaseListFragment {


    private YueDanPresenterImpl mYueDanPresenter;

    @Override
    public RecyclerView.Adapter getListAdapter() {
        return new YueDanListAdapter(getContext(), mYueDanPresenter.getListData());
    }

    @Override
    public BaseListPresenter getPresenter(BaseListView view) {
        mYueDanPresenter = new YueDanPresenterImpl(view);
        return mYueDanPresenter;
    }
}
