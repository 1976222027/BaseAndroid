package com.yanb.daqsoft.baseandroid.ktapp.mvp.presenter

import com.yanb.daqsoft.baseandroid.ktapp.mvp.contract.KtGrammarContract
import com.yanb.daqsoft.baseandroid.ktapp.mvp.model.KtGrammarModel
import com.yanb.daqsoft.baselib.ktbase.BaseKtPresenter

/**
 * 语法的P层
 * @author 严博
 * @date 2019-6-10.15:53
 * @version 1.0.0
 * @since JDK 1.8
 */
class KtGrammarPresenter :BaseKtPresenter<KtGrammarContract.View>(),KtGrammarContract.Presenter{
    private val homeModel: KtGrammarModel by lazy { KtGrammarModel() }
    override fun getHomeData(num: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = homeModel.getHomeData(num)
                .flatMap { homeBean->
                    val bannerItemList = homeBean.issueList[0].itemList
                    bannerItemList.filter { it -> it.type=="banner2"||it.type== "horizontalScrollCard"
                    }.forEach { it->
                                bannerItemList.remove(it)
                    }

                }


    }

    override fun loadMoreData() {
    }

}