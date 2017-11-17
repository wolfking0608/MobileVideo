package com.itheima.mvplayer.view;

public interface BaseListView {

    void onLoadListDataFailed();

    void onLoadListDataSuccess();

    void onLoadMoreListDataFailed();

    void onLoadMoreListDataSuccess();

    void onNoMoreData();
}
