package com.yj.widget.heple;

import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by niejiahuan on 18/1/19.
 * RefHeple  刷新工具类
 */
public class SmartRefHeple {
    public int perpage = 10,overPage=1;
    private int currentPage = 1;
    SmartRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;

    public SmartRefHeple(SmartRefreshLayout swipeRefreshLayout,
                         RecyclerView recyclerView){
        mSwipeRefreshLayout=swipeRefreshLayout;
        mRecyclerView=recyclerView;
    }
    public void onLoadmore(){
        overPage=currentPage;
        overPage++;
    }
    public void onRefresh(){
        overPage=1;
    }

    public void reqDataSucc(long total){
        currentPage=overPage;
        int currentTotal = currentPage * perpage;
        if (currentTotal<total||currentTotal>total){
            if (null!=mSwipeRefreshLayout){
                reqComplet(0,true);
            }
        }else if (currentTotal==total){
            reqComplet(0,false);
        }
    }

    public void reqComplet(int delayed,boolean noMoreData){
        if (null!=mSwipeRefreshLayout){
            if (mSwipeRefreshLayout.isRefreshing()){
                if (delayed!=0){
                    mSwipeRefreshLayout.finishRefresh();
                }else {
                    mSwipeRefreshLayout.finishRefresh(delayed);
                }
                if (noMoreData){
                    mSwipeRefreshLayout.resetNoMoreData();//重新设置还要更多数据
                }
            }
            if (noMoreData){
                if (mSwipeRefreshLayout.isLoading()){
                    if (delayed!=0){
                        mSwipeRefreshLayout.finishLoadmore(delayed);//带结束加载状态时间
                    }else {
                        mSwipeRefreshLayout.finishLoadmore();//结束加载状态
                    }
                }
            }else {
                mSwipeRefreshLayout.finishLoadmoreWithNoMoreData();//结束加载状态，并标记已无更多数据
            }

        }
    }
}
