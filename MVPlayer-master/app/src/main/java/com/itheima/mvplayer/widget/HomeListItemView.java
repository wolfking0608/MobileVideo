package com.itheima.mvplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.mvplayer.R;
import com.itheima.mvplayer.model.HomeItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListItemView extends RelativeLayout {
    public static final String TAG = "HomeListItemView";
    @BindView(R.id.video)
    ImageView mVideo;
    @BindView(R.id.video_type)
    ImageView mVideoType;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.description)
    TextView mDescription;

    public HomeListItemView(Context context) {
        this(context, null);
    }

    public HomeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_home_list_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(HomeItemBean homeItemBean) {
        mTitle.setText(homeItemBean.getTitle());
        mDescription.setText(homeItemBean.getDescription());
        Glide.with(getContext()).load(homeItemBean.getPosterPic()).centerCrop().into(mVideo);
    }
}
