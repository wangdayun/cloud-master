package com.cloud.core.exception;

import com.cloud.feign.util.DataUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常类
 *
 * @author : dayun_wang
 */
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public DataUtils jsonErrorHandler(HttpServletRequest request, GlobalException glodal) {
        DataUtils response = new DataUtils();
        response.setCode(glodal.getCode());
        response.setMessage(glodal.getMessage());
        response.setStatus(false);
        response.setData(null);

        return response;
    }
}
