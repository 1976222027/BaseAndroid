package com.yanb.daqsoft.baseandroid.ui.home.model

import android.databinding.ObservableArrayList
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.home.entity.ScenicEntity
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 实战推荐viewmodel
 */
class HomeActualCombatViewModel :MultiItemViewModel<HomeFragmentModel> {
    /**
     * 给recycleview添加ObservableList
     */
    var observableList = ObservableArrayList<ScenicEntity>()
    val itemBinding: ItemBinding<ScenicEntity> = ItemBinding.of(BR.accomAc, R.layout.item_home_actual_combat_child)
    constructor(homeFragmentModel: HomeFragmentModel,observableList: ObservableArrayList<ScenicEntity>):super(homeFragmentModel){
        this.observableList = observableList
    }
}