package com.yanb.daqsoft.baseandroid.scan;

import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.daqsoft.zxinglib.CaptureHelper;
import com.daqsoft.zxinglib.OnCaptureCallback;
import com.daqsoft.zxinglib.ViewfinderView;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.PageConstants;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.base.BaseTitleActivity;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 介绍二维码扫描用法
 * 最方便的方式直接跳CaptureActivity，回调resultCode == RESULT_OK && data!=null&&requestCode=REQUEST_CODE_SCAN
 * 支持界面自定义
 * 方式一继承CaptureActivity
 *  //获取CaptureHelper，里面有扫码相关的配置设置
 * getCaptureHelper().playBeep(true)//播放音效
 * .vibrate(true)//震动
 * .continuousScan(isContinuousScan);//是否连扫
 *
 * 方式二：如下通过实现类的方法
 */
@Route(path = PageConstants.ACTIVITY_SCAN)
public class ScanActivity extends BaseTitleActivity implements OnCaptureCallback {
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;
    private CaptureHelper mCaptureHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public void initView() {
        setTitleText("扫一扫");
        mCaptureHelper = new CaptureHelper(this, surfaceView, viewfinderView);
        mCaptureHelper.onCreate();
        mCaptureHelper.vibrate(true)// 震动
                .playBeep(true)// 播放声音
                .continuousScan(false);// 是否连续扫码
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * 扫码结果回调
     *
     * @param result 扫码结果
     * @return
     */
    @Override
    public boolean onResultCallback(String result) {
        /**
         * 连续扫码改为true即可
         */
        if (false) {
            ToastUtils.showCenterShort(result);
        }
        return false;
    }

    private void clickFlash(View v) {
        if (v.isSelected()) {
            offFlash();
            v.setSelected(false);
        } else {
            openFlash();
            v.setSelected(true);
        }

    }


    @OnClick({R.id.ivFlash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivFlash:
                clickFlash(view);
                break;
        }
    }
    /**
     * 关闭闪光灯（手电筒）
     */
    private void offFlash(){
        Camera camera = mCaptureHelper.getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }

    /**
     * 开启闪光灯（手电筒）
     */
    public void openFlash(){
        Camera camera = mCaptureHelper.getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
    }
}
