package com.daqsoft.customview.filter;

import android.view.View;
import android.widget.FrameLayout;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-10.16:12
 * @since JDK 1.8
 */

public interface DropDownAdapterInterface {
    /**
     * 设置筛选条目的个数
     */
    int getTabCount();

    /**
     * 设置每个tab筛选器默认的Title
     * @param postion
     * @return
     */
    String getTabTitleNomal(int postion);

    /**
     * 设置每个item距离底部的距离
     * @param postion
     * @return
     */
    int getBottomMargin(int postion);

    /**
     * 设置每个筛选条目的View
     * @param postion
     * @param parentContainer
     * @return
     */
    View getView(int postion, FrameLayout parentContainer);

}
