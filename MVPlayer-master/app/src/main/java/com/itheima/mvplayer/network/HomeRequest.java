package com.itheima.mvplayer.network;

import com.itheima.mvplayer.model.HomeItemBean;
import com.itheima.mvplayer.utils.URLProviderUtil;

import java.util.List;

public class HomeRequest extends MVPlayerRequest<List<HomeItemBean>> {
    public static final String TAG = "HomeRequest";

    public HomeRequest(String url, NetworkListener<List<HomeItemBean>> listener) {
        super(url, listener);
    }

    public static HomeRequest getRequest(NetworkListener<List<HomeItemBean>> listener) {
        return new HomeRequest(URLProviderUtil.getHomeUrl(0, DEFAULT_PAGE_SIZE), listener);
    }

    public static HomeRequest getLoadMoreRequest(int offset, NetworkListener<List<HomeItemBean>> listener) {
        return new HomeRequest(URLProviderUtil.getHomeUrl(offset, DEFAULT_PAGE_SIZE), listener);
    }
}
