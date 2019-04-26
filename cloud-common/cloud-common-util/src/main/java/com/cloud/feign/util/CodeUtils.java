package com.cloud.feign.util;

/**
 * 枚举类
 *
 * @author : dayun_wang
 */
public enum CodeUtils {
    /**
     * 正确
     */
    SUCCESS_CODE(200),
    /**
     * 参数错误
     */
    PARAM_ERROR_CODE(400),
    /**
     * 权限不够
     */
    LIMIT_ERROR_CODE(401),
    /**
     * Token过期
     */
    TOKEN_TIMEOUT_CODE(402),
    /**
     * 禁止访问
     */
    NO_AUTH_CODE(403),
    /**
     * 资源没有找到
     */
    NO_FOUND(404),
    /**
     * 服务器错误
     */
    SERVER_ERROR(500);

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    CodeUtils(int code) {
        this.code = code;
    }
}
