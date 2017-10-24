package com.yq.base.ui.kit;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.aries.ui.util.RomUtil;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;


/**
 * Created by wanglei on 2016/12/1.
 */

public class UiDelegateBase implements UiDelegate {

    public Context context;

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

}
