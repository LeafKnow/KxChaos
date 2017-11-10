package com.yq.base.ui.kit.recy.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yq.base.R;

import java.util.List;

/**
 * Created by Kaizen on 2015/9/16.
 */
public class LvSwipeRefreshHelper {

    public interface OnSwipeRefreshListener {

        void onRefresh(int page);

        void onLoad(int page);
    }

    private static volatile LvSwipeRefreshHelper helper;
    public static final int REFRESH = 0;
    public static final int LOAD = 1;
    public static final int EMPTY = 2;
    private int mCurrentStatus = 0;
    private View mFooter;
    private int mPage = 0;
    private SwipeRefreshLayout mSwipeRefresh;
    private ListView mListView;
    private OnSwipeRefreshListener mOnListener;

    public LvSwipeRefreshHelper(Context context) {
        mFooter = LayoutInflater.from(context).inflate(R.layout.pull_load_success, null);
    }

    public static LvSwipeRefreshHelper get(Context context) {
        if (helper == null) {
            synchronized (LvSwipeRefreshHelper.class) {
                if (helper == null) {
                    helper = new LvSwipeRefreshHelper(context);
                }
            }
        }
        return helper;
    }

    public void create(SwipeRefreshLayout swipeRefreshLayout, final ListView listView,
                       final OnSwipeRefreshListener onSwipeRefreshListener, int... colors) {
        if (swipeRefreshLayout != null && listView != null) {
            this.mSwipeRefresh = swipeRefreshLayout;
            this.mListView = listView;
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
                if (mListView.getFooterViewsCount() > 0) {
                    mListView.removeFooterView(mFooter);
                }
            }
        });
    }

    private void load() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (mCurrentStatus == EMPTY) {
                            if (mListView.getFooterViewsCount() < 1) {
                                mListView.addFooterView(mFooter);
                            }
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
