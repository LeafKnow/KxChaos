package com.yq.action.ui.fmt.start.adv;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.yq.action.R;
import com.yq.action.ui.act.main.MainAct;
import com.yq.base.ui.fmt.KitBaseFmt;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * 启动页广告
 */
public class AdvFmt extends KitBaseFmt<AdvPresenter,AdvModel> implements AdvContract.View {

    Handler handler;
    @BindView(R.id.tv_join_mian)
    TextView tvJoinMian;

    public AdvFmt() {
        // Required empty public constructor
    }

    public static AdvFmt newInstance() {
        AdvFmt advFmt = new AdvFmt();
        Bundle args = new Bundle();
        advFmt.setArguments(args);
        return advFmt;
    }

    @Override
    protected void setTitleBar() {

    }

    @Override
    public void initPresenter() {
     mPresenter.setVM(this,mModel,this._mActivity);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //显示广告展示时间
        startMain(5000);
    }

    @Override
    public void setListener() {
          //设置跳过广告按钮事件
        RxView.clicks(tvJoinMian).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startMain(0);
            }
        });
    }

    public void startMain(int time) {
        handler=new Handler();
        handler.postDelayed(runnable, time);
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(runnable);
            startActivity(new Intent(_mActivity, MainAct.class));
            _mActivity.finish();

        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.fragment_adv_fmt;
    }

}
