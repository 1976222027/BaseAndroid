package com.yanb.daqsoft.baseandroid.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.daqsoft.customview.login.LoginView;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.wxapi.SocialUtil;
import com.yanb.daqsoft.baselib.activities.BaseTitleFragment;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse;
import com.yanb.daqsoft.baselib.mvvmbase.http.observe.DefaultObserver;
import com.yanb.daqsoft.baselib.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import io.agora.yshare.SocialHelper;
import io.agora.yshare.callback.SocialLoginCallback;
import io.agora.yshare.entities.ThirdInfoEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-28.9:28
 * @since JDK 1.8
 */

public class LoginFragment extends BaseTitleFragment implements SocialLoginCallback {

    @BindView(R.id.login_loginview)
    LoginView mLoginView;
    static final String KEY_RESULT_TITLE = "title";
    /**
     * 三方登录
     */
    private SocialHelper socialHelper;

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
        socialHelper = SocialUtil.INSTANCE.socialHelper;
        mLoginView.setLoginListener(new LoginView.OnLoginListener() {
            @Override
            public void onLogin(String account, String password) {


            }
        });
    }


    @OnClick({R.id.mine_login_wechat, R.id.mine_login_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_login_wechat:

                break;
            case R.id.mine_login_qq:
                // QQ登录
                socialHelper.loginQQ(getBaseSupportActivity(), this);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && socialHelper != null) {
            socialHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != socialHelper) {
            socialHelper.clear();
        }
    }

    @Override
    public void socialError(String msg) {
        ToastUtils.showCenterShort("登录取消");
    }

    @Override
    public void loginSuccess(ThirdInfoEntity info) {
        ToastUtils.showCenterShort("登录成功");
    }
}
