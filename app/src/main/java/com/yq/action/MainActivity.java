package com.yq.action;

import android.os.Bundle;
import android.widget.TextView;

import com.yq.base.ui.act.KitBaseAct;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends KitBaseAct {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    public void initData(Bundle savedInstanceState) {
        //tvText= (TextView) findViewById(R.id.tv_text);
    }

    @Override
    public void setListener() {
        getUiDelegate().click(tvText).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                tvText.setText("set kit base");
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

    }

}
