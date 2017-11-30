package com.yq.networke.manager;


import com.yq.networke.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by niejiahuan on 16/8/1.
 */
public class RxFunc1<T extends ResultBean> implements Function<ResultBean<T>, Observable<?>> {

    @Override
    public Observable<?> apply(ResultBean<T> tResultBean) throws Exception {
        return RetroUtil.flatResult(tResultBean);
    }
}
