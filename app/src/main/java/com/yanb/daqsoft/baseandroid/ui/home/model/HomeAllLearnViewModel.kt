package com.yanb.daqsoft.baseandroid.ui.home.model

import android.databinding.ObservableArrayList
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.home.entity.LearnTag
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 大家都在学界面
 */
class HomeAllLearnViewModel :MultiItemViewModel<HomeFragmentModel>{
    /**
     * 给recycleview添加ObservableList
     */
    var observableList = ObservableArrayList<LearnTag>()
    val itemBinding: ItemBinding<LearnTag> = ItemBinding.of(BR.learnTag, R.layout.item_com_text_tag)
    constructor(homeFragmentModel: HomeFragmentModel,observableList: ObservableArrayList<LearnTag>):super(homeFragmentModel){
        this.observableList = observableList
    }
}