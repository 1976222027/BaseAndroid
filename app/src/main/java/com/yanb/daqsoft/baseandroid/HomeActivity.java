package com.yanb.daqsoft.baseandroid;
import com.daqsoft.branch_login.SplashFragment;
import com.yanb.daqsoft.baselib.activities.BaseSupportActivity;
import com.yanb.daqsoft.baselib.delegates.MainSlidingFragment;

public class HomeActivity extends BaseSupportActivity {

    @Override
    public MainSlidingFragment setRootDelegate() {
        return new CustomviewExampleFragment();
    }
}
