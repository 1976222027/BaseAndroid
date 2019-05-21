package com.yanb.daqsoft.baseandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;

/**
 * 首页
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.16:44
 * @since JDK 1.8
 */

public class FunFragment extends BaseSupportFragment{

    public static FunFragment newInstance() {
        Bundle args = new Bundle();
        FunFragment fragment = new FunFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object getLayout() {
        return R.layout.fragment_fun;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }
}
