package com.yq.action;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.aries.ui.view.title.TitleBarView;
import com.yq.base.ui.act.KitBaseAct;
import com.yq.base.widget.LoadingLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends KitBaseAct {

    @BindView(R.id.loading_view)
    LoadingLayout loadingView;

//    @BindView(R.id.tv_text)
//    TextView tvText;

    @Override
    public void initData(Bundle savedInstanceState) {
        //tvText= (TextView) findViewById(R.id.tv_text);
        loadingView.showLoading();
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
        loadingView.setOnloadingListener(new LoadingLayout.OnloadingListener() {
            @Override
            public void onEmpty() {
                loadingView.showContent();
            }

            @Override
            public void onError() {
                loadingView.showNoNetworke();
            }

            @Override
            public void noWorke() {
                loadingView.showEmpty();
            }
        });
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                titleBar.setVisibility(View.VISIBLE);
                loadingView.showError();
            }
        },5000);

    }

}
