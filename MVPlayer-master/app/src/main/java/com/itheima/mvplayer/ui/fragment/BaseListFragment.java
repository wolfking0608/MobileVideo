package com.itheima.mvplayer.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.itheima.mvplayer.R;
import com.itheima.mvplayer.presenter.BaseListPresenter;
import com.itheima.mvplayer.view.BaseListView;

import butterknife.BindView;

public abstract class BaseListFragment extends BaseFragment implements BaseListView{

    public static final String TAG = "BaseListFragment";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private BaseListPresenter mBaseListPresenter;
    private LinearLayoutManager mLinearLayoutManager;

    private RecyclerView.Adapter mAdapter;


    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void init() {
        super.init();
        mBaseListPresenter = getPresenter(this);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(mOnRefreshListener);
        initRecyclerView();

        mBaseListPresenter.loadListData();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = getListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    public abstract RecyclerView.Adapter getListAdapter();

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (mLinearLayoutManager.findLastVisibleItemPosition() == mBaseListPresenter.getListData().size() - 1) {
                    mBaseListPresenter.loadMoreListData();
                }
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mBaseListPresenter.refresh();
        }
    };

    public abstract BaseListPresenter getPresenter(BaseListView view);

    @Override
    public void onLoadListDataSuccess() {
        toast(R.string.load_data_success);
        mAdapter.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onLoadListDataFailed() {
        toast(R.string.load_data_failed);
    }

    @Override
    public void onLoadMoreListDataFailed() {
        toast(R.string.load_more_data_failed);
    }

    @Override
    public void onLoadMoreListDataSuccess() {
        toast(R.string.load_more_data_success);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoMoreData() {
        toast(R.string.no_more_data);
    }
}
