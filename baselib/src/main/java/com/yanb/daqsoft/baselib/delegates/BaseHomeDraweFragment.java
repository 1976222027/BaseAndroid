package com.yanb.daqsoft.baselib.delegates;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yanb.daqsoft.baselib.R;

/**
 * 主fragment带抽屉
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.14:02
 * @since JDK 1.8
 */

public abstract class BaseHomeDraweFragment extends BaseSupportFragment{
    protected OnFragmentOpenDrawerListener mOpenDraweListener;

    protected void initToolbarNav(Toolbar toolbar) {
        initToolbarNav(toolbar, false);
    }
    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDraweListener != null) {
                    mOpenDraweListener.onOpenDrawer();
                }
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDraweListener = (OnFragmentOpenDrawerListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentOpenDrawerListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mOpenDraweListener = null;
    }
    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }
}
