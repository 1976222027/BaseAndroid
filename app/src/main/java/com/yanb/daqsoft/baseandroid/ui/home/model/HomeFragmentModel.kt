package com.yanb.daqsoft.baseandroid.ui.home.model

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

class HomeFragmentModel : BaseViewModel<AppRepositoryModel> {
    /**
     * 布局类型
     */
    private val ITEMTYPE_HEAD = "head"
    private val ITEMTYPE_LEFT = "left"
    private val ITEMTYPE_RIGHT = "right"
    /**
     * 给RecycleView添加ObservableList
     */
    //给RecyclerView添加ObservableList
    var observableList: ObservableList<MultiItemViewModel<*>> = ObservableArrayList<MultiItemViewModel<*>>()

    /**
     * Recycleview多布局添加ItemBinding
     */
    val itemBinding = ItemBinding.of(OnItemBind<MultiItemViewModel<*>> { itemBinding, position, item ->
        // 通过item的类型动态设置item加载的布局
        val itemType = item.itemType
        if (ITEMTYPE_HEAD==itemType){
            //设置头布局
            itemBinding.set(BR.homeBannerViewModel, R.layout.item_home_head)
        }
    })

    constructor(application: Application) : super(application) {
        val list = listOf<String>("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg","http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg")
        val itemHead = MultiRvHeadViewModel(this,list)
        itemHead.multiItemType(ITEMTYPE_HEAD)
        observableList.add(itemHead)
    }
}