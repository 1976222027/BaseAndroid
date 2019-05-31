package com.daqsoft.branch_login;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daqsoft.customview.progress.CircleProgressBar;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.utils.BarUtils;
import com.yanb.daqsoft.baselib.utils.timer.BaseTimerTask;
import com.yanb.daqsoft.baselib.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 引导页加倒计时
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-4-30.17:36
 * @since JDK 1.8
 */

public class SplashFragment extends MainSlidingFragment {
    @BindView(R2.id.line_progress)
    CircleProgressBar lineProgress;
    @BindView(R2.id.solid_progress)
    CircleProgressBar solidProgress;
    @BindView(R2.id.custom_progress1)
    CircleProgressBar customProgress1;
    @BindView(R2.id.custom_progress2)
    CircleProgressBar customProgress2;
    @BindView(R2.id.custom_progress3)
    CircleProgressBar customProgress3;
    @BindView(R2.id.custom_progress4)
    CircleProgressBar customProgress4;
    @BindView(R2.id.custom_progress5)
    CircleProgressBar customProgress5;
    @BindView(R2.id.custom_progress6)
    CircleProgressBar customProgress6;


    @Override
    public Object getLayout() {
        return R.layout.splash_fragment;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        BarUtils.setStatusBarVisibility(getSupportDelegate().getActivity(), false);
        simulateProgress();
        customProgress5.setProgressFormatter(new CircleProgressBar.ProgressFormatter() {
            @Override
            public CharSequence format(int progress, int max) {
                Logger.e(progress+"--"+max);
                return  "跳过";
            }
        });

        // 隐藏内容显示
        customProgress6.setProgressFormatter(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (animator.isRunning()){
            animator.resume();
        }else {
            animator.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (animator.isRunning()){
            animator.pause();
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
    private ValueAnimator animator;
    private void simulateProgress() {
        animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                lineProgress.setProgress(progress);
                solidProgress.setProgress(progress);
                customProgress1.setProgress(progress);
                customProgress2.setProgress(progress);
                customProgress3.setProgress(progress);
                customProgress4.setProgress(progress);
                customProgress5.setProgress(progress);
                customProgress6.setProgress(progress);
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(5000);
    }

}
