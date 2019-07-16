package com.yanb.daqsoft.baselib.mvvmbase

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yanb.daqsoft.baselib.R
import com.yanb.daqsoft.baselib.databinding.ActivityBaseMvvmBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * MVVM下的baseactivity基类
 * @author 严博
 * @date 2019-6-27.13:57
 * @version 1.0.0
 * @since JDK 1.8
 */
abstract class BaseMvvmActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mDataBinding: DB
        private set
    protected var mViewModel:VM? =null
    private val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    /**
     * lateinit延迟初始化
     */
    private lateinit var mActivityBaseMvvmBinding: ActivityBaseMvvmBinding
    /**
     * 开始
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mActivityBaseMvvmBinding = DataBindingUtil.setContentView(this,R.layout.activity_base_mvvm)
        mDataBinding = DataBindingUtil.inflate(layoutInflater,getLayoutResId(),mActivityBaseMvvmBinding.acBaseMvvmContent,true)
        initViewModel()
        bindViewModel()
        init()
        // ViewModel订阅生命周期事件
        mViewModel?.run {
            lifecycle.addObserver(this)
        }
    }

    // 初始化
    protected abstract fun init()
    //初始化viewModel
    protected abstract fun initViewModel()
    // 绑定viewModel
    protected abstract fun bindViewModel()
    // 获取布局ID
    protected abstract fun getLayoutResId():Int

    /**
     * 添加网络监听器
     */
    fun addSubscription(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }

    /**
     * 删除网络监听
     */
    fun removeDisposable(){
        if (!mCompositeDisposable.isDisposed){
            mCompositeDisposable.dispose()
        }
    }

    /**
     * 销毁
     */
    override fun onDestroy() {
        super.onDestroy()
        if (!mCompositeDisposable.isDisposed){
            mCompositeDisposable.clear()
        }
    }

}

