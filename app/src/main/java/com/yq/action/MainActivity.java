package com.yq.action;

import android.os.Bundle;
import android.view.View;

import com.yq.base.ui.act.KitBaseAct;
import com.yq.base.widget.LoadingLayout;

import butterknife.BindView;

public class MainActivity extends KitBaseAct {

//    @BindView(R.id.loading_view)
//    LoadingLayout loadingView;

//    @BindView(R.id.tv_text)
//    TextView tvText;

    @Override
    public void initData(Bundle savedInstanceState) {
        //tvText= (TextView) findViewById(R.id.tv_text);

    }

    @Override
    public void setListener() {
//        getUiDelegate().click(tvText).subscribe(new Action1() {
//            @Override
//            public void call(Object o) {
//                // 1. 应用内简单的跳转(通过URL跳转在'中阶使用'中)
//                ARouter.getInstance().build("/test/MainActivity1").navigation();
//            }
//        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void setTitleBar() {
        titleBar.setTitleMainText("主标题");
        setTitleLine(true);
        titleBar.setVisibility(View.GONE);

    }

}
