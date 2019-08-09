package com.yanb.daqsoft.baseandroid.data.http

import io.reactivex.Observable

interface HttpDataInterface {
    /**
     * 登录
     */
    fun login():Observable<String>
}