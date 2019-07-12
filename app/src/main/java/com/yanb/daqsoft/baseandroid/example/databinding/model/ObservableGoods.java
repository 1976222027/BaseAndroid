package com.yanb.daqsoft.baseandroid.example.databinding.model;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;

/**
 *
 * 继承于 Observable 类相对来说限制有点高，且也需要进行 notify 操作，
 * 因此为了简单起见可以选择使用 ObservableField。ObservableField
 * 可以理解为官方对 BaseObservable 中字段的注解和刷新等操作的封装，
 * 官方原生提供了对基本数据类型的封装，例如 ObservableBoolean、ObservableByte、
 * ObservableChar、ObservableShort、ObservableInt、ObservableLong、ObservableFloat、
 * ObservableDouble 以及 ObservableParcelable ，也可通过 ObservableField 泛型来申明其他类型
 *
 */
public class ObservableGoods {
    private ObservableField<String> name;
    private ObservableFloat price;
    public ObservableGoods(String name,float price_) {
        this.name = new ObservableField<>(name);
        this.price = new ObservableFloat(price_);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableFloat getPrice() {
        return price;
    }

    public void setPrice(ObservableFloat price) {
        this.price = price;
    }
}
