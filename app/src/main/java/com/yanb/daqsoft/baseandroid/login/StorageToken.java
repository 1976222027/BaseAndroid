package com.yanb.daqsoft.baseandroid.login;

import com.yanb.daqsoft.baselib.utils.SPUtils;

/**
 * 存储token
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-29.10:17
 * @since JDK 1.8
 */

public class StorageToken {
    private static final String TOKEN = "token";
    private static final String SP_RX = "sp_rx";
    public static StorageToken getInstance(){
        return Holder.INSTANCE;
    }

    public static final class Holder{
        private static final StorageToken INSTANCE = new StorageToken();
    }
    public void setToken(String token){
        SPUtils.getInstance(SP_RX).put(TOKEN,token);
    }
    public String getToken(){
        return SPUtils.getInstance(SP_RX).getString(TOKEN);
    }
}
