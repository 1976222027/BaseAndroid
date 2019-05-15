package com.daqsoft.customview.commonrecycleview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.daqsoft.customview.R;
import com.yanb.daqsoft.baselib.utils.adapter.BaseQuickAdapter;

import java.util.List;

/**
 * 倾力打造列表公共布局
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-15.10:02
 * @since JDK 1.8
 */

public class CommonRecycleListView extends FrameLayout {
    /**
     * 列表
     */
    private RecyclerView mRv;
    /**
     * 适配器
     */
    private BaseQuickAdapter mAdapter;
    /**
     * 刷新控件
     */
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * 加载接口
     */
    public interface OnLoadListener{
        void onRefresh();
        void onLoadMore();
    }
    private OnLoadListener mLoadListener;
    public void setLoadListener(OnLoadListener listener){
        this.mLoadListener = listener;
    }
    public CommonRecycleListView(@NonNull Context context) {
        this(context,null);
    }

    public CommonRecycleListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonRecycleListView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout
                .layout_customview_comrecyclelist, null);
        mRv = (RecyclerView)view.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mLoadListener!=null){
                    mLoadListener.onRefresh();
                }
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(context));
        this.addView(view,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
    }

    /**
     * 设置适配器
     */
    public void setAdapter(BaseQuickAdapter adapter){
        this.mAdapter = adapter;
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mLoadListener!=null){
                    mLoadListener.onLoadMore();
                }
            }
        });
        mRv.setAdapter(mAdapter);
    }

    /**
     * 设置数据
     * @param isRefresh
     * @param data
     */
    public void setData(boolean isRefresh, List data) {
        //mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < 15) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }
    /**
     * 设置数据 不使用分页
     * @param data
     */
    public void setData(List data) {
        mAdapter.setNewData(data);
    }

}
