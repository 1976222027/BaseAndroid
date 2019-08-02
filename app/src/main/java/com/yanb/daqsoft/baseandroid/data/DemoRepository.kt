package com.yanb.daqsoft.baseandroid.data

import com.yanb.daqsoft.baseandroid.data.source.HttpDataSource
import com.yanb.daqsoft.baseandroid.data.source.LocalDataSource
import com.yanb.daqsoft.baselib.mvvmbase.BaseModel

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
class DemoRepository :BaseModel(),HttpDataSource,LocalDataSource{
    override fun saveUserName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePassword(password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPassword(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}