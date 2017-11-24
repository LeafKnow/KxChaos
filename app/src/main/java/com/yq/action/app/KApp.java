package com.yq.action.app;

import android.util.Log;

import com.amitshekhar.DebugDB;
import com.yq.base.app.KitBaseApp;
import com.yq.base.common.cache.DataCleanManager;
import com.yq.common.db.GreenDaoManager;

/**
 * Created by njh on 2017/11/9.
 */

public class KApp extends KitBaseApp{

    @Override
    public void onCreate() {
        super.onCreate();
       // FreelineCore.init(this);
        GreenDaoManager.getInstance();
       Log.e("DebugDB",DebugDB.getAddressLog());
        try {
            Log.e("TotalCacheSize",DataCleanManager.getTotalCacheSize(this)+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
