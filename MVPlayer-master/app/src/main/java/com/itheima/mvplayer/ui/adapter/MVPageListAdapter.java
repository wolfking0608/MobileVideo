package com.itheima.mvplayer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.itheima.mvplayer.model.MVPageBean;
import com.itheima.mvplayer.widget.MVPageListItemView;

import java.util.List;

public class MVPageListAdapter extends RecyclerView.Adapter<MVPageListAdapter.MVPageListItemViewHolder> {
    public static final String TAG = "MVPageListAdapter";

    private Context mContext;
    private List<MVPageBean.VideosBean> mVideos;

    public MVPageListAdapter(Context context, List<MVPageBean.VideosBean> videos) {
        mContext = context;
        mVideos = videos;
    }

    @Override
    public MVPageListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MVPageListItemViewHolder(new MVPageListItemView(mContext));
    }

    @Override
    public void onBindViewHolder(MVPageListItemViewHolder holder, int position) {
        holder.mMVPageListItemView.bindView(mVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public class MVPageListItemViewHolder extends RecyclerView.ViewHolder {

        private MVPageListItemView mMVPageListItemView;

        public MVPageListItemViewHolder(MVPageListItemView itemView) {
            super(itemView);
            mMVPageListItemView = itemView;
        }
    }
}
