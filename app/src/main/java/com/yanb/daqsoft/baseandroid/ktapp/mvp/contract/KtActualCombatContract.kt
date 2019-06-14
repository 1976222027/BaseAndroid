package com.yanb.daqsoft.baseandroid.ktapp.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter

/**
 * kt实战Contract
 * @author 严博
 * @date 2019-6-10.15:22
 * @version 1.0.0
 * @since JDK 1.8
 */
interface KtActualCombatContract {
    /**
     * View层
     */
    interface View : IBaseView{

    }

    /**
     * P层
     */
    interface Presenter:IPresenter<View>{

    }

}