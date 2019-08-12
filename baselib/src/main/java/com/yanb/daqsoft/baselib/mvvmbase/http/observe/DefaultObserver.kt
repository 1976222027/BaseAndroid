package com.yanb.daqsoft.baselib.mvvmbase.http.observe

import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import com.yanb.daqsoft.baselib.mvvmbase.http.exception.ExceptionHandle
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class DefaultObserver<T>:Observer<T> {
    /**
     * 请求成功
     * @param response 服务器返回数据
     */
    abstract fun onSuccess(response: T?)
    /**
     * 服务器返回异常在这里统一处理
     */
    abstract fun onFail(message: String?)

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(response: T) {
       if ((response as BaseResponse<*>).isOk){
           onSuccess(response)
       }else{
           onFail((response as BaseResponse<*>).message)
       }
    }

    override fun onError(e: Throwable) {
        ExceptionHandle.handleException(e)
    }

    override fun onComplete() {

    }

}