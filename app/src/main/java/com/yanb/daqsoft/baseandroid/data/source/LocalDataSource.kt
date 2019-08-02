package com.yanb.daqsoft.baseandroid.data.source

/**
 * 本地数据
 */
interface LocalDataSource {
    // 保存用户名
    fun saveUserName(name:String)
    // 保存密码
    fun savePassword(password:String)
    // 获取用户名
    fun getUserName():String
    // 获取用户密码
    fun getPassword():String
}