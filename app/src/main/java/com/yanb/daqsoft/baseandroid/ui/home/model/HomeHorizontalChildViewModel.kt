package com.yanb.daqsoft.baseandroid.ui.home.model

import android.databinding.ObservableField
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel
import com.yanb.daqsoft.baselib.mvvmbase.base.ItemViewModel

/**
 * 首页横向布局子模块recycleview viewModel
 */
class HomeHorizontalChildViewModel:ItemViewModel<HomeFragmentModel> {
    var nane = ObservableField<String>()
    constructor(homeFragmentModel: HomeFragmentModel,name_:String):super(homeFragmentModel){
        this.nane.set(name_)
    }
}