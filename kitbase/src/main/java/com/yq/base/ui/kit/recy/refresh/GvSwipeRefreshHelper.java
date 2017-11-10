package com.yq.base.ui.kit.recy.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.List;

/**
 * Created by win7 on 2016/3/16.
 */
public class GvSwipeRefreshHelper {
    public interface OnSwipeRefreshListener {

        void onRefresh(int page);

        void onLoad(int page);
    }

    private static volatile GvSwipeRefreshHelper helper;
    public static final int REFRESH = 0;
    public static final int LOAD = 1;
    public static final int EMPTY = 2;
    private int mCurrentStatus = 0;
    private int mPage = 0;
    private SwipeRefreshLayout mSwipeRefresh;
    private GridView mGridView;
    private OnSwipeRefreshListener mOnListener;


    public static GvSwipeRefreshHelper get() {
        if (helper == null) {
            synchronized (GvSwipeRefreshHelper.class) {
                if (helper == null) {
                    helper = new GvSwipeRefreshHelper();
                }
            }
        }
        return helper;
    }

    public void create(SwipeRefreshLayout swipeRefreshLayout, final GridView gridView,
                       final OnSwipeRefreshListener onSwipeRefreshListener, int... colors) {
        if (swipeRefreshLayout != null && gridView != null) {
            this.mSwipeRefresh = swipeRefreshLayout;
            this.mGridView = gridView;
            this.mOnListener = onSwipeRefreshListener;
            refresh(colors);
            load();
        }
    }

    private void refresh(int[] colors) {
        mSwipeRefresh.setColorSchemeResources(colors);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentStatus = REFRESH;
                mPage = 0;
                mOnListener.onRefresh(mPage);
            }
        });
    }

    private void load() {
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (mCurrentStatus == EMPTY) {
                        } else {
                            if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
//                                mSwipeRefresh.setRefreshing(true);
                                mCurrentStatus = LOAD;
                                mPage++;
                                mOnListener.onLoad(mPage);
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    public <T> void notifyRefresh(BaseAdapter adapter, List<T> container, List<T> data) {
        if (adapter != null
                && container != null
                && data != null) {
            if (data.size() == 0) {
                mCurrentStatus = EMPTY;
            } else {
                switch (mCurrentStatus) {
                    case REFRESH:
                        container.clear();
                        container.addAll(data);
                        break;
                    case LOAD:
                        container.addAll(data);
                        break;
                }
            }
            adapter.notifyDataSetChanged();
            mSwipeRefresh.setRefreshing(false);
        }
    }

    public int getCurrentStatus() {
        return mCurrentStatus;
    }

    public void setStatus(int status) {
        mCurrentStatus = status;
    }
}
