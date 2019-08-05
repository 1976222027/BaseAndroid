package com.yanb.daqsoft.baseandroid.data.source

import io.reactivex.Observable

interface HttpDataSource {
    //模拟登录
    fun login(): Observable<Any>
}