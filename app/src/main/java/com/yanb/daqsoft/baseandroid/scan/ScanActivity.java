package com.yanb.daqsoft.baseandroid.scan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.PageConstants;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.base.BaseActivity;
import com.yanb.daqsoft.baselib.base.BaseTitleActivity;

@Route(path = PageConstants.ACTIVITY_SCAN)
public class ScanActivity extends BaseTitleActivity {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_scan;
    }

    @Override
    public void initView() {

    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }
}
