package com.yq.common.common.sp;

import com.blankj.utilcode.util.SPUtils;

/**
 * Created by njh on 2017/11/15.
 * 广告缓存文件
 */

public class SpAdv {

    static {
        SPUtils.getInstance("AdvInfo");
    }
    public static final String ADV_CACHE="advCache";//是否有广告缓存
    public static boolean getAdvCache() {
        return SPUtils.getInstance().getBoolean(ADV_CACHE, false);
    }

    public static void setAdvCache(boolean advCache) {
        SPUtils.getInstance().put(ADV_CACHE, advCache);
    }


}
