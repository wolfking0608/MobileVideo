package com.itheima.mvplayer.utils;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

import com.itheima.mvplayer.model.AudioItemBean;
import com.itheima.mvplayer.ui.adapter.AudioListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AudioAsyncQueryHandler extends AsyncQueryHandler {
    public static final String TAG = "AudioAsyncQueryHandler";

    private List<AudioItemBean> mAudioItemBeanList;

    public AudioAsyncQueryHandler(ContentResolver cr) {
        super(cr);
        mAudioItemBeanList = new ArrayList<AudioItemBean>();
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        ((AudioListAdapter)cookie).swapCursor(cursor);
        mAudioItemBeanList.clear();
        while (cursor.moveToNext()) {
            AudioItemBean audioItemBean = AudioItemBean.from(cursor);
            mAudioItemBeanList.add(audioItemBean);
        }
    }

    public List<AudioItemBean> getAudioItemBeanList() {
        return mAudioItemBeanList;
    }
}
