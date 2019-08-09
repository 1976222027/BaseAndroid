package com.yanb.daqsoft.baselib.view

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * 矢量图标用法详见博客
 * https://www.jianshu.com/p/971b01c175a6
 */
class IconView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        /**
         * 首先在assets文件夹中添加图标文件
         */
        this.typeface = Typeface.createFromAsset(context.assets, "icon/iconfont.ttf")
    }

}
