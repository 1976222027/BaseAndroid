package com.yanb.daqsoft.baseandroid.common;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-24.17:33
 * @since JDK 1.8
 */

public class BaseRequestBean<T> {

    /**
     * responseTime : 1558690348579
     * message : 登录成功
     * code : 0
     * data : {"head":"http://file.geeker.com.cn/uploadfile/ycyjywgw/1555989717942/.png",
     * "expire":1558733548142,"ucToken":"11fa1003-e3db-4f51-ac52-1685a1bed7f2","name":"优秀的",
     * "id":30198,"account":"13778069524","token":"bc2e5100-33ef-485a-a501-0d85ac5201cf",
     * "ucId":"8987be4cf7a4403185d9bfa7545ff444"}
     */

    private long responseTime;
    private String message;
    private int code;
    private T data;

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
