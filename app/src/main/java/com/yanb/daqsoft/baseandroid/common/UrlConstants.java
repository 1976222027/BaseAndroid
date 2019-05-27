package com.yanb.daqsoft.baseandroid.common;

/**
 * 公共参数
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-24.17:09
 * @since JDK 1.8
 */

public class UrlConstants {
    /**
     * 基础请求地址
     */
    public static final String BASE_URL = "http://ptisp.daqsoft.com";
    /**
     * 登录
     * ignoreCode=1&account=13778069524&password=qyiZj1Q8etTrdTJ8d%2BgSTw%3D%3D%0A&siteCode=ycyjywgw&lang=cn&token=
     */
    public static final String LOGIN = "/govapi/api/gov/app/member/login";
    /**
     * 景区列表
     * http://ptisp.daqsoft.com/govapi/api/gov/app/scenery/list?page=1&limitPage=10&siteCode=nngjapp&lang=cn&token=
     */
    public static final String SCENIC_LIST = "/govapi/api/gov/app/scenery/list";
}
