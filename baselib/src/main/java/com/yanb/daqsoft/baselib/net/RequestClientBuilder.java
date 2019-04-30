package com.yanb.daqsoft.baselib.net;

import android.content.Context;

import com.yanb.daqsoft.baselib.net.callback.IError;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.net.callback.IRequest;
import com.yanb.daqsoft.baselib.net.callback.ISuccess;
import com.yanb.daqsoft.baselib.ui.LoaderStyle;

import java.io.File;
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

public final class RequestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String,Object> PARAMSMAP = RequestCreator.getParams();
    private IRequest mIRequest = null;
    private IError mIError = null;
    private IFailure mIFailure =null;
    private ISuccess mISuccess = null;
    private RequestBody mBody =null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
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

    /**
     * 请求参数拼接
     * 支持map,及键值对
     */
    public final RequestClientBuilder params(WeakHashMap<String,Object> paramsmap){
        PARAMSMAP.putAll(paramsmap);
        return this;
    }
    public final RequestClientBuilder params(String key,Object value){
        PARAMSMAP.put(key,value);
        return this;
    }

    /**
     * 文件
     * @param file
     * @return
     */
    public final RequestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }
    public final RequestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 延伸自定义
     */
    public final RequestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }
    public final RequestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }
    /**
     * 下载地址
     * @param dir
     * @return
     */
    public final RequestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 语音
     */
    public final RequestClientBuilder raw(String raw) {
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

    /**
     * 加载框
     * @param context
     * @param style
     * @return
     */
    public final RequestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }
    public final RequestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }
    public final RequestClient build(){
        return new RequestClient(mUrl,mIRequest,mISuccess,mIFailure,mIError,PARAMSMAP,mBody,mContext,mLoaderStyle,mFile,mDownloadDir,mExtension,mName);
    }

}
