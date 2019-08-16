package com.yanb.daqsoft.baselib.mvvmbase.http

/**
 *返回数据基类
 */
class BaseResponse<T>(val code:Int,val message:String,val data :T,val datas:T) {
    /**
     * 返回是否成功
     */
    val isOk: Boolean
        get() = code == 0
}
