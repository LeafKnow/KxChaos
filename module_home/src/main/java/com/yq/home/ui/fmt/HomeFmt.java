package com.yq.home.ui.fmt;


import android.os.Bundle;

import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.home.R;


/**
 * 主Fmt
 */
public class HomeFmt extends KitBaseFmt {


    public HomeFmt() {
        // Required empty public constructor
    }
    public static HomeFmt newInstance() {
        HomeFmt fragment = new HomeFmt();
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

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_fmt;
    }
}
