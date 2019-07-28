package com.yanb.daqsoft.baselib.mvvmbase

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yanb.daqsoft.baselib.R
import com.yanb.daqsoft.baselib.databinding.ActivityBaseMvvmBinding
import com.yanb.daqsoft.baselib.utils.ClassUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.reflect.ParameterizedType

/**
 * MVVM下的baseactivity基类
 * @author 严博
 * @date 2019-6-27.13:57
 * @version 1.0.0
 * @since JDK 1.8
 */
abstract class BaseMvvmActivity<DB : ViewDataBinding, VM : BaseViewModel<*>> : RxAppCompatActivity(),IBaseView {
    protected lateinit var mDataBinding: DB
        private set
    protected var mViewModel:VM? =null
    private var viewModelId: Int = 0
    /**
     * 寻找控件
     */
    protected fun <T : View> getView(id: Int): T {
        return findViewById<View>(id) as T
    }
    private val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    /**
     * 开始
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 页面接收的参数方法
        initParam()
        // 私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState)

    }

    /**
     * 注入绑定
     * 1、获取databinding类
     * 2、viewModelId
     * 3、mViewModel初始化
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        // DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        mDataBinding = DataBindingUtil.setContentView(this,initContentView(savedInstanceState))
        viewModelId = initVariableId()
        mViewModel = initViewModel()
        if (mViewModel == null) {
            val modelClass: Class<*>
            val type = javaClass.genericSuperclass
            if (type is ParameterizedType) {
                modelClass = type.actualTypeArguments[1] as Class<*>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel::class.java
            }
            mViewModel = createViewModel<ViewModel>(this, modelClass) as VM
        }
    }
    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProviders.of(activity).get(cls)
    }
    override fun initParam() {

    }
    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(savedInstanceState: Bundle?): Int
    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    fun initViewModel(): VM? {
        return null
    }
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

