package com.yanb.daqsoft.baseandroid.http;

import com.daqsoft.xhttp.Xhttp;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-30.13:33
 * @since JDK 1.8
 */

public class XhttpUtils {
    public static ApiService httpApiService;
    public static ApiService getApiService(){
        return Xhttp.getInstance().getmRetrofit().create(ApiService.class);
    }
}
