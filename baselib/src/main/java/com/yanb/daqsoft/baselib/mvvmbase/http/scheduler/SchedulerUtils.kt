package com.yanb.daqsoft.baselib.mvvmbase.http.scheduler

/**
 * Created by xuhao on 2017/11/17.
 * desc:
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
