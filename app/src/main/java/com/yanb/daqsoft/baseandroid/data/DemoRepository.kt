package com.yanb.daqsoft.baseandroid.data

import android.support.annotation.VisibleForTesting
import com.yanb.daqsoft.baseandroid.data.source.HttpDataSource
import com.yanb.daqsoft.baseandroid.data.source.LocalDataSource
import com.yanb.daqsoft.baselib.mvvmbase.BaseModel

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
class DemoRepository private constructor(private val httpDataSource: HttpDataSource,private val localDataSource: LocalDataSource):BaseModel(),HttpDataSource,LocalDataSource{
    /**
     * 双重校验锁带参数
     */
    companion object{
        @Volatile
        private var instance : DemoRepository?=null
        fun getInstance(httpDataSource: HttpDataSource,localDataSource: LocalDataSource)= instance?: synchronized(this){
            instance?:DemoRepository(httpDataSource,localDataSource).also {
                instance = it
            }
        }
    }

    @VisibleForTesting
    fun destroyInstance() {
        instance = null
    }

    override fun saveUserName(name: String) {
        localDataSource.saveUserName(name)
    }

    override fun savePassword(password: String) {
        localDataSource.savePassword(password)
    }

    override fun getUserName(): String {
        return localDataSource.getUserName()
    }

    override fun getPassword(): String {
        return localDataSource.getPassword()
    }

}