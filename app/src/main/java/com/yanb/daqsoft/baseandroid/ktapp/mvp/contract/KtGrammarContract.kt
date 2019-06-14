package com.yanb.daqsoft.baseandroid.ktapp.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter

/**
 * kt语法Contract
 * @author 严博
 * @date 2019-6-10.15:22
 * @version 1.0.0
 * @since JDK 1.8
 */
interface KtGrammarContract {
    /**
     * View层
     */
    interface View : IBaseView{

    }

    /**
     * P层
     */
    interface Presenter:IPresenter<View>{
        /**
         * 获取首页数据
         */
        fun getHomeData(num:Int)
        /**
         * 加载跟多数据
         */
        fun loadMoreData()
    }

}