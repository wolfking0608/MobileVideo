package com.itheima.mvplayer.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.mvplayer.app.Constant;
import com.itheima.mvplayer.model.HomeItemBean;
import com.itheima.mvplayer.ui.activity.MVPlayerActivity;
import com.itheima.mvplayer.widget.HomeListItemView;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListItemViewHolder> {
    public static final String TAG = "HomeListAdapter";

    private Context mContext;
    private List<HomeItemBean> mHomeItemBeanList;

    public HomeListAdapter(Context context, List<HomeItemBean> homeItemBeanList) {
        mContext = context;
        mHomeItemBeanList = homeItemBeanList;
    }

    @Override
    public HomeListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeListItemViewHolder(new HomeListItemView(mContext));
    }

    @Override
    public void onBindViewHolder(HomeListItemViewHolder holder, int position) {
        final HomeItemBean homeItemBean = mHomeItemBeanList.get(position);
        holder.mHomeListItemView.bindView(homeItemBean);
        holder.mHomeListItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MVPlayerActivity.class);
                intent.putExtra(Constant.Extra.VIDEO_URL, homeItemBean.getUrl());
                intent.putExtra(Constant.Extra.VIDEO_TITLE, homeItemBean.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHomeItemBeanList.size();
    }

    ;public class HomeListItemViewHolder extends RecyclerView.ViewHolder {

        private HomeListItemView mHomeListItemView;

        public HomeListItemViewHolder(HomeListItemView itemView) {
            super(itemView);
            mHomeListItemView = itemView;
        }
    }
}
