package com.yq.action.app;

import com.yq.base.app.KitBaseApp;
import com.yq.common.db.GreenDaoManager;

/**
 * Created by njh on 2017/11/9.
 */

public class KApp extends KitBaseApp{

    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoManager.getInstance();
    }

}
