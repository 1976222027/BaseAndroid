package com.yanb.daqsoft.baselib.mvvmbase.http.exception

/**
 * 接口请求错误封装
 */
class ApiException :RuntimeException{
    private var code :Int? = null
    constructor(throwable: Throwable,code:Int):super(throwable){
        this.code = code
    }
    constructor(message:String):super(Throwable(message))
}