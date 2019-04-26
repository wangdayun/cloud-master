package com.cloud.core.exception;


import com.cloud.feign.util.CodeUtils;

/**
 * 服务器异常类
 *
 * @author : dayun_wang
 */
public class ServerException extends GlobalException {

    public ServerException(String message) {
        super(message, CodeUtils.SERVER_ERROR.getCode());
    }
}
