package com.itheima.mvplayer.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itheima.mvplayer.R;
import com.itheima.mvplayer.app.Constant;
import com.itheima.mvplayer.model.AudioManager;
import com.itheima.mvplayer.ui.activity.AudioPlayerActivity;
import com.itheima.mvplayer.ui.adapter.AudioListAdapter;

import butterknife.BindView;

public class VBangFragment extends BaseFragment {
    public static final String TAG = "VBangFragment";
    @BindView(R.id.list_view)
    ListView mListView;
    private AudioListAdapter mAudioListAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_vlist;
    }

    @Override
    protected void init() {
        super.init();
        mAudioListAdapter = new AudioListAdapter(getContext(), null, false);
        mListView.setAdapter(mAudioListAdapter);
        mListView.setOnItemClickListener(mOnItemClickListener);
        if (hasReadExternalStoragePermission()) {
            AudioManager.getInstance().loadAudio(getContext(), mAudioListAdapter);
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
    }

    private boolean hasReadExternalStoragePermission() {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AudioManager.getInstance().loadAudio(getContext(), mAudioListAdapter);
                } else {
                    toast(R.string.read_storage_permission_deny);
                }
                break;
        }
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*            Intent intent = new Intent(getContext(), AudioPlayerActivity.class);
            Cursor cursor = (Cursor) mAudioListAdapter.getItem(position);
            String string = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            intent.putExtra(Constant.Extra.AUDIO_PATH, string);
            startActivity(intent);*/
            Intent intent = new Intent(getContext(), AudioPlayerActivity.class);
            intent.putExtra(Constant.Extra.AUDIO_POSITION, position);
            startActivity(intent);
        }
    };

}
