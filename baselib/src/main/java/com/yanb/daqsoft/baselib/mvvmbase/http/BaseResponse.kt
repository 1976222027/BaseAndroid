package com.yanb.daqsoft.baselib.mvvmbase.http

/**
 *
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
class BaseResponse<T> {

    var responseTime: Long = 0
    var message: String? = null
    var code: Int = 0
    var page: PageBean? = null
    val data: T? = null
    var datas: List<T>? = null
    class PageBean {
        /**
         * total : 45
         * currPage : 1
         * pageSize : 10
         * totalPage : 5
         */
        var total: Int = 0
        var currPage: Int = 0
        var pageSize: Int = 0
        var totalPage: Int = 0
    }
    /**
     * 返回是否成功
     */
    val isOk: Boolean
        get() = code == 0
}
