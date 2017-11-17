package com.itheima.mvplayer.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.itheima.mvplayer.model.AudioItemBean;
import com.itheima.mvplayer.widget.AudioListItemView;

public class AudioListAdapter extends CursorAdapter {

    public AudioListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new AudioListItemView(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        AudioItemBean itemBean = AudioItemBean.from(cursor);
        ((AudioListItemView)view).bindView(itemBean);
    }
}
