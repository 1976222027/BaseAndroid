package com.yanb.daqsoft.baseandroid.example.databinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ActivityDataBindingBinding
import com.yanb.daqsoft.baseandroid.example.databinding.model.Books
import com.yanb.daqsoft.baseandroid.example.databinding.model.Goods
import com.yanb.daqsoft.baseandroid.example.databinding.model.ObservableGoods
import com.yanb.daqsoft.baselib.utils.ToastUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * databingding学习界面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-6-27.11:17
 * @since JDK 1.8
 */
class DataBindingActivity : AppCompatActivity() ,View.OnClickListener{

    var mBooks:Books? = null
    var mObservableGoods:ObservableGoods? = null
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_star->
                ToastUtils.showCenterShort("带我飞")
        }
    }

    /**
     * ActivityDataBindingBinding这个类是根据布局文件名生成驼峰命名规则
     * 可自定义在data 节点添加class见布局
     * 控件的获取方式类似但首字母小写
     */
    private var dataBindData :ActivityDataBindingBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindData = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)

        //双向绑定
        mBooks = Books("语文啊","12","理科")
        dataBindData?.run {
            clickListener = this@DataBindingActivity
            books = mBooks
            // 单向绑定
            goods = Goods("烟波",23)

            // 绑定基础数据
            str = "我是基础数据直接得到"

            // 绑定高级变量如list
            val lists = ArrayList<String>()
            lists.add("我通过list得到list类型需要用到转义符")
            list = lists
            changeBooks = BooksHandler()
            // 通过ObservableField实现双向绑定
            mObservableGoods = ObservableGoods("严博",23f)
            obserGoods = mObservableGoods
        }
        bindUser()
    }



    /**
     * 绑定对象
     */
    private fun bindUser() {
        dataBindData?.data2 = com.yanb.daqsoft.baseandroid.example.DataBindData("我是通过实体类别名得到", 12)
        // 获取include布局ID
        //dataBindData?.includeTv?.tvIncludeName?.setText("我是通过include布局获得数据")
    }

    inner class BooksHandler {

        fun changeBooksName() {
            mBooks?.let {
                it.setName("我是名字${Random().nextInt(100)}")
                // 设置类型的时候未设置变化所以类型值不会联动改变
                it.setType("我是类型${Random().nextInt(100)}")
            }
        }

        fun changeBooksPrice() {
            mBooks?.let {
                it.setName("我是名字${Random().nextInt(100)}")
                // 设置类型的时候未设置变化所以类型值不会联动改变
                it.setType("我是类型${Random().nextInt(100)}")
                it.setPrice("我是价格${Random().nextInt(100)}")
            }
        }

        /**
         *
         * 改变Goods名称
         */
        fun changeGoodsName(){
            mObservableGoods?.let {
                it.name.set("改变名称${Random().nextInt(100)}")
            }
        }


    }

}
