package com.itheima.mvplayer.network;

import com.itheima.mvplayer.model.YueDanBean;
import com.itheima.mvplayer.utils.URLProviderUtil;

public class YueDanRequest extends MVPlayerRequest<YueDanBean> {
    public static final String TAG = "YueDanRequest";

    public YueDanRequest(String url, NetworkListener<YueDanBean> listener) {
        super(url, listener);
    }

    public static YueDanRequest getRequest(NetworkListener<YueDanBean> listener) {
        return new YueDanRequest(URLProviderUtil.getYueDanUrl(0, DEFAULT_PAGE_SIZE), listener);
    }

    public static YueDanRequest getLoadMoreRequest(int offset, NetworkListener<YueDanBean> listener) {
        return new YueDanRequest(URLProviderUtil.getYueDanUrl(offset, DEFAULT_PAGE_SIZE), listener);
    }
}
