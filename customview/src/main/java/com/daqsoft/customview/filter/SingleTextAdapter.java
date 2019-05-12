package com.daqsoft.customview.filter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.daqsoft.customview.R;
import com.yanb.daqsoft.baselib.utils.adapter.BaseQuickAdapter;
import com.yanb.daqsoft.baselib.utils.adapter.BaseViewHolder;

import java.util.List;

/**
 * 单条目选择
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-10.16:36
 * @since JDK 1.8
 */

public class SingleTextAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder>{
    public SingleTextAdapter(@Nullable List<T> data) {
        super(R.layout.item_dropmenu_single_text, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.tv_item_filter,(String)item);
    }
}
