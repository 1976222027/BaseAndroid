package com.yanb.daqsoft.baseandroid.data

/**
 * 本地数据的接口
 */
interface LocalDataSource {
    fun saveUserName(name:String)
    fun savePsd(psd:String)
    fun getUserName():String
    fun getPsd():String
}