package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
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

    private static final String OPTIONS = "OPTIONS";

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
        RequestContext context = RequestContext.getCurrentContext();
        try {
            logger.info("AccessTokenFilter -开始鉴权...");
            HttpServletRequest ctx = context.getRequest();
            String method = ctx.getMethod();
            String authorization = ctx.getHeader("Authorization");

            if (OPTIONS.equalsIgnoreCase(method)) {
                return null;
            }
            if (StringUtils.isBlank(authorization)) {
                throw new ZuulException("缺少Authorization信息", 403, "Absence of token information...");
            }

            context.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authorization);
            context.addZuulRequestHeader("x-label", authorization);
        } catch (Exception e) {
            logger.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}
