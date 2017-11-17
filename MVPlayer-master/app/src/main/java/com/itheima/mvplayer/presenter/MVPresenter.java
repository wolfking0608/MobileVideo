package com.itheima.mvplayer.presenter;

import com.itheima.mvplayer.model.AreaBean;

import java.util.List;

public interface MVPresenter {
    void loadAreaData();

    List<AreaBean> getAreas();
}
