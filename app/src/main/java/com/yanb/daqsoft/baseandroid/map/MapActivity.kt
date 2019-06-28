package com.yanb.daqsoft.baseandroid.map

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ActivityMapBinding
import com.yanb.daqsoft.baseandroid.map.entity.MapMarker

class MapActivity : AppCompatActivity() {
    private var bind:ActivityMapBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_map)
        var marker:MapMarker = MapMarker("哈哈哈","dfd","dfd")
        bind?.marker = marker
    }
}
