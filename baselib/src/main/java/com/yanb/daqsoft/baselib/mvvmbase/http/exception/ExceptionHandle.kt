package com.yanb.daqsoft.baselib.mvvmbase.http.exception

import com.google.gson.JsonParseException
import com.yanb.daqsoft.baselib.utils.KLog
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * 错误的处理类
 */
class ExceptionHandle {
    companion object {
        var errorCode = ErrorStatus.UNKNOWN_ERROR
        var errorMsg = "请求失败，请稍后重试"
        fun handleException(e: Throwable): String {
            e.printStackTrace()
            //网络超时
            if (e is SocketTimeoutException || e is ConnectException || e is UnknownHostException) {
                KLog.e("网络连接异常:${e.message}")
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
                // 解析错误
            } else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException) {
                KLog.e("数据解析异常:${e.message}")
                errorMsg = "数据解析异常"
                errorCode = ErrorStatus.SERVER_ERROR
                //服务器返回的错误信息
            } else if (e is ApiException) {
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                errorCode = ErrorStatus.SERVER_ERROR
                // 未知
            } else {
                try {
                    KLog.e("错误:${e.message}")
                } catch (e1: Exception) {
                    KLog.e("未知错误Debug调试 ")
                }
            }
            return errorMsg
        }
    }
}