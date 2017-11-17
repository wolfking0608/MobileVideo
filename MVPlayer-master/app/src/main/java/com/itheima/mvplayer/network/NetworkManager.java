package com.itheima.mvplayer.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManager {
    public static final String TAG = "NetworkManager";

    private static NetworkManager mNetworkManager;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private NetworkManager() {
        mOkHttpClient = new OkHttpClient();
    }


    public static NetworkManager getInstance() {
        if (mNetworkManager == null) {
            synchronized (NetworkManager.class) {
                if (mNetworkManager == null) {
                    mNetworkManager = new NetworkManager();
                }
            }
        }
        return mNetworkManager;
    }

    public void sendRequest(final MVPlayerRequest mvPlayerRequest) {
        Log.d(TAG, "sendRequest: " + mvPlayerRequest.getUrl());
        final Request request = new Request.Builder().get().url(mvPlayerRequest.getUrl()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mvPlayerRequest.getNetworkListener().onError(e.getLocalizedMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // If I call response.body().string() twice, there's a exception, wired, maybe okhttp bug.
                String responseString = response.body().string();
                Log.d(TAG, "onResponse: " + responseString);
                final Object parsedResponse =  mvPlayerRequest.parseNetworkResponse(responseString);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mvPlayerRequest.getNetworkListener().onSuccess(parsedResponse);
                    }
                });
            }
        });
    }
}
