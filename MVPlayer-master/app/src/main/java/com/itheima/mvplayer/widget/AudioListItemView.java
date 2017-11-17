package com.itheima.mvplayer.widget;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.mvplayer.R;
import com.itheima.mvplayer.model.AudioItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioListItemView extends RelativeLayout {
    public static final String TAG = "AudioListItemView";
    @BindView(R.id.audio_default)
    ImageView mAudioDefault;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.artist)
    TextView mArtist;
    @BindView(R.id.size)
    TextView mSize;

    public AudioListItemView(Context context) {
        this(context, null);
    }

    public AudioListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_audio_list_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AudioItemBean itemBean) {
        mTitle.setText(itemBean.getTitle());
        mArtist.setText(itemBean.getArtist());
        mSize.setText(Formatter.formatFileSize(getContext(), Long.valueOf(itemBean.getSize())));
    }
}
