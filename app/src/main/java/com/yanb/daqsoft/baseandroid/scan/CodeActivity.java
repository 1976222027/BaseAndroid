package com.yanb.daqsoft.baseandroid.scan;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.daqsoft.zxinglib.util.CodeUtils;
import com.google.zxing.BarcodeFormat;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.PageConstants;
import com.yanb.daqsoft.baseandroid.common.StorageConstants;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.base.BaseTitleActivity;
import com.yanb.daqsoft.baselib.permission.RxPermissions;
import com.yanb.daqsoft.baselib.utils.ObjectUtils;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 二维码生成
 */
@Route(path = PageConstants.ACTIVITY_CODE)
public class CodeActivity extends BaseTitleActivity {

    @BindView(R.id.ivCode)
    ImageView ivCode;
    /**
     * 权限管理
     */
    private RxPermissions permissions;
    @Override
    public int getLayoutId() {
        return R.layout.activity_code;
    }

    @Override
    public void initView() {
        setTitleText("付款码");
        permissions = new RxPermissions(this);
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    /**
     * 生成二维码
     *
     * @param content
     */
    private void createQRCode(String content) {
        //生成二维码最好放子线程生成防止阻塞UI，这里只是演示
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo);
        Bitmap bitmap = CodeUtils.createQRCode(content, 600, logo);
        //显示二维码
        ivCode.setImageBitmap(bitmap);
    }

    /**
     * 生成条形码
     *
     * @param content
     */
    private void createBarCode(String content) {
        //生成条形码最好放子线程生成防止阻塞UI，这里只是演示
        Bitmap bitmap = CodeUtils.createBarCode(content, BarcodeFormat.CODE_128, 800, 200, null,
                true);
        //显示条形码
        ivCode.setImageBitmap(bitmap);
    }


    @OnClick({R.id.btn_qr, R.id.btn_bar,R.id.btn_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_photo:
                photo();
                break;
            case R.id.btn_qr:
                createQRCode(getString(R.string.app_name));
                break;
            case R.id.btn_bar:
                createBarCode("1234567890");
                break;
        }
    }
    /**
     * 相册识别二维码
     */
    private void photo(){
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            Intent pickIntent = new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            startActivityForResult(pickIntent, StorageConstants.REQUEST_CODE_PHOTO);
                        }else {
                            ToastUtils.showCenterShort("APP需要用到读写权限!");
                        }
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case StorageConstants.REQUEST_CODE_PHOTO:
                    parsePhoto(data);
                    break;
            }

        }
    }
    private void parsePhoto(Intent data){
        final String path = UriUtils.INSTANCE.getImagePath(this,data);
        if(TextUtils.isEmpty(path)){
            return;
        }
        //异步解析
        asyncThread(new Runnable() {
            @Override
            public void run() {
                final String result = CodeUtils.parseCode(path);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ObjectUtils.isNotEmpty(result)){
                            ToastUtils.showCenterShort(result);
                        }else {
                            ToastUtils.showCenterShort("扫描失败!");
                        }
                    }
                });

            }
        });

    }
    private void asyncThread(Runnable runnable){
        new Thread(runnable).start();
    }
}
