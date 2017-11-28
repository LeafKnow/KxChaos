package com.yq.action.ui.act.start.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.github.paolorotolo.appintro.AppIntro;
import com.yq.action.R;
import com.yq.action.ui.act.main.MainAct;
import com.yq.action.ui.act.start.SplashAct;
import com.yq.action.ui.fmt.start.guide.SampleSlide;

/**
 * 引导界面
 */
public class GuideAct extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSkipButton(false);
        showDoneButton(false);
        setSeparatorColor(0);
        setProgressBarIndeterminateVisibility(false);
        addSlide(SampleSlide.newInstance(R.layout.yd_layout_one));
        addSlide(SampleSlide.newInstance(R.layout.yd_layout_two));
        addSlide(SampleSlide.newInstance(R.layout.yd_layout_three));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void joinMain(View view){
        ActivityUtils.startActivity(new Intent(GuideAct.this, MainAct.class));
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        ActivityUtils.finishActivity(GuideAct.this);
    }
}
