package com.yanb.daqsoft.baseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daqsoft.imgselected.Matisse;
import com.daqsoft.imgselected.customimgup.ImageSelectionView;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.utils.KLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 图片上传
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-14.17:56
 * @since JDK 1.8
 */

public class ImgUpFragment extends MainSlidingFragment {
    @BindView(R.id.imgup_imgselectview)
    ImageSelectionView imgupImgselectview;


    @Override
    public Object getLayout() {
        return R.layout.fragment_imgup;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == 23 && resultCode == RESULT_OK) {

            KLog.e("图片大小" );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
