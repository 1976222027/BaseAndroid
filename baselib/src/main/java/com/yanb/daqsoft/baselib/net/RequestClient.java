package com.yanb.daqsoft.baselib.net;

import android.content.Context;

import com.yanb.daqsoft.baselib.net.callback.IError;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.net.callback.IRequest;
import com.yanb.daqsoft.baselib.net.callback.ISuccess;
import com.yanb.daqsoft.baselib.net.callback.RequestCallBack;
import com.yanb.daqsoft.baselib.net.download.DownloadHandler;
import com.yanb.daqsoft.baselib.ui.LatteLoader;
import com.yanb.daqsoft.baselib.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 *
 */
public class RequestClient {
    private final String URL;//请求地址
    private static final WeakHashMap<String, Object> PARAMSMAP = RequestCreator.getParams();
    /**
     * 请求状态接口
     */
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    /**
     * 延伸
     */
    private final String EXTENSION;
    private final String NAME;
    /**
     * 下载
     */
    private final String DOWNLOAD_PATH;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;


    public RequestClient(String url, IRequest request, ISuccess success, IFailure failure,
                         IError error, Map<String, Object> paramsmap, RequestBody body, Context context,LoaderStyle loaderStyle,File file,String downpath,String extension,String name) {
        this.URL = url;
        this.EXTENSION = extension;
        this.NAME = name;
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
        this.FILE = file;
        this.DOWNLOAD_PATH  = downpath;
        PARAMSMAP.putAll(paramsmap);
    }

    public static RequestClientBuilder builder() {
        return new RequestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RequestService service = RequestCreator.getRequestService();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMSMAP);
                break;
            case POST:
                call = service.post(URL, PARAMSMAP);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMSMAP);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMSMAP);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBack(IREQUEST, ISUCCESS, IERROR, IFAILURE,LOADER_STYLE);
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        if (BODY ==null){
            request(HttpMethod.POST);
        }else {
            if (!PARAMSMAP.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public final void put(){
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMSMAP.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

    /**
     * 上传
     */
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
    //下载
    public final void download() {
        new DownloadHandler(URL, IREQUEST, DOWNLOAD_PATH, EXTENSION, NAME,
                ISUCCESS, IFAILURE, IERROR)
                .handleDownload();
    }
}