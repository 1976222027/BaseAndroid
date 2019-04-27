package com.yanb.daqsoft.baselib.net;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RequestService {
    /**
     * Get请求 先不传任何路径，请求什么传什么
     * QueryMap:以键值对的方式储存
     * @param url
     * @param params
     * @return
     */
    @GET
    Call<String> get(@Url String url, @QueryMap WeakHashMap<String,Object> params);


    /**
     * Post请求
     * 头头加 FormUrlEncoded（暂时不知道这个是什么用）
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap WeakHashMap<String,Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);


    /**
     * put请求
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap WeakHashMap<String,Object> params);
    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap WeakHashMap<String,Object> params);

    /**
     * 下载
     * Streaming 防止一次文件写入过大内存溢出
     * @param url
     * @param params
     * @return 返回的是ResponseBody请求体
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String,Object> params);

    /**
     * 上传
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
