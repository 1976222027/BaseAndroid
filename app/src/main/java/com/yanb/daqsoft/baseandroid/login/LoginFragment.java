package com.yanb.daqsoft.baseandroid.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daqsoft.customview.login.LoginView;
import com.daqsoft.xhttp.Xhttp;
import com.daqsoft.xhttp.observe.DefaultObserver;
import com.daqsoft.xhttp.response.BaseResponse;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.AESEncryptUtil;
import com.yanb.daqsoft.baseandroid.http.XhttpUtils;
import com.yanb.daqsoft.baselib.activities.BaseTitleFragment;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.utils.ToastUtils;;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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

public class LoginFragment extends BaseTitleFragment {

    @BindView(R.id.login_loginview)
    LoginView mLoginView;
    static final String KEY_RESULT_TITLE = "title";

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
        mLoginView.setLoginListener(new LoginView.OnLoginListener() {
            @Override
            public void onLogin(String account, String password) {
                try {
                    String psd = AESEncryptUtil.Encrypt(password);
                    XhttpUtils.getApiService().login("1",account,psd,"nngjapp")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DefaultObserver<BaseResponse<User>>() {
                                @Override
                                public void onSuccess(BaseResponse<User> response) {
                                    ToastUtils.showLong(response.getData().getName());
                                    StorageToken.getInstance().setToken(response.getData().getToken());
                                    StorageToken.getInstance().setUserName(response.getData().getName());
                                    StorageToken.getInstance().setHeadImg(response.getData().getHead());
                                    Bundle bundle = new Bundle();
                                    bundle.putString(KEY_RESULT_TITLE, response.getData().getName());
                                    setFragmentResult(RESULT_OK, bundle);
                                    // 隐藏软键盘
                                    hideSoftInput();
                                    pop();
                                }

                                @Override
                                public void onFail(String message) {
                                    ToastUtils.showCenterShort(message);
                                    hideSoftInput();
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
