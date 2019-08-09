package com.yanb.daqsoft.baseandroid.data

import com.yanb.daqsoft.baseandroid.common.SpTagConstants
import com.yanb.daqsoft.baselib.mvvmbase.utils.SPUtils

class LocalDataSourceImpl : LocalDataSource {

    /**
     * 单列无参数情况
     * 双重校验锁
     */
    companion object {
        val instance: LocalDataSourceImpl by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LocalDataSourceImpl()
        }
    }

    override fun saveUserName(name: String) {
        SPUtils.getInstance().put(SpTagConstants.SP_USERNAME, name)
    }

    override fun savePsd(psd: String) {
        SPUtils.getInstance().put(SpTagConstants.SP_PASWORD, psd)
    }

    override fun getUserName(): String = SPUtils.getInstance().getString(SpTagConstants.SP_USERNAME)

    override fun getPsd(): String = SPUtils.getInstance().getString(SpTagConstants.SP_PASWORD)


}