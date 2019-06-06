package com.yanb.daqsoft.baseandroid.http;


import com.daqsoft.xhttp.response.BaseResponse;
import com.yanb.daqsoft.baseandroid.common.UrlConstants;
import com.yanb.daqsoft.baseandroid.login.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 封装请求的接口
 */

public interface ApiService {

    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     */
    @GET(UrlConstants.LOGIN)
    Observable<BaseResponse<User>> login(@Query("ignoreCode") String ignoreCode, @Query
            ("account") String account, @Query("password") String password,@Query("siteCode") String siteCode);
}