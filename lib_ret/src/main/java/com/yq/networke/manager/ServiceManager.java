package com.yq.networke.manager;

import android.support.v4.util.ArrayMap;

import com.yq.networke.api.ApiService;

/**
 * Created by xdj on 16/3/14.
 * 接口管理
 */
public class ServiceManager {
    private static final ArrayMap<Class, Object> mServiceMap = new ArrayMap<>();

    public static <T> T create(Class<T> serviceClass) {
        Object service = mServiceMap.get(serviceClass);
        if (service == null) {
            service = RetrofitManager.INSTANCE.net().create(serviceClass);
            mServiceMap.put(serviceClass, service);
        }

        return (T) service;
    }
    public <T> T defCreate(){
        return (T) create(ApiService.class);
    }
}
