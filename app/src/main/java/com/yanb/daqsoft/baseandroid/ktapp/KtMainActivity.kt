package com.yanb.daqsoft.baseandroid.ktapp

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.daqsoft.tablayout.listener.CustomTabEntity
import com.daqsoft.tablayout.listener.OnTabSelectListener
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.entity.TabEntity
import com.yanb.daqsoft.baselib.ktbase.BaseActivity
import kotlinx.android.synthetic.main.activity_kt_main.*

/**
 * kotlinApp主界面
 * 本项目采用kotlin编写可以根据项目逐步学习kt在android中的运用，并且采用mvp模式
 */
class KtMainActivity : BaseActivity() {

    /**
     * 数组
     * Array
     */
    // 标题
    private val mTitle = arrayOf("语法", "实例")
    /**
     * intArrayOf之外还有ByteArray, ShortArray, IntArray，用来表示各个类型的数组
     * 省去了装箱操作，因此效率更高，其用法同Array一样
     */
    private val mIconUnSelectIds = intArrayOf(R.drawable.home_tab_home_normal, R.drawable.home_tab_find_normal)// 未选中图标
    private val mIconSelectIds = intArrayOf(R.drawable.home_tab_home_selected, R.drawable.home_tab_find_selected)//选中图标
    private val mTabEntities = ArrayList<CustomTabEntity>()
    /**
     * 当前选中标识
     */
    private var mIndex = 0

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        initTab()

    }

    /**
     * 初始化Tab
     */
    private fun initTab() {
        /**
         * 给每个标题赋值
         * until区间
         * a until b 从a到b范围内所有的值，包括a和不包括b
         */
        (0 until mTabEntities.size)
                .mapTo(mTabEntities) { TabEntity(mTitle[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        com_tablayout.setTabData(mTabEntities)
        com_tablayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {

            }

            override fun onTabReselect(position: Int) {

            }
        })

    }

    override fun start() {

    }


    override fun initData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_kt_main
    }

    /**
     * 切换
     */
    private fun switchFragment(postion:Int){
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)

    }

    /**
     * 隐藏所有的Fragment
     *
     */
    private fun hideFragments(transaction: FragmentTransaction?) {

    }
}
