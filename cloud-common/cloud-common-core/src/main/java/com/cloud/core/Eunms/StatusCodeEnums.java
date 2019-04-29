package com.cloud.core.Eunms;

/**
 * 操作状态
 *
 * @author: dayun_wang
 */
public enum  StatusCodeEnums {
    /**
     * 正确
     */
    SUCCESS_CODE(200,"操作成功!"),
    /**
     * 参数错误
     */
    PARAM_ERROR_CODE(400,"未知错误,请稍后重试!"),
    /**
     * 权限不够
     */
    LIMIT_ERROR_CODE(401,"不好意思,你没有权限哟!"),
    /**
     * Token过期
     */
    TOKEN_TIMEOUT_CODE(402,"不好意思，登录过期啦!"),
    /**
     * 禁止访问
     */
    NO_AUTH_CODE(403,"不好意思,禁止访问!"),
    /**
     * 资源没有找到
     */
    NO_FOUND(404,"不好意思,资源没有找到!"),
    /**
     * 服务器错误
     */
    SERVER_ERROR(500,"不好意思,服务器发生错误,请稍后重新操作!");

    private Integer status;
    private String message;

    StatusCodeEnums(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
