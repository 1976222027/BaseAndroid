package com.yanb.daqsoft.baseandroid.ktapp.fragment

import android.os.Bundle
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ktapp.mvp.contract.KtActualCombatContract
import com.yanb.daqsoft.baseandroid.ktapp.mvp.contract.KtGrammarContract
import com.yanb.daqsoft.baselib.ktbase.BaseKtFragment

/**
 * Kt实战界面
 * @author 严博
 * @date 2019-6-10.15:20
 * @version 1.0.0
 * @since JDK 1.8
 */
class KtActualCombatFragemnt :BaseKtFragment(), KtActualCombatContract.View{

    private var mTitle: String? = null
    companion object {
        fun getInstance(title: String): KtActualCombatFragemnt {
            val fragment = KtActualCombatFragemnt()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun initView() {

    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.kt_fragment_actualcombat
}