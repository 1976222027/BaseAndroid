package com.yanb.daqsoft.baselib.net;

import android.content.Context;

import com.yanb.daqsoft.baselib.net.callback.IError;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.net.callback.IRequest;
import com.yanb.daqsoft.baselib.net.callback.ISuccess;
import com.yanb.daqsoft.baselib.net.callback.RequestCallBack;
import com.yanb.daqsoft.baselib.ui.LatteLoader;
import com.yanb.daqsoft.baselib.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 *
 */
public class RequestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMSMAP = RequestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    public RequestClient(String url, IRequest request, ISuccess success, IFailure failure,
                         IError error, Map<String, Object> paramsmap, RequestBody body, Context context,LoaderStyle loaderStyle) {
        this.URL = url;
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
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
            case PUT:
                call = service.put(URL, PARAMSMAP);
                break;
            case DELETE:
                call = service.delete(URL, PARAMSMAP);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBack(IREQUEST, ISUCCESS, IERROR, IFAILURE);
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
