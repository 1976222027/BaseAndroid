package com.daqsoft.customview.filter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.yanb.daqsoft.baselib.utils.SizeUtils;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-10.16:11
 * @since JDK 1.8
 */

public class DropDownComAdapter implements DropDownAdapterInterface {
    private OnFilterDoneListener onFilterDoneListener;
    private final Context mContext;
    private String[] mTitleArray;

    public DropDownComAdapter(OnFilterDoneListener onFilterDoneListener, Context mContext,
                              String[] mTitleArray) {
        this.onFilterDoneListener = onFilterDoneListener;
        this.mContext = mContext;
        this.mTitleArray = mTitleArray;
    }

    /**
     * 接口选择结束
     */
    public interface OnFilterDoneListener {
        void onFilterDone(int position, String positionTitle, String urlValue);
    }

    @Override
    public int getTabCount() {
        return mTitleArray.length;
    }

    @Override
    public String getTabTitleNomal(int postion) {
        return mTitleArray[postion];
    }

    @Override
    public int getBottomMargin(int postion) {
        if (postion==3){
            return 0;
        }
        return SizeUtils.dp(mContext,140);
    }

    /**
     * 这里就是具体显示的View了
     * @param postion
     * @param parentContainer
     * @return
     */
    @Override
    public View getView(int postion, FrameLayout parentContainer) {
        return null;
    }
}
