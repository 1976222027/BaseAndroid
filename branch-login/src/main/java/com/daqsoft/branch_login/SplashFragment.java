package com.daqsoft.branch_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.utils.BarUtils;
import com.yanb.daqsoft.baselib.utils.timer.BaseTimerTask;
import com.yanb.daqsoft.baselib.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

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

    private Timer mTimer = null;
    private int mCount = 5 ;

    @Override
    public Object getLayout() {
        return R.layout.splash_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        BarUtils.setStatusBarVisibility(getBaseSupportActivity(),false);
        initTimer();
    }

    /**
     * 初始化时间
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onTimer() {
        getBaseSupportActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer !=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer=null;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
