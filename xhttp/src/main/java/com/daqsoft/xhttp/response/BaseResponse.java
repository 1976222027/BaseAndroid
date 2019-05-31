package com.daqsoft.xhttp.response;

import java.util.List;

/**
 * 基础请求实体类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-27.16:06
 * @since JDK 1.8
 */

public class BaseResponse<T> {

    private long responseTime;
    private String message;
    private int code;
    private PageBean page;
    private T data;
    private List<T> datas;

    public T getData() {
        return data;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }


    public static class PageBean {
        /**
         * total : 45
         * currPage : 1
         * pageSize : 10
         * totalPage : 5
         */

        private int total;
        private int currPage;
        private int pageSize;
        private int totalPage;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }
    }

}
