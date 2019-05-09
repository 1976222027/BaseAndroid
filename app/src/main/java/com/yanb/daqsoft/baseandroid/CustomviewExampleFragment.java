package com.yanb.daqsoft.baseandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.daqsoft.customview.example.CustomviewExampleView;
import com.daqsoft.customview.example.PieData;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 自定义view的实例页面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-9.17:26
 * @since JDK 1.8
 */

public class CustomviewExampleFragment extends MainSlidingFragment {

    @BindView(R.id.customview)
    CustomviewExampleView customview;

    @Override
    public Object getLayout() {
        return R.layout.fragment_customexample;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initPieData();
    }

    private void initPieData() {
        List<PieData> mlist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieData pieData = new PieData();
            pieData.setValue(i);
            mlist.add(pieData);
        }
        customview.setData(mlist);
    }

    @Override
    public void post(Runnable runnable) {

    }


}
