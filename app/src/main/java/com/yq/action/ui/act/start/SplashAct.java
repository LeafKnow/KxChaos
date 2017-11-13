package com.yq.action.ui.act.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yq.action.R;
import com.yq.action.model.sys.SystemCount;
import com.yq.action.ui.act.main.MainAct;
import com.yq.base.ui.act.KitBaseAct;

import java.util.List;

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
//                if (SystemCount.getSystemDao().loadAll().size()>0){
//                    //启动引导界面
//
//                }else {//启动主界面
//                    startActivity(new Intent(SplashAct.this, MainActivity.class));
//                }
                startActivity(new Intent(SplashAct.this, MainAct.class));
                SplashAct.this.finish();
            }
        },5000);
        startCount();
    }

    @Override
    public void setListener() {

    }
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
}
