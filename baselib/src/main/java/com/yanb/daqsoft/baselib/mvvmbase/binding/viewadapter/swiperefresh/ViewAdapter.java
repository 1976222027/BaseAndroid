package com.yanb.daqsoft.baselib.mvvmbase.binding.viewadapter.swiperefresh;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingCommand;


/**
 * Created by goldze on 2017/6/18.
 */
public class ViewAdapter {
    //下拉刷新命令
    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }

    //是否刷新中
    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

}
