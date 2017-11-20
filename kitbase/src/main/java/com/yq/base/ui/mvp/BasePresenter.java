package com.yq.base.ui.mvp;

import android.app.Activity;

import java.util.Map;

import rx.Observable;

/**
 * Created by niejiahuan on 2016/5/31.
 */
public abstract class BasePresenter<E extends BaseModel, T> {
    public Activity mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m, Activity context) {
        this.mView = v;
        this.mModel = m;
        this.mContext = context;
        this.onStart();
    }

    public abstract void onStart();

    /**
     * 获取数据
     *
     * @param params 参数集
     * @return
     */
    public Observable getDate(Map<String, String> params) {
        return mModel.getDate(params);
    }

    public void onDestroy() {
    }
}
