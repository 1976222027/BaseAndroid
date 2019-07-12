package com.yanb.daqsoft.baseandroid.example.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.yanb.daqsoft.baseandroid.BR;


/**
 * 这里我先用java来写双向绑定的数据源
 * kt好像不能写，待研究
 */
public class Books extends BaseObservable {
    /**
     * 如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
     */
    @Bindable
    public String name;
    /**
     * 如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
     */
    private String price;

    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Books(String name, String price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //只更新本字段
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        // 更新所有字段
        notifyChange();
    }
}
