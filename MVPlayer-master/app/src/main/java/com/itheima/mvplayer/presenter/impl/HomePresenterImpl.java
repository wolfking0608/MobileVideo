package com.itheima.mvplayer.presenter.impl;

import com.itheima.mvplayer.model.HomeItemBean;
import com.itheima.mvplayer.network.HomeRequest;
import com.itheima.mvplayer.network.MVPlayerRequest;
import com.itheima.mvplayer.network.NetworkListener;
import com.itheima.mvplayer.presenter.BaseListPresenter;
import com.itheima.mvplayer.view.BaseListView;

import java.util.ArrayList;
import java.util.List;


public class HomePresenterImpl implements BaseListPresenter<HomeItemBean> {
    public static final String TAG = "HomePresenterImpl";

    private BaseListView mHomeView;

    private List<HomeItemBean> mHomeItemBeanList;

    private boolean mHasMoreData = true;

    public HomePresenterImpl(BaseListView homeView) {
        mHomeView = homeView;
        mHomeItemBeanList = new ArrayList<HomeItemBean>();
    }

    @Override
    public void loadListData() {
        if (mHomeItemBeanList.size() > 0) {
            mHomeView.onLoadListDataSuccess();
            return;
        }
        HomeRequest.getRequest(mListNetworkListener).execute();
    }


    private NetworkListener<List<HomeItemBean>> mListNetworkListener = new NetworkListener<List<HomeItemBean>>() {
        @Override
        public void onError(String errorMsg) {
            mHomeView.onLoadListDataFailed();
        }

        @Override
        public void onSuccess(List<HomeItemBean> result) {
            mHomeItemBeanList.addAll(result);
            mHomeView.onLoadListDataSuccess();
        }
    };

    @Override
    public List<HomeItemBean> getListData() {
        return mHomeItemBeanList;
    }

    @Override
    public void refresh() {
        mHomeItemBeanList.clear();
        loadListData();
    }

    @Override
    public void loadMoreListData() {
        if (mHasMoreData) {
            HomeRequest.getLoadMoreRequest(mHomeItemBeanList.size(), mLoadMoreListener).execute();
        } else {
            mHomeView.onNoMoreData();
        }
    }

    private NetworkListener<List<HomeItemBean>> mLoadMoreListener = new NetworkListener<List<HomeItemBean>>() {
        @Override
        public void onError(String errorMsg) {
            mHomeView.onLoadMoreListDataFailed();
        }

        @Override
        public void onSuccess(List<HomeItemBean> result) {
            mHasMoreData = (result.size() == MVPlayerRequest.DEFAULT_PAGE_SIZE);
            mHomeItemBeanList.addAll(result);
            mHomeView.onLoadMoreListDataSuccess();
        }
    };

}
