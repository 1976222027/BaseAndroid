package com.yanb.daqsoft.baseandroid.ui.home.model;

import android.support.annotation.NonNull;

import com.yanb.daqsoft.baselib.mvvmbase.base.BaseViewModel;
import com.yanb.daqsoft.baselib.mvvmbase.base.MultiItemViewModel;
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingAction;
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingCommand;
import com.yanb.daqsoft.baselib.utils.ToastUtils;
;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleHeadViewModel extends MultiItemViewModel {

    public MultiRecycleHeadViewModel(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
