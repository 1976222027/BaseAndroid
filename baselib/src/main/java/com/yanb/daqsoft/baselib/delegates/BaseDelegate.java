package com.yanb.daqsoft.baselib.delegates;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanb.daqsoft.baselib.app.Apps;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author: yanbo
 * @date: 2019/4/25.
 * @Email: 760375443@qq.com
 * @Description: 布局 可以是view或layout
 * abstract 抽象类不想让用户new出
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    /**
     * 布局 可以是view或layout
     * 子类传入布局
     * @return
     */
    public abstract Object getLayout();
    public Unbinder mUnbinder = null;
    public abstract void onBindView(@Nullable Bundle savedInstanceState,@Nullable View rootView);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (getLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLayout(), container, false);//false 没有父布局
        } else if (getLayout() instanceof View) {
            rootView = (View) getLayout();
        }
        if (rootView !=null){
            mUnbinder = ButterKnife.bind(this,rootView);// 绑定视图
            onBindView(savedInstanceState,rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
