package com.daqsoft.customview.dropdownmenu.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.daqsoft.customview.R;
import com.yanb.daqsoft.baselib.utils.adapter.BaseQuickAdapter;
import com.yanb.daqsoft.baselib.utils.adapter.BaseViewHolder;

import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-13.14:36
 * @since JDK 1.8
 */

public class ListDropDownAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private int checkItemPosition = 0;
    private Context context;
    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }
    public ListDropDownAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.layout_customview_dropdown_singletext, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text,item);
        if (checkItemPosition !=-1){
            if (checkItemPosition==helper.getAdapterPosition()){
                helper.setTextColor(R.id.text,context.getResources().getColor(R.color.main));
            }else {
                helper.setTextColor(R.id.text,context.getResources().getColor(R.color.text_nomal));
            }
        }
    }
}
