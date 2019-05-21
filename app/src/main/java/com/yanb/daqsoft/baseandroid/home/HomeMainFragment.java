package com.yanb.daqsoft.baseandroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.daqsoft.tablayout.CommonTabLayout;
import com.daqsoft.tablayout.listener.CustomTabEntity;
import com.daqsoft.tablayout.listener.OnTabSelectListener;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.common.IconConstants;
import com.yanb.daqsoft.baseandroid.entity.TabEntity;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseHomeDraweFragment;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主fragment 嵌套4个fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-21.13:49
 * @since JDK 1.8
 */

public class HomeMainFragment extends BaseHomeDraweFragment {
    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.com_tablayout)
    CommonTabLayout comTablayout;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    private BaseSupportFragment[] mFragments = new BaseSupportFragment[4];
    private static final int REQ_MSG = 10;
    /**
     * 底部导航按钮
     */
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "喜欢", "发现", "我的"};

    /**
     * 获取当前页面
     *
     * @return
     */
    public static HomeMainFragment newInstance() {
        return new HomeMainFragment();
    }

    @Override
    public Object getLayout() {
        return R.layout.fragment_main_home;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], IconConstants.getHomeIconSelectIds()[i], IconConstants.getHomeIconUnselectIds()[i]));
        }
        comTablayout.setTabData(mTabEntities);
        comTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ToastUtils.showLong("你选中"+position);
                showHideFragment(mFragments[position]);
            }

            @Override
            public void onTabReselect(int position) {
                ToastUtils.showLong("你重新选中"+position);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseSupportFragment firstFragment = (BaseSupportFragment) findChildFragment(HomeFragment.class);
        if (firstFragment == null){
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = FunFragment.newInstance();
            mFragments[THIRD] = FindFragment.newInstance();
            mFragments[FOUR] = MeFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR]);
        }else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = (BaseSupportFragment) findChildFragment(FunFragment.class);
            mFragments[THIRD] = (BaseSupportFragment) findChildFragment(FindFragment.class);
            mFragments[FOUR] = (BaseSupportFragment) findChildFragment(MeFragment.class);
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {

        }
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(BaseSupportFragment targetFragment) {
        start(targetFragment);
    }
}
