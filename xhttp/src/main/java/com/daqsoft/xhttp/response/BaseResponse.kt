package com.daqsoft.xhttp.response

/**
 * 基础请求实体类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-27.16:06
 * @since JDK 1.8
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

}
