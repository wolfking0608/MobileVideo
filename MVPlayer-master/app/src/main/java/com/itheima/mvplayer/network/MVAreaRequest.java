package com.itheima.mvplayer.network;

import com.itheima.mvplayer.model.AreaBean;

import java.util.List;

public class MVAreaRequest extends MVPlayerRequest<List<AreaBean>> {
    public static final String TAG = "MVAreaRequest";


    public MVAreaRequest(String url, NetworkListener<List<AreaBean>> listener) {
        super(url, listener);
    }
}
