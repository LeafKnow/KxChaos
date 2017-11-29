package com.yq.chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yq.base.ui.fmt.KitBaseFmt;

import action.yq.com.module_chat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFmt extends KitBaseFmt {


    public ChatFmt() {
        // Required empty public constructor
    }
    public static ChatFmt newInstance() {
        ChatFmt fragment = new ChatFmt();
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
        return R.layout.fragment_chat_fmt;
    }
}
