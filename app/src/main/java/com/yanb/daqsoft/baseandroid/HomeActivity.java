package com.yanb.daqsoft.baseandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daqsoft.update.AppUpdater;
import com.daqsoft.zxinglib.Intents;
import com.yanb.daqsoft.baseandroid.common.StorageConstants;
import com.yanb.daqsoft.baseandroid.home.HomeMainFragment;
import com.yanb.daqsoft.baseandroid.home.MeFragment;
import com.yanb.daqsoft.baseandroid.login.LoginFragment;
import com.yanb.daqsoft.baseandroid.rxexample.Rxjava2ExampleFragment;
import com.yanb.daqsoft.baselib.activities.BaseSupportActivity;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.delegates.BaseHomeDraweFragment;
import com.yanb.daqsoft.baselib.delegates.BaseSupportFragment;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 主Activity单页面设计
 */
public class HomeActivity extends BaseSupportActivity implements NavigationView
        .OnNavigationItemSelectedListener,BaseHomeDraweFragment.OnFragmentOpenDrawerListener {
    private String mUrl = "https://raw.githubusercontent.com/jenly1314/AppUpdater/master/app/release/app-release.apk";
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    /**
     * NavigationView上的名字和头像
     */
    private TextView mNavName;
    private ImageView mNavImg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        //new AppUpdater(this,mUrl).start();
        BaseSupportFragment fragment = (BaseSupportFragment) findFragment(HomeMainFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, HomeMainFragment.newInstance());
        }
        initDrawer();
    }

    /**
     * 初始化抽屉布局
     */
    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
//        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.nav_home);
        // 下面设置点击头像进入登录
        LinearLayout llheader = (LinearLayout) navView.getHeaderView(0);
        mNavName = (TextView) llheader.findViewById(R.id.tv_name);
        mNavImg = (ImageView) llheader.findViewById(R.id.img_nav);
        llheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
                drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //goLogin();
                        ToastUtils.showCenterShort("进入登录页面");
                    }
                }, 250);
            }
        });
    }

    /**
     * 设置动画，也可以使用setFragmentAnimator()设置
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                final ISupportFragment topFragment = getTopFragment();
                if (id == R.id.nav_login){
                    start(LoginFragment.newInstance());
                }
            }
        },300);
        return false;
    }

    @Override
    public void onBackPressedSupport() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            ISupportFragment topFragment = getTopFragment();
            // 主页的Fragment
            if (topFragment instanceof BaseHomeDraweFragment) {
                navView.setCheckedItem(R.id.nav_home);
            }
            if (getSupportFragmentManager().getBackStackEntryCount()>1){
                pop();
            }else {
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    finish();
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    ToastUtils.showLong(R.string.press_again_exit);
                }
            }
        }
    }
    /**
     * 打开抽屉
     */
    @Override
    public void onOpenDrawer() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StorageConstants.REQUEST_CODE_SCAN && resultCode == RESULT_OK && data != null){
            String result = data.getStringExtra(Intents.Scan.RESULT);
            ToastUtils.showCenterShort(result);
        }
    }
}
