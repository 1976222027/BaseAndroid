package com.yanb.daqsoft.baseandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.daqsoft.customview.dropdownmenu.DropDownMenu;
import com.daqsoft.customview.dropdownmenu.adapter.ListDropDownAdapter;
import com.yanb.daqsoft.baselib.delegates.BaseSlidingFragment;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;
import com.yanb.daqsoft.baselib.utils.adapter.BaseQuickAdapter;
import com.yanb.daqsoft.baselib.utils.glide.GlideApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-13.13:33
 * @since JDK 1.8
 */

public class SelectedMemuFragment extends MainSlidingFragment {

    @BindView(R.id.drop_menu)
    DropDownMenu dropMenu;
    private List<View> popupViews = new ArrayList<>();
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter ageAdapter2;
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String headers[] = {"城市","性别"};
    @Override
    public Object getLayout() {
        return R.layout.fragment_selected_menu;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
    }

    private void initView() {
        //init age menu
        RecyclerView ageView = new RecyclerView(getActivity());
        ageView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> ageList = new ArrayList<>();
        for (int i = 0; i < ages.length; i++) {
            ageList.add(ages[i]);
        }
        ageAdapter = new ListDropDownAdapter(getActivity(),ageList);
        ageView.setAdapter(ageAdapter);
        popupViews.add(ageView);
        ageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ageAdapter.setCheckItem(position);
                dropMenu.setTabText(position == 0 ? headers[0] : ages[position]);
                dropMenu.closeMenu();
            }
        });

        RecyclerView ageView2 = new RecyclerView(getActivity());
        ageView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> ageList2 = new ArrayList<>();
        for (int i = 0; i < ages.length; i++) {
            ageList2.add(ages[i]);
        }
        ageAdapter2 = new ListDropDownAdapter(getActivity(),ageList2);
        ageView2.setAdapter(ageAdapter2);
        popupViews.add(ageView2);
        ageAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ageAdapter2.setCheckItem(position);
                dropMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                dropMenu.closeMenu();
            }
        });


        //init context view
        TextView contentView = new TextView(getActivity());
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        dropMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    @Override
    public void post(Runnable runnable) {

    }

}
