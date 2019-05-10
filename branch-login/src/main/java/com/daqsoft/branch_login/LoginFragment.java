package com.daqsoft.branch_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daqsoft.customview.login.LoginView;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-10.9:45
 * @since JDK 1.8
 */

public class LoginFragment extends MainSlidingFragment {
    @BindView(R2.id.login_loginview)
    LoginView loginView;

    @Override
    public Object getLayout() {
        return R.layout.login_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        loginView.setLoginListener(new LoginView.OnLoginListener() {
            @Override
            public void onLogin(String account, String password) {
                Logger.e("你的账号-->"+account+"你的密码-->"+password);
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
