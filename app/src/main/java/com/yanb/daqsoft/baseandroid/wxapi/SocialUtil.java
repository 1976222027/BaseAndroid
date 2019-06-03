package com.yanb.daqsoft.baseandroid.wxapi;


import io.agora.yshare.SocialHelper;

public enum SocialUtil {
    INSTANCE();

    public SocialHelper socialHelper;

    SocialUtil() {
        socialHelper = new SocialHelper.Builder()
                .setQqAppId("101585934")
                .setWxAppId("wx13ee55166072b68c")
                .setWxAppSecret("wxAppSecret")
                .build();
    }
}
