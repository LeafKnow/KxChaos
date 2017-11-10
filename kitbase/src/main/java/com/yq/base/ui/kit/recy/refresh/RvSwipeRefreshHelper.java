package com.yq.base.ui.kit.recy.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yq.base.R;

import java.util.List;

/**
 * RecylerView加载
 */
public class RvSwipeRefreshHelper {

    public interface OnSwipeRefreshListener {

        void onRefresh();

        void onLoad();
    }

    private static volatile RvSwipeRefreshHelper helper;
    public static final int REFRESH = 0;
    public static final int LOAD = 1;
    public static final int EMPTY = 2;
    private int mCurrentStatus = 0;
    private View mFooter;
    private int mPage = 0;

    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView recyclerView;
    private OnSwipeRefreshListener mOnListener;

    private RvSwipeRefreshHelper(Context context) {
        mFooter = LayoutInflater.from(context).inflate(R.layout.pull_load_success, null);
    }

    public RvSwipeRefreshHelper() {

    }

    public static RvSwipeRefreshHelper get(Context context) {
        if (helper == null) {
            synchronized (RvSwipeRefreshHelper.class) {
                if (helper == null) {
                    helper = new RvSwipeRefreshHelper(context);
                }
            }
        }
        return helper;
    }

    public void create(SwipeRefreshLayout swipeRefreshLayout, final RecyclerView recyclerView,
                       final OnSwipeRefreshListener onSwipeRefreshListener, int... colors) {
        if (swipeRefreshLayout != null && recyclerView != null) {
            this.mSwipeRefresh = swipeRefreshLayout;
            this.recyclerView = recyclerView;
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
                mOnListener.onRefresh();
            }
        });
    }

    private void load() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动，既是否向右滑动或向下滑动

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时

                if (mCurrentStatus == EMPTY) {
                } else {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最后一个完全显示的ItemPosition
                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                        int totalItemCount = manager.getItemCount();

                        // 判断是否滚动到底部，并且是向下滚动
                        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast && !mSwipeRefresh.isRefreshing()) {
                            //加载更多功能的代码
//                            mSwipeRefresh.setRefreshing(true);
                            mCurrentStatus = LOAD;
                            mOnListener.onLoad();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }

            }
        });
    }

    public <T> void notifyRefresh(RecyclerView.Adapter adapter, List<T> container, List<T> data) {
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
