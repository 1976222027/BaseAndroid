package com.yanb.daqsoft.baselib.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.yanb.daqsoft.baselib.R;
import com.yanb.daqsoft.baselib.utils.ObjectUtils;
import com.yanb.daqsoft.baselib.utils.titlebar.CommonTitleBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-28.10:13
 * @since JDK 1.8
 */

public abstract class BaseTitleFragment<P extends IBasePresenter> extends SupportFragment implements IBaseView {
    private CommonTitleBar commonTitleBar;
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;
    public P mPresenter;
    protected View rootView;
    /**
     * 带标题的父布局
     */
    private LinearLayout parentLinearLayout;

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
        if (rootView!=null){
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent!=null){
                parent.removeView(rootView);
            }
        }else {
            rootView = inflater.inflate(R.layout.include_title_bar,container,false);
            commonTitleBar=(CommonTitleBar)rootView.findViewById(R.id.common_title);
            commonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                            || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                        pop();
                    }
                }
            });
            ViewStub stub = (ViewStub) rootView.findViewById(R.id.view_stub);
            stub.setLayoutResource((Integer) getLayout());
            stub.inflate();
        }
        mUnbinder = ButterKnife.bind(this, rootView);// 绑定视图
        // 子类必须重写
        onBindView(savedInstanceState, rootView);
        return rootView;
    }
    /**
     * 将baseActivity布局添加到parentLinearLayout
     * @param layoutId
     */
    private void initContentView(int layoutId) {
        //把parentLinearLayout布局添加到viewGroup里实现布局的关联
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(getActivity());
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(getActivity()).inflate(layoutId,parentLinearLayout,true);
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
    public final BaseSupportActivity getBaseSupportActivity() {
        return (BaseSupportActivity) _mActivity;
    }

    /**
     * 设置标题文字
     */
    public void setTitleText(String title){
        if (commonTitleBar!=null){
            commonTitleBar.getCenterTextView().setText(title);
        }
    }
}
