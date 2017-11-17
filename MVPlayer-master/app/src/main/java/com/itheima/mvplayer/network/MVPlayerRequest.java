package com.itheima.mvplayer.network;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/21 14:45
 * 描述： TODO
 */
public class MVPlayerRequest<T> {
    private static final String TAG = "MVPlayerRequest";

    public static final int DEFAULT_PAGE_SIZE = 10;

    private String mUrl;
    private NetworkListener<T> mNetworkListener;
    private Gson mGson;

    public MVPlayerRequest(String url, NetworkListener<T> listener) {
        mUrl = url;
        mNetworkListener = listener;
        mGson = new Gson();
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public NetworkListener<T> getNetworkListener() {
        return mNetworkListener;
    }

    public void setNetworkListener(NetworkListener<T> networkListener) {
        mNetworkListener = networkListener;
    }

    public void execute() {
        NetworkManager.getInstance().sendRequest(this);
    }

    public T parseNetworkResponse(String result) {
        Class c = this.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) c.getGenericSuperclass();
        Type actualType = parameterizedType.getActualTypeArguments()[0];
        return mGson.fromJson(result, actualType);
    }
}
