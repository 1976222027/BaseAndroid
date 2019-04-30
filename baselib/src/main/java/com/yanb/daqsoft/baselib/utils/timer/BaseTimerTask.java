package com.yanb.daqsoft.baselib.utils.timer;

import java.util.TimerTask;

/**
 * 倒计时基础工具类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-4-30.16:44
 * @since JDK 1.8
 */

public class BaseTimerTask extends TimerTask{
    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener !=null){
            mITimerListener.onTimer();
        }
    }
}
