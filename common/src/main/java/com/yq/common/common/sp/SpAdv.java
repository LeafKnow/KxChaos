package com.yq.common.common.sp;

import android.content.Context;

/**
 * Created by njh on 2017/11/15.
 * 广告缓存文件
 */

public class SpAdv {

    static {
        SP.FILE_NAME="AdvInfo";
    }
    public static final String ADV_CACHE="advCache";//是否有广告缓存
    public static boolean getAdvCache(Context context) {
        return (boolean) SP.get(context, ADV_CACHE, false);
    }

    public static void setAdvCache(Context context, boolean advCache) {
        SP.put(context, ADV_CACHE, advCache);
    }


}
