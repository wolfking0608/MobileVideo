package com.itheima.mvplayer.presenter.impl;

import com.itheima.mvplayer.model.AreaBean;
import com.itheima.mvplayer.network.MVAreaRequest;
import com.itheima.mvplayer.network.NetworkListener;
import com.itheima.mvplayer.presenter.MVPresenter;
import com.itheima.mvplayer.utils.URLProviderUtil;
import com.itheima.mvplayer.view.MVView;

import java.util.ArrayList;
import java.util.List;

public class MVPresenterImpl implements MVPresenter {
    public static final String TAG = "MVPresenterImpl";

    public MVView mMVView;

    private List<AreaBean> mAreas;

    public MVPresenterImpl(MVView view) {
        mMVView = view;
        mAreas = new ArrayList<AreaBean>();
    }

    @Override
    public void loadAreaData() {
        new MVAreaRequest(URLProviderUtil.getMVareaUrl(), mNetworkListener).execute();
    }

    @Override
    public List<AreaBean> getAreas() {
        return mAreas;
    }

    private NetworkListener<List<AreaBean>> mNetworkListener = new NetworkListener<List<AreaBean>>() {
        @Override
        public void onError(String errorMsg) {
            mMVView.onLoadAreaFailed();
        }

        @Override
        public void onSuccess(List<AreaBean> result) {
            mAreas.addAll(result);//Notice that we should add data first, then notify change.
            mMVView.onLoadAreaSuccess();
        }
    };
}
