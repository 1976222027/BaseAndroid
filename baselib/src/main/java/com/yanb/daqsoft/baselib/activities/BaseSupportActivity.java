package com.yanb.daqsoft.baselib.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.yanb.daqsoft.baselib.R;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author: yanbo
 * @date: 2019/4/25.
 * @Email: 760375443@qq.com
 * @Description: 基础SupportActivity
 */

public abstract class BaseSupportActivity extends SupportActivity {
    // 设置根delegate
    public abstract MainSlidingFragment setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    /**
     * 因为是单页面设计所以在这里可以做优化
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();//
        System.runFinalization();
    }
}
