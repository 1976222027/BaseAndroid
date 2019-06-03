package com.yanb.daqsoft.baseandroid.wxapi;
import io.agora.yshare.SocialHelper;
import io.agora.yshare.WXHelperActivity;


public class WXEntryActivity extends WXHelperActivity {

    @Override
    protected SocialHelper getSocialHelper() {
        return SocialUtil.INSTANCE.socialHelper;
    }
}
