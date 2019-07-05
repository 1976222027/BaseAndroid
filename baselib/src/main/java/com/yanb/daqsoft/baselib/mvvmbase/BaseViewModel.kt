package com.yanb.daqsoft.baselib.mvvmbase

import android.arch.lifecycle.DefaultLifecycleObserver
import android.databinding.BaseObservable
/**
 * ViewModel的基类
 * 数据绑定
 */
abstract class BaseViewModel:BaseObservable(), DefaultLifecycleObserver {

}