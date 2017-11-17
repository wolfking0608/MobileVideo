package com.itheima.mvplayer.model;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.CursorAdapter;

import com.itheima.mvplayer.utils.AudioAsyncQueryHandler;

public class AudioManager {
    public static final String TAG = "AudioManager";

    private static AudioManager mAudioManager;

    private CursorAdapter mCursorAdapter;
    private AudioAsyncQueryHandler mAudioAsyncQueryHandler;

    private AudioManager() {}

    public static AudioManager getInstance() {
        if (mAudioManager == null) {
            synchronized (AudioManager.class) {
                if (mAudioManager == null) {
                    mAudioManager = new AudioManager();
                }
            }
        }
        return mAudioManager;
    }

    public void loadAudio(Context context, CursorAdapter cursorAdapter) {
        mCursorAdapter = cursorAdapter;
/*        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE};
        Cursor query = contentResolver.query(uri, projection, null, null, null);
        PrintUtils.printCursor(query);*/
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME};
        mAudioAsyncQueryHandler = new AudioAsyncQueryHandler(context.getContentResolver());
        mAudioAsyncQueryHandler.startQuery(0, cursorAdapter, uri, projection, null, null, null);
    }

    public AudioItemBean getAudioItem(int position) {
        return mAudioAsyncQueryHandler.getAudioItemBeanList().get(position);
    }

    public int getAudioCount() {
        return mAudioAsyncQueryHandler.getAudioItemBeanList().size();
    }
}
