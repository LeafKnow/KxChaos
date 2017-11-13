package com.yq.action.app;

import android.content.Context;

import com.yq.action.db.GreenDaoManager;
import com.yq.base.app.KitBaseApp;

/**
 * Created by njh on 2017/11/9.
 */

public class KApp extends KitBaseApp{
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        GreenDaoManager.getInstance();
    }

}
