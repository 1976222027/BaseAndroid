package com.yanb.daqsoft.baseandroid.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.me.model.MeFragmentModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseFragment

class MeFragment :BaseFragment<com.yanb.daqsoft.baseandroid.databinding.FragmentHomeBinding, MeFragmentModel>(){
    private lateinit var mTitle:String
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int = R.layout.fragment_me

    override fun initVariableId(): Int =  BR.honeViewModel
    /**
     * 这里我要弄一个衍生对象
     */
    companion object{
        fun getInstance(title:String): MeFragment {
            val fragment = MeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}