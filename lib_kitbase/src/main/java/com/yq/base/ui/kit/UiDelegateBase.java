package com.yq.base.ui.kit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.aries.ui.util.RomUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.yq.base.common.camera.OpenPhoto;
import com.yq.base.event.StartBrotherEvent;
import com.yq.base.ui.fmt.KitBaseFmt;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;


/**
 * Created by njh on 2016/12/1.
 */

public class UiDelegateBase implements UiDelegate {

    public Context context;
    private ProgressDialog dialog;//加载状态
    private OpenPhoto moPenPhoto;
    private UiDelegateBase(Context context) {
        this.context = context;
    }



    public static UiDelegateBase create(Context context) {
        return new UiDelegateBase(context);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {
        if(dialog!=null){
            dialog.dismiss();
        }
        EventBus.getDefault().unregister(context);
    }

    @Override
    public void visible(boolean flag, View view) {
        if (flag) view.setVisibility(View.VISIBLE);
    }

    @Override
    public void gone(boolean flag, View view) {
        if (flag) view.setVisibility(View.GONE);
    }

    @Override
    public void inVisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toastShort(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Observable click(View view) {
        return RxView.clicks(view)
                .throttleFirst(1000, TimeUnit.MILLISECONDS);
    }
    public String getSubText() {
        String text = "Android" + Build.VERSION.RELEASE;
        if (RomUtil.isMIUI()) {
            text += "-MIUI" + RomUtil.getMIUIVersion();
        } else if (RomUtil.isFlyme()) {
            text += "-Flyme" + RomUtil.getFlymeVersionCode();
        } else if (RomUtil.isEMUI()) {
            text += "-EMUI" + RomUtil.getEMUIVersion();
        }
        return text;
    }

    @Override
    public void showProgressDialog(String text){
        if(dialog==null){
            dialog=new ProgressDialog(context);
        }
        if(dialog.isShowing())dialog.dismiss();
        dialog.setMessage(text);
        dialog.show();
    }
    @Override
    public void dismissProgressDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void startAct(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    @Override
    public void startFmt(String path) {
        SupportFragment k = (SupportFragment) ARouter.getInstance().build(path).navigation();
       EventBus.getDefault().post(new StartBrotherEvent(k));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        moPenPhoto=new OpenPhoto((Activity) context);
        getOpenPhoto().getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (null!=getOpenPhoto()){
            getOpenPhoto().getTakePhoto().onSaveInstanceState(outState);
        }

    }

    @Override
    public OpenPhoto getOpenPhoto() {
        return moPenPhoto;
    }

    @Override
    public void showOpenPhoto(View view) {
        if (null!=getOpenPhoto()){
            getOpenPhoto().openPhoto(view);
        }else {
            toastShort("拍照组件未初始化！");
        }
    }

}
