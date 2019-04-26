package com.cloud.core.exception;

/**
 * 全局异常类
 *
 * @author : dayun_wang
 */
public class GlobalException extends Exception {

    private int code;

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(GlobalException e){
        super(e.getMessage());
        this.code = e.getCode();
    }

    public GlobalException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
