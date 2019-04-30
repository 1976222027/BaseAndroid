package com.daqsoft.branch_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.utils.timer.ITimerListener;

/**
 * 引导页加倒计时
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-4-30.17:36
 * @since JDK 1.8
 */

public class SplashFragment extends MainSlidingFragment implements ITimerListener{
    @Override
    public Object getLayout() {
        return null;
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
