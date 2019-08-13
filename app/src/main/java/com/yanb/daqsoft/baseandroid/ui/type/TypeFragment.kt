package com.yanb.daqsoft.baseandroid.ui.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.type.model.TypeFragmentModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseFragment

/**
 * 分类
 */
class TypeFragment :BaseFragment<com.yanb.daqsoft.baseandroid.databinding.FragmentTypeBinding, TypeFragmentModel>(){
    private lateinit var mTitle:String
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int = R.layout.fragment_type

    override fun initVariableId(): Int =  BR.typeViewmodel
    /**
     * 这里我要弄一个衍生对象
     */
    companion object{
        fun getInstance(title:String): TypeFragment {
            val fragment = TypeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}