package com.yanb.daqsoft.baseandroid.common;

import com.yanb.daqsoft.baseandroid.R;

/**
 * 图标工具
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.16:00
 * @since JDK 1.8
 */

public class IconConstants {

    /**
     * 获取首页导航未选中时的图片
     * @return
     */
    public static int[] getHomeIconUnselectIds(){
        int[] mIconUnselectIds = {
                R.drawable.home_tab_home_normal, R.drawable.home_tab_find_normal,
                R.drawable.home_tab_service_normal, R.drawable.home_tab_mine_normal};
        return mIconUnselectIds;
    }
    /**
     * 获取首页导航选中时的图片
     * @return
     */
    public static int[] getHomeIconSelectIds(){
        int[] mIconUnselectIds = {
                R.drawable.home_tab_home_selected, R.drawable.home_tab_find_selected,
                R.drawable.home_tab_service_selected, R.drawable.home_tab_mine_selected};
        return mIconUnselectIds;
    }
}
