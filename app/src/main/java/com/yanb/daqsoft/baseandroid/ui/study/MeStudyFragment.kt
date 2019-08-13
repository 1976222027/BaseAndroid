package com.yanb.daqsoft.baseandroid.ui.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.study.model.MeStudyFragmentModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseFragment

/**
 * 分类
 */
class MeStudyFragment :BaseFragment<com.yanb.daqsoft.baseandroid.databinding.FragmentMeStudyBinding, MeStudyFragmentModel>(){
    private lateinit var mTitle:String
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int = R.layout.fragment_me_study

    override fun initVariableId(): Int =  BR.studyViewModel
    /**
     * 这里我要弄一个衍生对象
     */
    companion object{
        fun getInstance(title:String): MeStudyFragment {
            val fragment = MeStudyFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}