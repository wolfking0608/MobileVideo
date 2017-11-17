package com.itheima.mvplayer.presenter.impl;

import com.itheima.mvplayer.model.YueDanBean;
import com.itheima.mvplayer.network.NetworkListener;
import com.itheima.mvplayer.network.YueDanRequest;
import com.itheima.mvplayer.presenter.BaseListPresenter;
import com.itheima.mvplayer.view.BaseListView;

import java.util.ArrayList;
import java.util.List;

public class YueDanPresenterImpl implements BaseListPresenter<YueDanBean.PlayListsBean> {
    public static final String TAG = "YueDanPresenterImpl";

    private BaseListView mYueDanView;

    private List<YueDanBean.PlayListsBean> mPlayListsBeanList;


    public YueDanPresenterImpl(BaseListView view) {
        mYueDanView = view;
        mPlayListsBeanList = new ArrayList<YueDanBean.PlayListsBean>();
    }

    @Override
    public void loadListData() {
        if (mPlayListsBeanList.size() > 0) {
            mYueDanView.onLoadListDataSuccess();
            return;
        }
        YueDanRequest.getRequest(mYueDanBeanNetworkListener).execute();
    }

    private NetworkListener<YueDanBean> mYueDanBeanNetworkListener = new NetworkListener<YueDanBean>() {
        @Override
        public void onError(String errorMsg) {
            mYueDanView.onLoadListDataFailed();
        }

        @Override
        public void onSuccess(YueDanBean result) {
            mPlayListsBeanList.addAll(result.getPlayLists());
            mYueDanView.onLoadListDataSuccess();
        }
    };

    @Override
    public void loadMoreListData() {
        YueDanRequest.getLoadMoreRequest(mPlayListsBeanList.size(), mLoadMoreListener).execute();
    }


    private NetworkListener<YueDanBean> mLoadMoreListener = new NetworkListener<YueDanBean>() {
        @Override
        public void onError(String errorMsg) {
            mYueDanView.onLoadListDataFailed();
        }

        @Override
        public void onSuccess(YueDanBean result) {
            mPlayListsBeanList.addAll(result.getPlayLists());
            mYueDanView.onLoadListDataSuccess();
        }
    };



    @Override
    public List<YueDanBean.PlayListsBean> getListData() {
        return mPlayListsBeanList;
    }

    @Override
    public void refresh() {
        mPlayListsBeanList.clear();
        loadListData();
    }
}
