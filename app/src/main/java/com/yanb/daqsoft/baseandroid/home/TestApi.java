package com.yanb.daqsoft.baseandroid.home;



import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-27.10:11
 * @since JDK 1.8
 */

public class TestApi<T>  {
    private long responseTime;
    private String message;
    private List<T> datas;

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

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
