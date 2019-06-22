package com.daqsoft.ktxhttp

/**
 * 基础数据源
 * @author 严博
 * @date 2019-6-20.17:01
 * @version 1.0.0
 * @since JDK 1.8
 */
class BaseResponse<T>(val code:Int,
                      val  msg:String,
                      val  data: T)

