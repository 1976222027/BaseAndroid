package com.yanb.daqsoft.baseandroid.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.app.Injection
import com.yanb.daqsoft.baseandroid.factory.AppViewModelFactory
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel
import com.yanb.daqsoft.baseandroid.ui.home.model.HomeFragmentModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseFragment
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

class HomeFragment :BaseFragment<com.yanb.daqsoft.baseandroid.databinding.FragmentHomeBinding, HomeFragmentModel>(){
    private lateinit var mTitle:String
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?,
                                 savedInstanceState: Bundle?): Int = R.layout.fragment_home

    override fun initVariableId(): Int =  BR.honeViewModel
    /**
     * 这里我要弄一个衍生对象
     */
    companion object{
        fun getInstance(title:String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun initViewModel(): HomeFragmentModel {
        val factory = AppViewModelFactory.getInstance(activity!!.application, Injection.provideAppRepository())
        return ViewModelProviders.of(this,factory).get(HomeFragmentModel::class.java)
    }
    override fun initData() {
        super.initData()
        binding.adapter = BindingRecyclerViewAdapter<Any>()
        viewModel.getScenicList()
    }

}