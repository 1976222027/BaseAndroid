package com.yanb.daqsoft.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yanb.daqsoft.baselib.activities.AppManager;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.activities.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-6-3.17:14
 * @since JDK 1.8
 */
public abstract class BaseActivity <P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    protected P presenter;
    public Context mContext;
    public Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        doBeforeSetContentView();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        presenter = initPresenter();
        this.initView();
    }
    // 获取布局ID
    public abstract int getLayoutId();
    // 初始化
    public abstract void initView();
    // 初始化Presenter
    public abstract P initPresenter();

    /**
     * 设置布局前设置
     */
    public void doBeforeSetContentView() {
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }
    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        // 将当前activity移除管理栈
        AppManager.getAppManager().removeActivity(this);
        if (presenter !=null){
            // 在presenter中解绑释放view
            presenter.detach();
            presenter = null;
        }
        if (mUnbinder !=null){
            mUnbinder.unbind();
        }
        super.onDestroy();
    }
}
