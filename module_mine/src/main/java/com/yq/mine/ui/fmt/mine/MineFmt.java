package com.yq.mine.ui.fmt.mine;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.common.config.RouteConfig;
import com.yq.mine.R;
import com.yq.mine.R2;
import com.yq.networke.api.ApiService;
import com.yq.networke.manager.RxSchedulers;
import com.yq.networke.manager.ServiceManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Map<String,String> parm= new HashMap<>();
        parm.put("city","北京");
        Observable observable= ServiceManager.create(ApiService.class)
                .get("http://www.sojson.com/open/api/weather/json.shtml",parm)
                .compose(RxSchedulers.io_main());
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e("onNext","onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
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
        getUiDelegate().startFmt(RouteConfig.FmtConfig.CHANGE_PWD_FMT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_fmt;
    }

}
