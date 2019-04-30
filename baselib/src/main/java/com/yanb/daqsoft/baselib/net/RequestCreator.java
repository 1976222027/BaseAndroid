package com.yanb.daqsoft.baselib.net;

import com.yanb.daqsoft.baselib.app.Apps;
import com.yanb.daqsoft.baselib.app.ConfigKeys;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class RequestCreator {
    /**
     * 参数容器
     */
    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    /**
     * 构建Okhttp
     */
    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;//超时
        private static final OkHttpClient.Builder BUILDER= new OkHttpClient.Builder();
        // 拦截器
        private static final ArrayList<Interceptor> INTERCEPTORS = Apps.getConfigByKeys(ConfigKeys.INTERCEPTOR);
        private static OkHttpClient.Builder addIntercepter(){
            if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:
                     INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addIntercepter()
                .connectTimeout(TIME_OUT,TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构造全局Retrofit客户端
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL = Apps.getConfigByKeys(ConfigKeys.API_HOST);
        private static final Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    private static final class RequestServiceHolder{
        private static final RequestService REQUEST_SERVICE = RetrofitHolder.mRetrofit.create(RequestService.class);
    }

    /**
     * 获取请求服务
     * @return
     */
    public static RequestService getRequestService(){
        return RequestServiceHolder.REQUEST_SERVICE;
    }
}
