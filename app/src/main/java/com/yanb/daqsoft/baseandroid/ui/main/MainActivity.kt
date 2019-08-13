package com.yanb.daqsoft.baseandroid.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.daqsoft.tablayout.listener.CustomTabEntity
import com.daqsoft.tablayout.listener.OnTabSelectListener
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.common.ArrayTagConstants
import com.yanb.daqsoft.baseandroid.common.IconConstants
import com.yanb.daqsoft.baseandroid.common.ParamsConstants
import com.yanb.daqsoft.baseandroid.databinding.ActivityMainBinding
import com.yanb.daqsoft.baseandroid.ui.entity.TabEntity
import com.yanb.daqsoft.baseandroid.ui.home.HomeFragment
import com.yanb.daqsoft.baseandroid.ui.main.model.MainViewModel
import com.yanb.daqsoft.baseandroid.ui.me.MeFragment
import com.yanb.daqsoft.baseandroid.ui.study.MeStudyFragment
import com.yanb.daqsoft.baseandroid.ui.type.TypeFragment
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseActivity
import com.yanb.daqsoft.baselib.mvvmbase.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    /**
     * 下面是四个界面
     */
    var mHomeFragment: HomeFragment? = null
    var mTypeFragment: TypeFragment? = null
    var mMeStudyFragment: MeStudyFragment? = null
    var mMeFragment: MeFragment? = null
    /**
     * 默认首页
     */
    private var mIndex = 0
    /**
     * 底部筛选条目
     */
    private val mTabEntities = ArrayList<CustomTabEntity>()
    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    override fun initVariableId(): Int = BR.mainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt(ParamsConstants.PARAMS_SAVEINDEX)
        }
        super.onCreate(savedInstanceState)

    }

    override fun initData() {
        initTab()
        main_tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    /**
     * 初始化底部
     */
    private fun initTab() {
        (0 until ArrayTagConstants.mMainBottomTag.size)
                .mapTo(mTabEntities) {
                    TabEntity(ArrayTagConstants.mMainBottomTag[it], IconConstants.homeIconSelectIds[it], IconConstants.homeIconUnselectIds[it])
                }
        main_tab_layout.setTabData(mTabEntities)
        main_tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    /**
     * 筛选切换fragment
     */
    private fun switchFragment(postion: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragment(transaction)
        when (postion) {
            0 ->// 首页
                mHomeFragment?.let {
                    transaction.show(it)
                } ?: HomeFragment.getInstance(ArrayTagConstants.mMainBottomTag[postion]).let {
                    mHomeFragment = it
                    transaction.add(R.id.main_fl_container, it, "home")
                }
            1 -> // 分类
                mTypeFragment?.let {
                    transaction.show(it)
                } ?: TypeFragment.getInstance(ArrayTagConstants.mMainBottomTag[postion]).let {
                    mTypeFragment = it
                    transaction.add(R.id.main_fl_container, it, "type")
                }
            2 -> // 我的学习
                mMeStudyFragment?.let {
                    transaction.show(it)
                } ?: MeStudyFragment.getInstance(ArrayTagConstants.mMainBottomTag[postion]).let {
                    mMeStudyFragment = it
                    transaction.add(R.id.main_fl_container, it, "me")
                }
            3 -> // 个人中心
                mMeFragment?.let {
                    transaction.show(it)
                } ?: MeFragment.getInstance(ArrayTagConstants.mMainBottomTag[postion]).let {
                    mMeFragment = it
                    transaction.add(R.id.main_fl_container, it, "me")
                }
            else -> {

            }
        }
        mIndex = postion
        main_tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }
    private fun hideFragment(transaction: FragmentTransaction){
        mHomeFragment?.let { transaction.hide(it) }
        mTypeFragment?.let { transaction.hide(it) }
        mMeStudyFragment?.let { transaction.hide(it) }
        mMeFragment?.let { transaction.hide(it) }
    }
    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle?) {
        // 记录fragment的位置，防止崩溃 activity被系统回收时fragment错乱
        if (main_tab_layout!=null){
            outState?.putInt(ParamsConstants.PARAMS_SAVEINDEX,mIndex)
        }
    }
    /**
     * 下面是常用的点击两次退出界面
     */
    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                ToastUtils.showLong("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
