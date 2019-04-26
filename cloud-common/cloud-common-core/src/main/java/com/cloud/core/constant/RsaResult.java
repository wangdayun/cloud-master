package com.cloud.core.constant;

/**
 * RSA签名
 *
 * @author : dayun_wang
 */
public class RsaResult {
    private boolean status;
    private String uid;
    private String msg;
    private int code;

    public RsaResult() {

    }

    public RsaResult(boolean status, String uid, String msg, int code) {
        this.status = status;
        this.uid = uid;
        this.msg = msg;
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
