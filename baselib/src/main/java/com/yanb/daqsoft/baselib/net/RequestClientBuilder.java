package com.yanb.daqsoft.baselib.net;

import com.yanb.daqsoft.baselib.net.callback.IError;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.net.callback.IRequest;
import com.yanb.daqsoft.baselib.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-4-28.11:11
 * @since JDK 1.8
 */

public class RequestClientBuilder {
    private String mUrl;
    private static final WeakHashMap<String,Object> PARAMSMAP = RequestCreator.getParams();
    private IRequest mIRequest;
    private IError mIError;
    private IFailure mIFailure;
    private ISuccess mISuccess;
    private RequestBody mBody;

    public RequestClientBuilder() {
    }

    /**
     *
     * @param url 传入地址
     * @return
     */
    public final RequestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }
    public final RequestClientBuilder params(WeakHashMap<String,Object> paramsmap){
        PARAMSMAP.putAll(paramsmap);
        return this;
    }
    public final RequestClientBuilder params(String key,Object value){
        PARAMSMAP.put(key,value);
        return this;
    }
    public final RequestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }
    public final RequestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }
    public final RequestClientBuilder onRrror(IError iError){
        this.mIError = iError;
        return this;
    }
    public final RequestClientBuilder onSuccess(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }
    public final RequestClientBuilder onFailure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }
    public final RequestClient build(){
        return new RequestClient(mUrl,mIRequest,mISuccess,mIFailure,mIError,PARAMSMAP,mBody);
    }

}
