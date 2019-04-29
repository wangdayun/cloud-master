package com.cloud.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.cloud.core.common.JwtKeyUtils;
import com.cloud.core.constant.RsaResult;
import com.cloud.feign.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Feign拦截器
 *
 * @author : dayun_wang
 */
public class TokenInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
        if (ex != null) {
            BaseUtils.addDebugLog("<=afterCompletion-解析Token失败...:" + ex.getMessage());
            this.handleException(response);
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String uri = request.getRequestURI();
//        String authorization = request.getHeader("x-label");
//        BaseUtils.addDebugLog("<=preHandle-权限拦截器...:" + uri + ",authorization:" + authorization);
//        // 配置免验证的方法
//
//        // 验证token是否正确
//        if (StringUtils.isNotBlank(authorization)) {
//            JwtKeyUtils.getInstance();
//            RsaResult result = JwtKeyUtils.checkToken(authorization);
//            if (result.isStatus()) {
//                BaseUtils.addDebugLog("<=preHandle-权限拦截器...验证成功uri:" + uri);
//                return true;
//            }
//        }
//        BaseUtils.addDebugLog("<=preHandle-权限拦截器...验证失败,非法请求！uri:" + uri);
//        handleException(response);
        return true;
    }

    private void handleException(HttpServletResponse response) throws Exception {
        DataUtils responseData = DataUtils.fail(CodeUtils.NO_AUTH_CODE.getCode(), "非法请求...");
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(responseData));
        response.flushBuffer();
    }
}
