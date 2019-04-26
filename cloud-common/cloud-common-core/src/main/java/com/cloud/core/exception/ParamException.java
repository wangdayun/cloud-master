package com.cloud.core.exception;

import com.cloud.feign.util.CodeUtils;

/**
 * 参数化异常
 *
 * @author : dayun_wang
 */
public class ParamException extends GlobalException {
    public ParamException(String message) {
        super(message, CodeUtils.PARAM_ERROR_CODE.getCode());
    }
}
