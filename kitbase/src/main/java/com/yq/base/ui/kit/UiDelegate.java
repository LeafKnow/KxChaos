package com.yq.base.ui.kit;

import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;

import rx.Observable;


/**
 * Created by wanglei on 2016/12/1.
 */

public interface UiDelegate {

    void resume();
    void pause();
    void destory();

    void visible(boolean flag, View view);
    void gone(boolean flag, View view);
    void inVisible(View view);

    void toastShort(String msg);
    void toastLong(String msg);
    Observable click(View view);
    String getSubText();
    void showProgressDialog(String msg);
    void dismissProgressDialog();
    void startAct(String path);
}
