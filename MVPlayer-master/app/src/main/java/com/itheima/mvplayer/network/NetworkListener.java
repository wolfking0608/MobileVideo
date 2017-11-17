package com.itheima.mvplayer.network;

public interface NetworkListener<T> {

    void onError(String errorMsg);

    void onSuccess(T result);
}
