package com.yanb.daqsoft.baseandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daqsoft.customview.img.CircleImageView;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.StorageConstants;
import com.yanb.daqsoft.baseandroid.login.LoginFragment;
import com.yanb.daqsoft.baseandroid.login.StorageToken;
import com.yanb.daqsoft.baseandroid.rxexample.Rxjava2ExampleFragment;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;
import com.yanb.daqsoft.baselib.utils.glide.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.16:44
 * @since JDK 1.8
 */

public class MeFragment extends BaseSupportFragment {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_head)
    CircleImageView mHeadImg;


    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object getLayout() {
        return R.layout.fragment_me;
    }

    /**
     * 更新个人信息
     */
    public void notifyInfo() {
        tvName.setText(StorageToken.getInstance().getUserName());
        Logger.e("你的头像" + StorageToken.getInstance().getHeadImg());
        GlideUtils.loadImage(getSupportDelegate().getActivity(), mHeadImg, StorageToken
                .getInstance().getHeadImg(), R.mipmap.icon_head_default);
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }


    @OnClick({R.id.ll_me_logo, R.id.common_rxjava2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_me_logo:
                // 也可以像使用getParentFragment()的方式,拿到父Fragment来操作 或者使用 EventBusActivityScope
                ((HomeMainFragment) getParentFragment()).startBrotherFragmentForResult
                        (LoginFragment.newInstance(), StorageConstants.ME_REQUESD_CODE);
                break;
            case R.id.common_rxjava2:
                // 也可以像使用getParentFragment()的方式,拿到父Fragment来操作 或者使用 EventBusActivityScope
                ((HomeMainFragment) getParentFragment()).startBrotherFragment(Rxjava2ExampleFragment.newInstance());
                break;
        }
    }

}
