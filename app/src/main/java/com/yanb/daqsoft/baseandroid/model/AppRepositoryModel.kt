package com.yanb.daqsoft.baseandroid.model


import com.yanb.daqsoft.baseandroid.data.source.LocalDataSource
import com.yanb.daqsoft.baseandroid.data.source.HttpDataInterface
import com.yanb.daqsoft.baseandroid.login.User
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseModel
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import io.reactivex.Observable

/**
 * MVVM的Model层，统一模块的数据仓库，
 * 包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
class AppRepositoryModel private constructor(private val localDataSource: LocalDataSource,
                                             private val httpDataInterface: HttpDataInterface):BaseModel(), LocalDataSource, HttpDataInterface {
    override fun login(ignoreCode:String,account:String,pasd:String): Observable<BaseResponse<User>> {
        return httpDataInterface.login(ignoreCode,account,pasd)
    }

    override fun getUserName(): String =localDataSource.getUserName()

    override fun getPsd(): String =localDataSource.getPsd()

    override fun saveUserName(name: String) {
        localDataSource.saveUserName(name)
    }

    override fun savePsd(psd: String) {
        localDataSource.savePsd(psd)
    }

    /**
     * 单列
     */
    companion object{
        @Volatile
        private var instance:AppRepositoryModel?=null
        fun getInstance(localDataSource: LocalDataSource, httpDataInterface: HttpDataInterface)= instance?: synchronized(this){
            instance?:AppRepositoryModel(localDataSource,httpDataInterface).also {
                instance = it
            }
        }
    }
}