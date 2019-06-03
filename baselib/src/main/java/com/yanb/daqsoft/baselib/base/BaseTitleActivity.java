package com.yanb.daqsoft.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yanb.daqsoft.baselib.R;
import com.yanb.daqsoft.baselib.activities.AppManager;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.activities.IBaseView;
import com.yanb.daqsoft.baselib.utils.titlebar.CommonTitleBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 带标题的基础界面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-6-3.17:14
 * @since JDK 1.8
 */
public abstract class BaseTitleActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    protected P presenter;
    public Context mContext;
    public Unbinder mUnbinder;
    private LinearLayout parentLinearLayout;
    private CommonTitleBar commonTitleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        doBeforeSetContentView();
        initContentView(R.layout.include_activity_title_bar);
        if (getLayoutId()!=0){
            setContentView(getLayoutId());
            commonTitleBar=(CommonTitleBar)findViewById(R.id.common_title);
            commonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                            || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                        finish();
                    }
                }
            });
            mUnbinder = ButterKnife.bind(this);
            presenter = initPresenter();
            this.initView();
        }
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
     * 将子Activity布局添加到parentLinearLayout
     *
     */
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID,parentLinearLayout,true);

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
    /**
     * 将baseActivity布局添加到parentLinearLayout
     * @param layoutId
     */
    private void initContentView(int layoutId) {
        //把parentLinearLayout布局添加到viewGroup里实现布局的关联
        ViewGroup viewGroup = findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutId,parentLinearLayout,true);
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
