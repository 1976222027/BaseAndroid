package com.yanb.daqsoft.baseandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.daqsoft.rxyhttp.EasyHttp;
import com.daqsoft.rxyhttp.callback.SimpleCallBack;
import com.daqsoft.rxyhttp.exception.ApiException;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.BaseRequestBean;
import com.yanb.daqsoft.baseandroid.common.UrlConstants;
import com.yanb.daqsoft.baseandroid.login.User;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 首页
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.16:44
 * @since JDK 1.8
 */

public class HomeFragment extends BaseSupportFragment{

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        getData();
    }

    private void getData() {
        EasyHttp.get(UrlConstants.LOGIN)
                .params("ignoreCode","1")
                .params("account","13778069524")
                .params("password","qyiZj1Q8etTrdTJ8d%2BgSTw%3D%3D%0A")
                .params("siteCode","ycyjywgw")
                .execute(new SimpleCallBack<BaseRequestBean<User>>() {

                    @Override
                    public void onError(ApiException e) {
                        Logger.e("错误!"+e.getMessage());
                    }

                    @Override
                    public void onSuccess(BaseRequestBean<User> bean) {
                        Logger.e("你请求的地址-->"+bean.getData().getAccount());
                    }
                });
    }
}
