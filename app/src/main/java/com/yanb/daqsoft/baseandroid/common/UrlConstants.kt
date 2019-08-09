package com.yanb.daqsoft.baseandroid.common

/**
 * 公共参数
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-24.17:09
 * @since JDK 1.8
 */

object UrlConstants {
    /**
     * 基础请求地址
     */
    const val BASE_URL = "http://ptisp.daqsoft.com"
    /**
     * 登录
     * ignoreCode=1&account=13778069524&password=qyiZj1Q8etTrdTJ8d%2BgSTw%3D%3D%0A&siteCode=ycyjywgw&lang=cn&token=
     */
    const val LOGIN = "/govapi/api/gov/app/member/login"
    /**
     * 景区列表
     * http://ptisp.daqsoft.com/govapi/api/gov/app/scenery/list?page=1&limitPage=10&siteCode=nngjapp&lang=cn&token=
     */
    const val SCENIC_LIST = "/govapi/api/gov/app/scenery/list"
}
