package com.itheima.mvplayer.ui.activity;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.itheima.mvplayer.R;
import com.itheima.mvplayer.app.Constant;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MVPlayerActivity extends BaseActivity {
    public static final String TAG = "MVPlayerActivity";
    @BindView(R.id.description)
    RadioButton mDescription;
    @BindView(R.id.comment)
    RadioButton mComment;
    @BindView(R.id.relative)
    RadioButton mRelative;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.video_player)
    JCVideoPlayer mJCVideoPlayer;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_player;
    }

    @Override
    protected void init() {
        super.init();
        String url = getIntent().getStringExtra(Constant.Extra.VIDEO_URL);
        String title = getIntent().getStringExtra(Constant.Extra.VIDEO_TITLE);
        mRadioGroup.check(R.id.description);
        mJCVideoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
    }

    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


}
