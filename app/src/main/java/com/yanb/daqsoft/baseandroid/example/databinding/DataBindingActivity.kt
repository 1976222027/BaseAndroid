package com.yanb.daqsoft.baseandroid.example.databinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ActivityDataBindingBinding
import com.yanb.daqsoft.baseandroid.example.databinding.model.Goods

import com.yanb.daqsoft.baselib.utils.ToastUtils

/**
 * databingding学习界面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-6-27.11:17
 * @since JDK 1.8
 * 基本使用API
 * android:visibility="@{user.isAdult ? View.VISIBLE : View.GONE}"//支持三元运算符。需要导入View包
 * android:text="@{String.valueOf(user.age)}"//@{只能是String}
 * android:text="@{StringUtils.capitalize(user.firstName)}"//前面导入了这个包，可以调用这个静态方法
 * android:text="@{user.displayName ?? user.lastName}"//它表达的是如果左边不是 null 的，那么使用左边的值，否者使用右边的值
 * 注意：
 * 1.在xml布局中java基础包不需要导入
 * 2、可声明基础变量直接使用：
 *     <data>
            <variable
                name="str"
                type="String"/>
            // 使用这个的时候一定要进行转换为String类型
            <variable
                name="age"
                type="int" />
        </data>
 *
 *
 *
 */
class DataBindingActivity : AppCompatActivity() ,View.OnClickListener{
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
        dataBindData?.clickListener = this
        bindUser()
        bindList()
        bindBaseData()
        // 单向数据绑定
        bindOnly()
    }

    /**
     * 单向数据绑定
     */
    private fun bindOnly() {
        dataBindData?.goods = Goods("烟波",23)
    }

    /**
     * 绑定基础数据
     */
    private fun bindBaseData() {
        dataBindData?.str = "我是基础数据直接得到"
    }

    /**
     * 绑定高级变量如list
     */
    private fun bindList() {
        val list = ArrayList<String>()
        list.add("我通过list得到list类型需要用到转义符")
        dataBindData?.list = list
    }

    /**
     * 绑定对象
     */
    private fun bindUser() {
        dataBindData?.data2 = com.yanb.daqsoft.baseandroid.example.DataBindData("我是通过实体类别名得到", 12)
        // 获取include布局ID
        //dataBindData?.includeTv?.tvIncludeName?.setText("我是通过include布局获得数据")
    }
}
