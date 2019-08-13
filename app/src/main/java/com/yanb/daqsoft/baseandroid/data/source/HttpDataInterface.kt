package com.yanb.daqsoft.baseandroid.data.source

import com.yanb.daqsoft.baseandroid.login.User
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import io.reactivex.Observable

interface HttpDataInterface {
    /**
     * 登录
     */
    fun login(ignoreCode:String,account:String,pasd:String):Observable<BaseResponse<User>>
}