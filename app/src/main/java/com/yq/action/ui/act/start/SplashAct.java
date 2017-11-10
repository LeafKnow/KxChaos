package com.yq.action.ui.act.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yq.action.MainActivity;
import com.yq.action.R;
import com.yq.base.ui.act.KitBaseAct;

/***
 * 启动界面
 */
public class SplashAct extends KitBaseAct {

    @Override
    protected void setTitleBar() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashAct.this, MainActivity.class));
                SplashAct.this.finish();
            }
        },5000);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }
}
