package com.yanb.daqsoft.baseandroid.login.model

import android.app.Application
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.os.Bundle
import android.text.TextUtils
import android.view.View

import com.yanb.daqsoft.baseandroid.common.AESEncryptUtil
import com.yanb.daqsoft.baseandroid.http.XhttpUtils
import com.yanb.daqsoft.baseandroid.login.User
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingAction
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingCommand
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingConsumer
import com.yanb.daqsoft.baselib.mvvmbase.bus.event.SingleLiveEvent
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import com.yanb.daqsoft.baselib.mvvmbase.http.observe.DefaultObserver
import com.yanb.daqsoft.baselib.mvvmbase.utils.KLog
import com.yanb.daqsoft.baselib.mvvmbase.utils.RxUtils
import com.yanb.daqsoft.baselib.utils.ToastUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class LoginViewModel:BaseViewModel<AppRepositoryModel> {

    constructor(application: Application,appRepositoryModel: AppRepositoryModel) :super(application,appRepositoryModel){
        //重本地拿到数据绑定到View层
        userName.set(model.getUserName())
        psd.set(model.getPsd())
    }

    /**
     * 登录点击事件
     */
    val login = BindingCommand<Any>(BindingAction {
        logins()
    })

    /**
     * 登录
     */
    fun logins(){
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showLong("请输入账号")
            return
        }
        if (TextUtils.isEmpty(psd.get())) {
            ToastUtils.showLong("请输入密码！")
            return
        }
        val pasdNew = AESEncryptUtil.Encrypt(psd.get())
        model.login("1",userName.get()!!,pasdNew)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<BaseResponse<User>>() {
                    override fun onSuccess(response: BaseResponse<User>?) {
                        KLog.e("----------->"+response?.data?.head)
                    }

                    override fun onFail(message: String?) {
                        KLog.e("----------->"+message)
                    }

                })
        model.saveUserName(userName.get()!!)
        model.savePsd(psd.get()!!)
    }
    // 封装一个界面改变的观察者
    val uc = UIChangeObservable()
    inner class UIChangeObservable{
        val psdSwitchEvent = SingleLiveEvent<Boolean>()
    }
    // 密码明文显示  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    val psdTransformationMethod = BindingCommand<Any>(BindingAction {
        //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
        uc.psdSwitchEvent.value = uc.psdSwitchEvent.getValue() == null || !uc.psdSwitchEvent.getValue()!!
    })
    // 密码
    val psd = ObservableField<String>("")
    /**
     * 密码相关
     */
    val psdEtChange = BindingCommand(BindingConsumer<String> { t->
        psdClearImaVisibility.set(if (t.isNullOrEmpty())View.GONE else View.VISIBLE)
        psd.set(t)
    })
    val psdClearImaVisibility = ObservableInt(View.GONE)
    val clearPsd = BindingCommand<Any>(BindingAction {
        psd.set("")
    })

    /**
     * 用户名清除按钮监听
     * 设置默认值就是隐藏
     */
    val userClearImgVisibility = ObservableInt(View.GONE)
    // 用户名
    var userName = ObservableField<String>("")
    // 监听用户名编辑框输入情况
    val userEtChange = BindingCommand(BindingConsumer<String> { t ->
        userClearImgVisibility.set(if (t.isNullOrEmpty())View.GONE else View.VISIBLE)
        userName.set(t)
    })
    // 清除用户名
    val clearUsername = BindingCommand<Any>(BindingAction { userName.set("") })



    override fun onDestroy() {
        super.onDestroy()
    }
}
