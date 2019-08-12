package com.yanb.daqsoft.baseandroid.ui.main.model

import android.app.Application
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel

class MainViewModel :BaseViewModel<AppRepositoryModel>{

    constructor(application: Application) :super(application){

    }
}