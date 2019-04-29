package com.yanb.daqsoft.baselib.net.callback;

import android.os.Handler;
import android.support.v4.app.NavUtils;

import com.yanb.daqsoft.baselib.app.Apps;
import com.yanb.daqsoft.baselib.app.ConfigKeys;
import com.yanb.daqsoft.baselib.net.RequestCreator;
import com.yanb.daqsoft.baselib.ui.LatteLoader;
import com.yanb.daqsoft.baselib.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-4-28.13:45
 * @since JDK 1.8
 */

public final class RequestCallBack implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOAD_STYLE;
    private static final Handler HANDLER = Apps.getHandler();

    public RequestCallBack(IRequest request, ISuccess success, IError error, IFailure failure,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOAD_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR !=null){
                ERROR.onError(response.code(),response.message());
            }
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        if (REQUEST !=null){
            REQUEST.onFinish();
        }
        onRequestFinish();
    }

    /**
     * 完成
     */
    private void onRequestFinish() {
        final long delayed = Apps.getConfigByKeys(ConfigKeys.LOADER_DELAYED);
        if (LOAD_STYLE!= null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RequestCreator.getParams().clear();
                    LatteLoader.stopLoading();
                }
            },delayed);
        }

    }
}
