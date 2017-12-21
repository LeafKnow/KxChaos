package me.leefeng.promptlibrary.util;

import android.app.Activity;
import android.content.Context;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by njh on 2017/12/21.
 */

public class PromptViewDialogUtil {
    private PromptDialog promptDialog;
    static PromptViewDialogUtil promptViewDialogUtil;
    public static PromptViewDialogUtil create(Context context){
        if (null==promptViewDialogUtil){
            promptViewDialogUtil= new PromptViewDialogUtil(context);
        }
        return promptViewDialogUtil;
    }
    Context mCintext;
    public PromptViewDialogUtil(Context context){

        mCintext=context;
        //创建对象
        promptDialog = new PromptDialog((Activity) context);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
    }

    public PromptDialog getPromptDialog() {
        return promptDialog;
    }
}
