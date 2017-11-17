package com.itheima.mvplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.mvplayer.R;
import com.itheima.mvplayer.model.YueDanBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YueDanListItemView extends RelativeLayout {
    public static final String TAG = "YueDanListItemView";

    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.mv_count)
    TextView mMvCount;
    @BindView(R.id.default_icon)
    ImageView mDefaultIcon;
    @BindView(R.id.item_img)
    ImageView mItemImg;

    public YueDanListItemView(Context context) {
        this(context, null);
    }

    public YueDanListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_yue_dan_list_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(YueDanBean.PlayListsBean playListsBean) {
        mTitle.setText(playListsBean.getTitle());
        mNickname.setText(playListsBean.getCreator().getNickName());
        Glide.with(getContext()).load(playListsBean.getThumbnailPic()).centerCrop().into(mItemImg);
        Glide.with(getContext()).load(playListsBean.getCreator().getLargeAvatar()).into(mAvatar);
    }
}
