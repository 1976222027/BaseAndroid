package com.yanb.daqsoft.baseandroid.ktapp.fragment

import android.os.Bundle
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ktapp.mvp.contract.KtGrammarContract
import com.yanb.daqsoft.baseandroid.ktapp.mvp.presenter.KtGrammarPresenter
import com.yanb.daqsoft.baselib.ktbase.BaseKtFragment
import kotlinx.android.synthetic.main.kt_fragment_grammar.*

/**
 * Kt语法界面
 * @author 严博
 * @date 2019-6-10.15:20
 * @version 1.0.0
 * @since JDK 1.8
 */
class KtGrammarFragemnt :BaseKtFragment(),KtGrammarContract.View{
    private val mPrestener by lazy { KtGrammarPresenter() }
    private var mTitle: String? = null
    private var isRefresh = false
    companion object {
        fun getInstance(title: String): KtGrammarFragemnt {
            val fragment = KtGrammarFragemnt()
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
        mPrestener.attachView(this)
        mRefreshlayout.setOnRefreshListener {
            isRefresh = true
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.kt_fragment_grammar
}