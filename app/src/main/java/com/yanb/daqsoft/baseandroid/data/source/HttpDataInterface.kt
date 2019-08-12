package com.yanb.daqsoft.baseandroid.data.source

import com.daqsoft.xhttp.response.BaseResponse
import com.yanb.daqsoft.baseandroid.login.User
import io.reactivex.Observable

interface HttpDataInterface {
    /**
     * 登录
     */
    fun login(ignoreCode:String,account:String,pasd:String):Observable<BaseResponse<User>>
}