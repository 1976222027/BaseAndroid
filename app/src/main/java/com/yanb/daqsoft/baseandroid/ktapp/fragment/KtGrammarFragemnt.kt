package com.yanb.daqsoft.baseandroid.ktapp.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hazz.kotlinmvp.net.exception.ErrorStatus
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ktapp.adapter.GrammarHomeAdapter
import com.yanb.daqsoft.baseandroid.ktapp.mvp.contract.KtGrammarContract
import com.yanb.daqsoft.baseandroid.ktapp.mvp.model.bean.HomeBean
import com.yanb.daqsoft.baseandroid.ktapp.mvp.presenter.KtGrammarPresenter
import com.yanb.daqsoft.baselib.ktbase.BaseKtFragment
import com.yanb.daqsoft.baselib.mvvmbase.http.exception.ErrorStatus
import com.yanb.daqsoft.baselib.utils.ToastUtils
import kotlinx.android.synthetic.main.kt_fragment_grammar.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Kt语法界面
 * @author 严博
 * @date 2019-6-10.15:20
 * @version 1.0.0
 * @since JDK 1.8
 */
class KtGrammarFragemnt : BaseKtFragment(), KtGrammarContract.View {
    /**
     * 初始化P层
     * by lazy只有引用到这个变量的时候方法体里面才会调用
     */
    private val mPrestener by lazy { KtGrammarPresenter() }
    private val simpleDateFormat by lazy {
        SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH)
    }
    private var mTitle: String? = null
    private var num: Int = 1
    /**
     * 适配器相关
     */
    private var mHomeAdapter: GrammarHomeAdapter? = null
    /**
     * 加载更多
     */
    private var loadingMore = false
    //刷新
    private var isRefresh = false
    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    /**
     * 伴生对象，类名直接访问其中方法
     */
    companion object {
        fun getInstance(title: String): KtGrammarFragemnt {
            val fragment = KtGrammarFragemnt()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    /**
     * 初始化view
     */
    override fun initView() {
        mPrestener.attachView(this)
        // 设置刷新头的颜色
        mRefreshlayout.run {
            setPrimaryColorsId(android.R.color.holo_green_light, android.R.color.holo_green_dark, android.R.color.white)
            setOnRefreshListener {
                isRefresh = true
                mPrestener.getHomeData(num)
            }
            setEnableHeaderTranslationContent(true)
        }
        /**
         * 监听滚动
         */
        mRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            /**
             * 滚动状态改变监听
             */
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = mRecycleView.childCount
                    val itemCount = mRecycleView.layoutManager.itemCount
                    val firstVisibleItem = (mRecycleView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true
                            mPrestener.loadMoreData()
                        }
                    }
                }
            }

            /**
             * 滚动监听
             */
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (currentVisibleItemPosition==0){
                    toolbar.setBackgroundColor(resources.getColor(R.color.transparent))
                    iv_search.visibility = View.GONE
                    tv_header_title.text = ""
                }else{
                    if (mHomeAdapter?.mData!!.size > 1) {
                        toolbar.setBackgroundColor(resources.getColor(R.color.light_transparent))
                        iv_search.setImageResource(R.drawable.comm_titlebar_search_normal)
                        val itemList = mHomeAdapter!!.mData
                        val item = itemList[currentVisibleItemPosition + mHomeAdapter!!.bannerIteamSize - 1]
                        if (item.type == "textHeader") {
                            tv_header_title.text = item.data?.text
                        } else {
                            tv_header_title.text = simpleDateFormat.format(item.data?.date)
                        }
                    }
                }
            }
        })
        iv_search.setOnClickListener { openSearActivity() }
        mLayoutStatusView = multipleStatusView
    }

    /**
     * 打开搜索界面
     */
    private fun openSearActivity() {

    }

    override fun lazyLoad() {
        mPrestener.getHomeData(num)
    }

    override fun getLayoutId(): Int = R.layout.kt_fragment_grammar

    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()
        mHomeAdapter = activity?.let { GrammarHomeAdapter(it, homeBean.issueList[0].itemList) }
        //设置 banner 大小
        mHomeAdapter?.setBannerSize(homeBean.issueList[0].count)
        mRecycleView.adapter = mHomeAdapter
        mRecycleView.layoutManager = linearLayoutManager
        mRecycleView.itemAnimator = DefaultItemAnimator()
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
        loadingMore = false
        mHomeAdapter?.addItemData(itemList)
    }

    override fun showError(msg: String, errorCode: Int) {
        ToastUtils.showCenterShort(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun dismissLoading() {
        mRefreshlayout.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPrestener.detachView()
    }
}