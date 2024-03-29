package com.yanb.daqsoft.baseandroid.ui.home.model

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ItemHomeHorizontalBinding
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel
import com.yanb.daqsoft.baseandroid.ui.home.entity.LearnTag
import com.yanb.daqsoft.baseandroid.ui.home.entity.ScenicEntity
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import com.yanb.daqsoft.baselib.mvvmbase.http.observe.DefaultObserver
import com.yanb.daqsoft.baselib.mvvmbase.http.scheduler.SchedulerUtils
import com.yanb.daqsoft.baselib.utils.KLog
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

open class HomeFragmentModel : BaseViewModel<AppRepositoryModel> {
    /**
     * 布局类型
     */
    private val ITEMTYPE_HEAD = "head"
    // 通知
    private val ITEMTYPE_NOTICE = "notice"
    // 横向的菜单
    private val ITEMTYPE_MENU = "menu"
    // 中间跟多
    private val ITEMTYPE_MENU_MORE = "menumore"
    // 横向菜单
    private val ITEMTYPE_HORIZONTAL = "horizonta"
    // 实战推荐
    private val ITEMTYPE_ACTUALCOMBAT = "actualcombat"
    // 都在学模块
    private val ITEMTYPE_ALL_LEARN = "alllearn"
    /**
     * 给RecycleView添加ObservableList
     */
    var observableList: ObservableList<MultiItemViewModel<*>> = ObservableArrayList<MultiItemViewModel<*>>()
    // 横项的数据源
    var observableChildList:ObservableArrayList<ScenicEntity> = ObservableArrayList()
    // 都在学列表
    var observableLearnList:ObservableArrayList<LearnTag> = ObservableArrayList()
    /**
     * Recycleview多布局添加ItemBinding
     */
    val itemBinding = ItemBinding.of(OnItemBind<MultiItemViewModel<*>> { itemBinding, position, item ->
        // 通过item的类型动态设置item加载的布局
        val itemType = item.itemType
        if (ITEMTYPE_HEAD == itemType) {
            //设置头布局
            itemBinding.set(BR.homeBannerViewModel, R.layout.item_home_head)
        } else if (ITEMTYPE_NOTICE == itemType) {
            itemBinding.set(BR.noticeViewModel, R.layout.item_home_notice)
        } else if (ITEMTYPE_MENU == itemType) {
            itemBinding.set(BR.menuViewModel, R.layout.item_home_menu)
        } else if (ITEMTYPE_MENU_MORE == itemType) {
            itemBinding.set(BR.moreViewModel, R.layout.item_home_menu_more)
        }else if (ITEMTYPE_HORIZONTAL == itemType){
            itemBinding.set(BR.horizontalViewModel2, R.layout.item_home_horizontal)
            // 实战推荐
        }else if (ITEMTYPE_ACTUALCOMBAT == itemType){
            itemBinding.set(BR.accomViewmodel, R.layout.item_home_actualcombat)
        }else if (ITEMTYPE_ALL_LEARN == itemType){
            itemBinding.set(BR.learnViewModel, R.layout.item_home_all_learn)
        }
    })

    constructor(application: Application,appRepositoryModel: AppRepositoryModel) : super(application,appRepositoryModel) {
        // 添加banner
        val list = listOf<String>("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg", "http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg")
        val itemHead = HomeBannerViewModel(this, list)
        itemHead.multiItemType(ITEMTYPE_HEAD)
        observableList.add(itemHead)

        val itemNotice = HomeNoticeViewModel(this)
        itemNotice.multiItemType(ITEMTYPE_NOTICE)
        observableList.add(itemNotice)

        val itemMenu = HomeMenuViewModel(this)
        itemMenu.multiItemType(ITEMTYPE_MENU)
        observableList.add(itemMenu)

        val itemMenuMore = HomeMenuMoreViewModel(this)
        itemMenuMore.multiItemType(ITEMTYPE_MENU_MORE)
        observableList.add(itemMenuMore)


    }

    fun getScenicList(){
        // 这里部分参数写死只是为演示lng=104.071747&page=1&limitPage=10&lat=30.53779&siteCode=nngjapp&lang=cn&token=
        model.getScenicList("104.071747","30.53779","1","5")
                .doOnSubscribe {
                    addSubscribe(it)
                }
                .compose(SchedulerUtils.ioToMain())
                .subscribe(object : DefaultObserver<BaseResponse<List<ScenicEntity>>>(){
                    override fun onSuccess(response: BaseResponse<List<ScenicEntity>>?) {
                        KLog.e("你请求的景区数据-》${response?.datas?.get(0)?.name}")
                        response?.datas?.forEach {
                            observableChildList.add(it)
                        }
                        val itemHore = HomeHorizontalViewModel(this@HomeFragmentModel,observableChildList)
                        itemHore.multiItemType(ITEMTYPE_HORIZONTAL)
                        observableList.add(itemHore)


                        // 这里添加实战 列表
                        val itemAccom = HomeActualCombatViewModel(this@HomeFragmentModel,observableChildList)
                        itemAccom.multiItemType(ITEMTYPE_ACTUALCOMBAT)
                        observableList.add(itemAccom)

                        // 添加都在学列表
                        val learnTag  = listOf<String>("java","kotlin","rubuy","java","kotlin","rubuy","java","换一批")
                        learnTag.forEach {
                            var learn :LearnTag?  = null
                            if (it == "换一批"){
                                 learn = LearnTag(it,true)
                            }else{
                                 learn = LearnTag(it,false)
                            }
                            observableLearnList.add(learn)
                        }
                        val itemLearn = HomeAllLearnViewModel(this@HomeFragmentModel,observableLearnList)
                        itemLearn.multiItemType(ITEMTYPE_ALL_LEARN)
                        observableList.add(itemLearn)

                    }

                    override fun onFail(message: String?) {
                        KLog.e("错误--》${message}")
                    }

                })
    }

}