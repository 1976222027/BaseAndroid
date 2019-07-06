package com.yanb.daqsoft.baseandroid.home;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.PageConstants;
import com.yanb.daqsoft.baseandroid.common.StorageConstants;
import com.yanb.daqsoft.baseandroid.example.databinding.DataBindingActivity;
import com.yanb.daqsoft.baseandroid.ktapi.KtExampleActivity;
import com.yanb.daqsoft.baseandroid.ktapp.KtMainActivity;
import com.yanb.daqsoft.baseandroid.picupdate.PictureUpdateActivity;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;
import com.yanb.daqsoft.baselib.permission.RxPermissions;
import com.yanb.daqsoft.baselib.utils.BarUtils;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 首页
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.16:44
 * @since JDK 1.8
 */

public class HomeFragment extends BaseSupportFragment implements AppBarLayout
        .OnOffsetChangedListener {
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.include_toolbar_open)
    View mToolbarOpenLayout;
    @BindView(R.id.include_toolbar_close)
    View mToolbarCloseLayout;
    @BindView(R.id.toolbar_open_bg_view)
    View mToolbarOpenBgView;
    @BindView(R.id.bg_toolbar_close)
    View mToolbarCloseBgView;
    @BindView(R.id.content_bg_view)
    View contentBgView;
    @BindView(R.id.status_bar)
    View status_bar;
    /**
     * 权限管理
     */
    private RxPermissions permissions;

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
        status_bar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, BarUtils.getStatusBarHeight()));
        appBarLayout.addOnOffsetChangedListener(this);
        permissions = new RxPermissions(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //垂直方向偏移量
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();
        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
            mToolbarOpenLayout.setVisibility(View.VISIBLE);
            mToolbarCloseLayout.setVisibility(View.GONE);
            //根据偏移百分比 计算透明值
            float scale2 = (float) offset / (scrollRange / 2);
            int alpha2 = (int) (255 * scale2);
            mToolbarOpenBgView.setBackgroundColor(Color.argb(alpha2, 117, 186, 255));
        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
            mToolbarOpenLayout.setVisibility(View.GONE);
            mToolbarCloseLayout.setVisibility(View.VISIBLE);
            float scale3 = (float) (scrollRange - offset) / (scrollRange / 2);
            int alpha3 = (int) (255 * scale3);
            mToolbarCloseBgView.setBackgroundColor(Color.argb(alpha3, 117, 186, 255));
        }
        //根据偏移百分比计算扫一扫布局的透明度值
        float scale = (float) offset / scrollRange;
        int alpha = (int) (255 * scale);
        contentBgView.setBackgroundColor(Color.argb(alpha, 117, 186, 255));
    }



    @OnClick({R.id.ll_scan, R.id.ll_aply,R.id.ll_picupdate,R.id.ll_kt_study})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_kt_study:
                startActivity(new Intent(getSupportDelegate().getActivity(), DataBindingActivity.class));
                break;
            case R.id.ll_picupdate:
                startActivity(new Intent(getSupportDelegate().getActivity(), PictureUpdateActivity.class));
                break;
            case R.id.ll_scan:
                scan();
                break;
            case R.id.ll_aply:
                ARouter.getInstance().build(PageConstants.ACTIVITY_CODE).navigation(getSupportDelegate().getActivity());
                break;
        }
    }
    /**
     * 扫描二维码
     */
    private void scan(){
        permissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            ARouter.getInstance().build(PageConstants.ACTIVITY_SCAN).navigation(getSupportDelegate().getActivity(),StorageConstants.REQUEST_CODE_SCAN);
                        }else {
                            ToastUtils.showCenterShort("获取相机权限失败!");
                        }
                    }
                });
    }
}
