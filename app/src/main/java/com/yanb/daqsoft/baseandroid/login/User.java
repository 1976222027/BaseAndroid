package com.yanb.daqsoft.baseandroid.login;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-24.17:35
 * @since JDK 1.8
 */

public class User {

    /**
     * head : http://file.geeker.com.cn/uploadfile/ycyjywgw/1555989717942/.png
     * expire : 1558733548142
     * ucToken : 11fa1003-e3db-4f51-ac52-1685a1bed7f2
     * name : 优秀的
     * id : 30198
     * account : 13778069524
     * token : bc2e5100-33ef-485a-a501-0d85ac5201cf
     * ucId : 8987be4cf7a4403185d9bfa7545ff444
     */

    private String head;
    private long expire;
    private String ucToken;
    private String name;
    private int id;
    private String account;
    private String token;
    private String ucId;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getUcToken() {
        return ucToken;
    }

    public void setUcToken(String ucToken) {
        this.ucToken = ucToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUcId() {
        return ucId;
    }

    public void setUcId(String ucId) {
        this.ucId = ucId;
    }
}
