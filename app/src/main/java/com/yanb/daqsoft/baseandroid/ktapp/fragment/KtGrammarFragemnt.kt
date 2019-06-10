package com.yanb.daqsoft.baseandroid.ktapp.fragment

import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ktapp.mvp.contract.KtGrammarContract
import com.yanb.daqsoft.baselib.ktbase.BaseKtFragment

/**
 * Kt语法界面
 * @author 严博
 * @date 2019-6-10.15:20
 * @version 1.0.0
 * @since JDK 1.8
 */
class KtGrammarFragemnt :BaseKtFragment(),KtGrammarContract.View{
    private val mPresenter by lazy {  }
    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun initView() {

    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.kt_fragment_grammar
}