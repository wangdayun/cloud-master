package com.cloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.cloud.feign.model.RsaResult;
import com.cloud.feign.util.*;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义拦截器
 *
 * @author : dayun_wang
 */
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String authorization = ctx.getRequest().getHeader("Authorization");
        if (StringUtils.isBlank(authorization)){
            ctx.setSendZuulResponse(false);
            ctx.set("isSuccess", false);
            DataUtils responseData = DataUtils.fail(CodeUtils.NO_AUTH_CODE.getCode(), "非法请求【缺少Authorization信息】");
            ctx.setResponseBody(JSON.toJSONString(responseData));
            ctx.getResponse().setContentType("application/json;charset=utf-8");
            return null;
        }

        JwtUtils jwtUtils = JwtUtils.getInstance();
        RsaResult jwt = JwtUtils.checkToken(authorization);
        if (!jwt.isStatus()){
            ctx.setSendZuulResponse(false);
            ctx.set("isSuccess", false);
            DataUtils responseData = DataUtils.fail(jwt.getCode(),jwt.getMsg());
            ctx.setResponseBody(JSON.toJSONString(responseData));
            ctx.getResponse().setContentType("application/json;charset=utf-8");
            return null;
        }
        ctx.addZuulRequestHeader("access-token", authorization);
        return null;
    }
}
