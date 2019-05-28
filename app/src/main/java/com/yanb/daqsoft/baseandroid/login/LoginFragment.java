package com.yanb.daqsoft.baseandroid.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baselib.activities.BaseTitleFragment;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;

/**
 * 登录
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-28.9:28
 * @since JDK 1.8
 */

public class LoginFragment extends BaseTitleFragment {

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }
}
