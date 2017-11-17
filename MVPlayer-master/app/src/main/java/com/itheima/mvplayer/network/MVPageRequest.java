package com.itheima.mvplayer.network;

import com.itheima.mvplayer.model.MVPageBean;
import com.itheima.mvplayer.utils.URLProviderUtil;

public class MVPageRequest extends MVPlayerRequest<MVPageBean> {
    public static final String TAG = "MVPageRequest";

    public MVPageRequest(String url, NetworkListener listener) {
        super(url, listener);
    }

    public static MVPageRequest getRequest(String code, NetworkListener<MVPageBean> listener) {
        return new MVPageRequest(URLProviderUtil.getMVListUrl(code, 0, DEFAULT_PAGE_SIZE), listener);
    }

    public static MVPageRequest getLodeMoreRequest(String code, int size, NetworkListener<MVPageBean> networkListener) {
        return new MVPageRequest(URLProviderUtil.getMVListUrl(code, size, DEFAULT_PAGE_SIZE), networkListener);
    }
}
