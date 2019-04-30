package com.daqsoft.branch_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.utils.timer.ITimerListener;

import butterknife.BindView;

/**
 * 引导页加倒计时
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-4-30.17:36
 * @since JDK 1.8
 */

public class SplashFragment extends MainSlidingFragment implements ITimerListener{
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    @Override
    public Object getLayout() {
        return R.layout.splash_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }

    @Override
    public void onTimer() {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
