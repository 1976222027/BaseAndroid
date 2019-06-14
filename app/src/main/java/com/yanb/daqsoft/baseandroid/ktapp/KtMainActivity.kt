package com.yanb.daqsoft.baseandroid.ktapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.daqsoft.tablayout.listener.CustomTabEntity
import com.daqsoft.tablayout.listener.OnTabSelectListener
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.entity.TabEntity
import com.yanb.daqsoft.baseandroid.ktapp.fragment.KtActualCombatFragemnt
import com.yanb.daqsoft.baseandroid.ktapp.fragment.KtGrammarFragemnt
import com.yanb.daqsoft.baselib.ktbase.BaseActivity
import com.yanb.daqsoft.baselib.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_kt_main.*

/**
 * kotlinApp主界面
 * 本项目采用kotlin编写可以根据项目逐步学习kt在android中的运用，并且采用mvp模式
 */
class KtMainActivity : BaseActivity() {

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
     * 这里涉及到空？的用法
     * 下面的意思是mKtGrammarFragemnt可空，加了？才可赋值null
     * 在使用声明了可空的对象后都要用对象?.公共方法来操作
     * mKtGrammarFragemnt?.** 当mKtGrammarFragemnt为null时输出null
     * 或者
     * mKtGrammarFragemnt!!.公共方法,当mKtGrammarFragemnt为null是报空指针异常
     */
    private var mKtGrammarFragemnt: KtGrammarFragemnt? = null
    private var mKtActualCombatFragemnt: KtActualCombatFragemnt? = null
    /**
     * 当前选中标识
     */
    private var mIndex = 0

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        initTab()
        kt_com_tablayout.currentTab = mIndex
        switchFragment(mIndex)
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
        (0 until mTitle.size)
                .mapTo(mTabEntities) { TabEntity(mTitle[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        kt_com_tablayout.setTabData(mTabEntities)
        kt_com_tablayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                // 切换fragment
                switchFragment(position)
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
    private fun switchFragment(postion: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        /**
         * 介绍条件语句when使用
         */
        when(postion){
            0 // 语法页面
            -> mKtGrammarFragemnt?.let {
                transaction.show(it)
                // ?:意思是当前面对象为空时才执行下一步操作
            }?:KtGrammarFragemnt.getInstance(mTitle[postion]).let {
                mKtGrammarFragemnt = it
                transaction.add(R.id.kt_fl_tab_container,it,"grammar")
            }
            1//实战界面
            ->mKtActualCombatFragemnt?.let {
                transaction.show(it)
            }?:KtActualCombatFragemnt.getInstance(mTitle[postion]).let {
                mKtActualCombatFragemnt = it
                transaction.add(R.id.kt_fl_tab_container,it,"actualcombat")
            }
            else->{

            }
        }
        mIndex = postion
        kt_com_tablayout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * 这里介绍let的用法
     * 1、判空
     * 使用let函数处理需要针对一个可null的对象统一做判空处理。
     * object?.let{ }object不为空的情况下才执行大括弧中的方法，大括弧中用it代替object对象访问公有的属性和方法
     *
     * 2、作用域
     * { }
     * 注意：
     * object?.let{ }是有返回值的，返回值为函数块的最后一行或指定return表达式。
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mKtGrammarFragemnt?.let { transaction.hide(it) }
        mKtActualCombatFragemnt?.let { transaction.hide(it) }
    }
    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        // 记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (kt_com_tablayout!=null){
            outState.putInt("currTabIndex", mIndex)
        }
    }
    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis().minus(mExitTime)<=2000){
                finish()
            }else{
                mExitTime = System.currentTimeMillis()
                ToastUtils.showCenterShort("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
