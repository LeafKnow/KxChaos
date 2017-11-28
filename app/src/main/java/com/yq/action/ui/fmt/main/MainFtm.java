package com.yq.action.ui.fmt.main;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.action.R;
import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.common.config.RouteConfig;
import com.yq.common.event.StartBrotherEvent;
import com.yq.common.event.TabSelectedEvent;
import com.yq.common.widget.BottomBar;
import com.yq.common.widget.BottomBarTab;
import com.yq.mine.ui.fmt.mine.MineFmt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主 FTM
 */
@Route(path = RouteConfig.FmtConfig.MAIN_FMT)
public class MainFtm extends KitBaseFmt {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    @BindView(R.id.btnbar)
    BottomBar btnbar;

    private SupportFragment[] mFragments = new SupportFragment[4];

    public MainFtm() {
        // Required empty public constructor
    }

    public static MainFtm newInstance() {
        MainFtm fragment = new MainFtm();
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
        SupportFragment firstFragment = findChildFragment(MineFmt.class);
        if (firstFragment == null) {
            mFragments[FIRST] = MineFmt.newInstance();
            mFragments[SECOND] = MineFmt.newInstance();
            mFragments[THIRD] = MineFmt.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(MineFmt.class);
            mFragments[THIRD] = findChildFragment(MineFmt.class);
        }
        btnbar.addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_white_24dp,R.drawable.ic_message_white_24dp,"首页"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_account_circle_white_24dp,R.drawable.ic_message_white_24dp, "首页"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_discover_white_24dp,R.drawable.ic_message_white_24dp, "首页"));

        // 模拟未读消息
        btnbar.getItem(FIRST).setUnreadCount(-1);

        btnbar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                EventBus.getDefault().post(new TabSelectedEvent(position));
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_ftm;
    }

    /**
     * start other BrotherFragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        if (event.type == 0) {
            start(event.targetFragment);
        }
    }

    @Override
    public boolean eventRegister() {
        return true;
    }
    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
