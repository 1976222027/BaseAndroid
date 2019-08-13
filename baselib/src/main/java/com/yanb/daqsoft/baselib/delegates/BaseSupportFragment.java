package com.yanb.daqsoft.baselib.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.activities.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author: yanbo
 * @date: 2019/4/25.
 * @Email: 760375443@qq.com
 * @Description: 布局 可以是view或layout
 * abstract 抽象类不想让用户new出
 * 丝滑单页面设计基类
 */

public abstract class BaseSupportFragment<P extends IBasePresenter> extends SupportFragment implements IBaseView {
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;
    public P mPresenter;
    protected View rootView;

    /**
     * 布局 可以是view或layout
     * 子类传入布局
     *
     * @return
     */
    public abstract Object getLayout();
    // 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract P initPresenter();
    public abstract void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLayout(), container, false);//false 没有父布局
        } else if (getLayout() instanceof View) {
            rootView = (View) getLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mUnbinder = ButterKnife.bind(this, rootView);// 绑定视图
        // 子类必须重写
        onBindView(savedInstanceState, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }
    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter != null){
            mPresenter.detach();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.rootView = null;
    }
}
