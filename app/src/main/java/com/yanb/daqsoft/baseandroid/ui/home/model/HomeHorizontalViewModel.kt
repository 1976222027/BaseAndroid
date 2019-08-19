package com.yanb.daqsoft.baseandroid.ui.home.model

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.home.entity.ScenicEntity
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 首页横向布局
 */
class HomeHorizontalViewModel : MultiItemViewModel<HomeFragmentModel> {
    val childAdapter  = BindingRecyclerViewAdapter<Any>()
    /**
     * 给recycleview添加ObservableList
     */
    var observableList = ObservableArrayList<HomeHorizontalChildViewModel>()
    val itemBinding:ItemBinding<HomeHorizontalChildViewModel> = ItemBinding.of(BR.homehchildViewModel, R.layout.item_home_horizontal_child)
    constructor(homeFragmentModel: HomeFragmentModel):super(homeFragmentModel){
        this.observableList = homeFragmentModel.getScenicItem()
    }
}

