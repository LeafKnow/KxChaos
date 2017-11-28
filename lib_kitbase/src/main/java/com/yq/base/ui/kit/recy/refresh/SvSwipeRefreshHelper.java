package com.yq.base.ui.kit.recy.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ScrollView;

import com.yq.base.R;

import java.util.List;

/**
 * Scrollview加载
 */
public class SvSwipeRefreshHelper {

    public interface OnSwipeRefreshListener {

        void onRefresh();

        void onLoad();
    }

    private int index = 0;
    private static volatile SvSwipeRefreshHelper helper;
    public static final int REFRESH = 0;
    public static final int LOAD = 1;
    public static final int EMPTY = 2;
    private int mCurrentStatus = 0;
    private View mFooter;
    private int mPage = 0;
    private SwipeRefreshLayout mSwipeRefresh;
    private ScrollView scrollView;
    private OnSwipeRefreshListener mOnListener;

    private SvSwipeRefreshHelper(Context context) {
        mFooter = LayoutInflater.from(context).inflate(R.layout.pull_load_success, null);
    }

    public SvSwipeRefreshHelper() {

    }

    public static SvSwipeRefreshHelper get(Context context) {
        if (helper == null) {
            synchronized (SvSwipeRefreshHelper.class) {
                if (helper == null) {
                    helper = new SvSwipeRefreshHelper(context);
                }
            }
        }
        return helper;
    }

    public void create(SwipeRefreshLayout swipeRefreshLayout, final ScrollView scrollView,
                       final OnSwipeRefreshListener onSwipeRefreshListener, int... colors) {
        if (swipeRefreshLayout != null && scrollView != null) {
            this.mSwipeRefresh = swipeRefreshLayout;
            this.scrollView = scrollView;
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
                mOnListener.onRefresh();

            }
        });
    }

    private void load() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        index++;
                        break;
                    default:
                        break;
                }
                if (mCurrentStatus == EMPTY) {

                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP && index > 0&&!mSwipeRefresh.isRefreshing()) {
                        index = 0;
                        View view = ((ScrollView) v).getChildAt(0);
                        if (view.getMeasuredHeight() <= v.getScrollY() + v.getHeight()) {
//                            mSwipeRefresh.setRefreshing(true);
                            mCurrentStatus = LOAD;
                            mOnListener.onLoad();
                        }
                    }
                }
                return false;
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
