package com.cloud.feign.util;

/**
 * 返回状态工具类
 *
 * @author : dayun_wang
 */
public class DataUtils {
    private boolean status = true;
    private int code = 200;
    private String message;
    private Object data;

    public static DataUtils ok(Object data) {
        return new DataUtils(data);
    }

    public static DataUtils fail() {
        return new DataUtils(null);
    }

    public static DataUtils fail(String message) {
        return new DataUtils(message);
    }

    public static DataUtils fail(int code, String message) {
        return new DataUtils(code, message);
    }

    public static DataUtils failByParam(String message) {
        return new DataUtils(CodeUtils.PARAM_ERROR_CODE.getCode(), message);
    }

    public DataUtils() {

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataUtils(Object data) {
        super();
        this.data = data;
    }

    public DataUtils(String message) {
        this.message = message;
    }

    public DataUtils(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
