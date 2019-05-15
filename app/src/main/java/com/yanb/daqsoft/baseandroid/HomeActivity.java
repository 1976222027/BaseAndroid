package com.yanb.daqsoft.baseandroid;
import android.content.Intent;

import com.daqsoft.branch_login.LoginFragment;
import com.daqsoft.branch_login.SplashFragment;
import com.yanb.daqsoft.baselib.activities.BaseSupportActivity;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;

public class HomeActivity extends BaseSupportActivity {

    @Override
    public MainSlidingFragment setRootDelegate() {
        return new PullToRefreshUseFragment();
    }


}
