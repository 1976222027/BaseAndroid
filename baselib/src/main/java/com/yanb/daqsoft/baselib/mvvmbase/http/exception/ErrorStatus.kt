package com.yanb.daqsoft.baselib.mvvmbase.http.exception

/**
 * 错误状态码
 */
object ErrorStatus {
    /**
     * 响应成功
     */
    const val SUCCESS = 0
    /**
     * 未知错误
     */
    const  val UNKNOWN_ERROR = 1002
    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003
    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004
    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005
}