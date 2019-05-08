package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 续签token
 *
 * @author: dayun_wang
 */
@Component
@Slf4j
public class RenewAuthFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(RenewAuthFilter.class);

    @Resource
    private JwtTokenStore jwtTokenStore;

    /**
     * 20分钟
     */
    private static final int EXPIRES_IN = 60 * 20;


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("RenewAuthFilter - 续租...");
        RequestContext context = RequestContext.getCurrentContext();
        try{
            doFilter(context);
        }catch (Exception e){
            logger.error("RenewAuthFilter - token续租. [FAIL] EXCEPTION={}",e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }

    private void doFilter(RequestContext requestContext) throws ZuulException{
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)){
            return;
        }
        OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(authorization);
        int expiresIn = oAuth2AccessToken.getExpiresIn();

        if (expiresIn < EXPIRES_IN) {
            HttpServletResponse servletResponse = requestContext.getResponse();
            servletResponse.addHeader("Renew-Header", "true");
        }
    }
}
