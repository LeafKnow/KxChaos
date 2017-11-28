package com.yq.base.ui.mvp;

import java.util.Map;

import rx.Observable;

/**
 * Created by niejiahuan on 2016/5/31.
 */
public interface BaseModel {
    /**
     * 获取网络数据
     * @param map
     * @return
     */
    Observable getDate(Map<String, String> map);
}
