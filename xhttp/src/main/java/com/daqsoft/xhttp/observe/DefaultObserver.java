package com.daqsoft.xhttp.observe;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.daqsoft.xhttp.R;
import com.daqsoft.xhttp.exception.NoDataExceptionException;
import com.daqsoft.xhttp.exception.ServerResponseException;
import com.google.gson.JsonParseException;
import com.yanb.daqsoft.baselib.utils.ToastUtils;
import com.yanb.daqsoft.baselib.utils.Utils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * DefaultObserver
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-27.18:10
 * @since JDK 1.8
 */

public abstract class DefaultObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException){
            // HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        }else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        }else if(e instanceof ServerResponseException){
            onFail(e.getMessage());
        }else if (e instanceof NoDataExceptionException){
            onSuccess(null);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }

    }

    @Override
    public void onComplete() {

    }
    /**
     * 请求成功
     * @param response 服务器返回数据
     */
    public abstract void onSuccess(T response);

    /**
     * 服务器返回异常在这里统一处理
     */
    public void onFail(String message){
        ToastUtils.showCenterShort(message);
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.showCenterShort(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.showCenterShort(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtils.showCenterShort(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtils.showCenterShort(R.string.parse_error);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtils.showCenterShort(R.string.unknown_error);
                break;
        }
    }
    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
