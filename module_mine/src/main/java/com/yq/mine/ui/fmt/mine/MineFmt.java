package com.yq.mine.ui.fmt.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.common.config.RouteConfig;
import com.yq.mine.R;
import com.yq.mine.R2;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * 个人中心
 */
@Route(path = RouteConfig.FmtConfig.MINE_FMT)
public class MineFmt extends KitBaseFmt {
    @BindView(R2.id.tv_open_pwd)
    TextView tvOpenPwd;


    public MineFmt() {
        // Required empty public constructor
    }

    public static MineFmt newInstance() {
        MineFmt fragment = new MineFmt();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setTitleBar() {
        titleBar.setTitleMainText("");
        titleBar.setVisibility(View.GONE);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        getUiDelegate().click(tvOpenPwd).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                openChangePwd();
            }
        });
    }


    public void openChangePwd() {
        getUiDelegate().startFmt(RouteConfig.FmtConfig.ChangePwd_FMT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_fmt;
    }

}
