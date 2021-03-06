package com.yq.action.ui.act.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.blankj.utilcode.util.ActivityUtils;
import com.yq.action.R;
import com.yq.action.ui.act.main.MainAct;
import com.yq.action.ui.act.start.guide.GuideAct;
import com.yq.action.ui.fmt.start.adv.AdvFmt;
import com.yq.base.ui.act.KitBaseAct;
import com.yq.common.common.sp.SpAdv;
import com.yq.common.model.sys.SystemCount;

import java.util.List;

/***
 * 启动界面
 */
public class SplashAct extends KitBaseAct {
    Handler handler;
    @Override
    protected void setTitleBar() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

        if (SystemCount.getSystemDao().loadAll().size()>0){//启动主界面 判断是否第一次开启app
            if (SpAdv.getAdvCache()){//判断是否有广告缓存
                //加载广告
                loadRootFragment(R.id.fl_adv_container, AdvFmt.newInstance());
            }else {//进入主界面
                startMain(3000);
            }
        }else {
            SpAdv.setAdvCache(true);
            //启动引导界面
            ActivityUtils.startActivity(new Intent(SplashAct.this,GuideAct.class));
            ActivityUtils.finishActivity(SplashAct.this);
        }
        startCount();
    }

    @Override
    public void setListener() {

    }
    public void startMain(int time){
        if (null==handler) {
            handler = new Handler();
        }
        handler.removeCallbacks(mRunnable);
        handler.postDelayed(mRunnable,time);
    }
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            ActivityUtils.startActivity(new Intent(SplashAct.this, MainAct.class));
            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
            ActivityUtils.finishActivity(SplashAct.this);
        }
    };
    public void startCount(){
        SystemCount systemCount=new SystemCount();
        List<SystemCount> systemCounts = SystemCount.getSystemDao().loadAll();
        if (systemCounts.size()>0){
            systemCount.setStartCount(systemCounts.get(0).getStartCount()+1);
        }else {
            systemCount.setStartCount(1l);
            SystemCount.getSystemDao().insert(systemCount);
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean initPhoto() {
        return false;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        if (null!=handler){
            handler.removeCallbacks(mRunnable);
            handler=null;
        }
        super.onDestroy();
    }
}
