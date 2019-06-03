package com.yanb.daqsoft.baseandroid.scan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.daqsoft.zxinglib.CaptureHelper;
import com.daqsoft.zxinglib.OnCaptureCallback;
import com.daqsoft.zxinglib.ViewfinderView;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baselib.activities.BaseTitleFragment;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.permission.RxPermissions;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

import butterknife.BindView;

/**
 * 扫描功能
 * 演示自定义扫码功能不需集成特殊界面
 * @author 严博
 * @version 1.0.0
 * @date 2019-6-3.14:57
 * @since JDK 1.8
 */

public class ScanFragment extends BaseTitleFragment implements OnCaptureCallback {
    private CaptureHelper mCaptureHelper;
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;

    public static ScanFragment newInstance() {
        Bundle args = new Bundle();
        ScanFragment fragment = new ScanFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object getLayout() {
        return R.layout.fragment_scan;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        setTitleText("扫一扫");
        initUi();
    }

    private void initUi() {
        mCaptureHelper = new CaptureHelper(this,surfaceView,viewfinderView);
        mCaptureHelper.vibrate(true)//震动
                .continuousScan(false);// 是否连续扫描
    }

    @Override
    public boolean onResultCallback(String result) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
        Logger.e("扫码onResume");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCaptureHelper.onCreate();
        Logger.e("扫码onActivityCreated");
    }


    @Override
    public void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
        Logger.e("扫码onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
        Logger.e("扫码onDestroy");
    }
}
