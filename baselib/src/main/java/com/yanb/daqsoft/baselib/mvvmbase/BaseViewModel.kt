package com.yanb.daqsoft.baselib.mvvmbase

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.DefaultLifecycleObserver
import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * ViewModel的基类
 * 数据绑定
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private var mCompositeDisposable: CompositeDisposable? = null
    protected fun addDisposable(disposable: Disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = CompositeDisposable()
        }
        this.mCompositeDisposable!!.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (this.mCompositeDisposable != null && !mCompositeDisposable!!.isDisposed) {
            this.mCompositeDisposable!!.clear()
        }
    }
}