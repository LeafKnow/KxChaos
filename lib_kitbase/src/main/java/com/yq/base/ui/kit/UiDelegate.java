package com.yq.base.ui.kit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.yq.base.common.camera.OpenPhoto;

import me.leefeng.promptlibrary.PromptDialog;
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
    void startFmt(String path);
    void onCreate(@Nullable Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);
    OpenPhoto getOpenPhoto();
    void showOpenPhoto(View v);
    PromptDialog getPromptDialog();

}
