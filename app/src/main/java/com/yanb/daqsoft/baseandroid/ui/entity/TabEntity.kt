package com.yanb.daqsoft.baseandroid.ui.entity

import com.daqsoft.tablayout.listener.CustomTabEntity

class TabEntity(private var title:String,private var selectedIcon:Int,private var unSelectedIcon:Int):CustomTabEntity {
    override fun getTabTitle(): String = title

    override fun getTabSelectedIcon(): Int  = selectedIcon

    override fun getTabUnselectedIcon(): Int  = unSelectedIcon
}