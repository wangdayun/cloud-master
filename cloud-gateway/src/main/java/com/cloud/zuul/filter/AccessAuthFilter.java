package com.cloud.zuul.filter;

import com.cloud.core.common.JwtKeyUtils;
import com.cloud.core.constant.RsaResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
/**
 * 自定义token验证
 *
 * @author : dayun_wang
 */
@Component
@Slf4j
public class AccessAuthFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessAuthFilter.class);

    private static final String AUTH_PATH = "/user";

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
        logger.info("AccessTokenFilter -开始鉴权...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try{
            doAuthenticate(requestContext);
        }catch (Exception e){
            logger.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    private void doAuthenticate(RequestContext context) throws ZuulException{
        HttpServletRequest ctx = context.getRequest();
        String requestURI = ctx.getRequestURI();

        // 过滤请求
        if(requestURI.contains(AUTH_PATH)){
            return;
        }

        String authorization = ctx.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)){
            throw new ZuulException("缺少Authorization信息", 403, "Absence of token information...");
        }

        JwtKeyUtils.getInstance();
        RsaResult jwt = JwtKeyUtils.checkToken(authorization);
        if (!jwt.isStatus()){
            throw new ZuulException("Authorization验证失败", 403, "Token validation fails...");
        }
        context.addZuulRequestHeader("x-label", authorization);
        return ;
    }
}
