package com.yanb.daqsoft.baseandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daqsoft.customview.commonrecycleview.CommonRecycleListView;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.utils.adapter.BaseQuickAdapter;
import com.yanb.daqsoft.baselib.utils.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-15.10:28
 * @since JDK 1.8
 */

public class PullToRefreshUseFragment extends MainSlidingFragment {
    @BindView(R.id.comrefview)
    CommonRecycleListView comrefview;


    @Override
    public Object getLayout() {
        return R.layout.fragment_pulltorefresh;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initRv();
    }

    private void initRv() {
        List<String> mlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mlist.add("我是"+i);
        }
        comrefview.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_tour_list,mlist) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_name,item);
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }

}
