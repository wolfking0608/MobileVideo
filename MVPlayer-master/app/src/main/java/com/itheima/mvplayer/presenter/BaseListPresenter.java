package com.itheima.mvplayer.presenter;

import java.util.List;

public interface BaseListPresenter<T> {

    void loadListData();

    List<T> getListData();

    void refresh();

    void loadMoreListData();

}