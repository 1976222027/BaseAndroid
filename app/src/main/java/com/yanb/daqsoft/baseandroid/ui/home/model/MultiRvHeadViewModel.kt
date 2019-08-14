package com.yanb.daqsoft.baseandroid.ui.home.model

import android.app.Application
import android.databinding.ObservableField
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingAction
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingCommand
import com.yanb.daqsoft.baselib.utils.ToastUtils

/**
 * 首页列表头布局viewmodel
 */
class MultiRvHeadViewModel :MultiItemViewModel<HomeFragmentModel>{
    val bannerImgList =  ObservableField<List<String>>()

    constructor(homeFragmentModel: HomeFragmentModel,list:List<String>):super(homeFragmentModel){
        this.bannerImgList.set(list)
    }
    // 条目点击事件
    var itemClick = BindingCommand<Any>(BindingAction {

    })
}