package com.yanb.daqsoft.baseandroid.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.app.Injection
import com.yanb.daqsoft.baseandroid.databinding.ActivityLoginNewBinding
import com.yanb.daqsoft.baseandroid.factory.AppViewModelFactory
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel
import com.yanb.daqsoft.baseandroid.utils.HelpUtils
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseActivity

class LoginNewActivity : BaseActivity<ActivityLoginNewBinding,LoginViewModel>() {

    /**
     * ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
     */
    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_login_new

    override fun initVariableId(): Int = BR.loginViewModel
    override fun initViewModel(): LoginViewModel {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        val factory = AppViewModelFactory.getInstance(application,Injection.provideAppRepository())
        return ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
    }

    override fun initViewObservable() {
        // 监听viewModele 中密码明文显示的观察者，当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uc.psdSwitchEvent.observe(this, Observer<Boolean> {
            //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
            if (viewModel.uc.psdSwitchEvent.value!!){
                //密码可见
                //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                binding.ivShowPwd.setImageResource(R.mipmap.common_icon_eye_open)
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }else{
                //密码不可见
                binding.ivShowPwd.setImageResource(R.mipmap.common_icon_eye_close)
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        })
    }


}
