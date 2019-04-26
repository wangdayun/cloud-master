package com.cloud.zuul.config;

import com.cloud.core.constant.Const;
import com.cloud.feign.util.BaseUtils;
import com.cloud.feign.util.JwtUtils;
import com.cloud.feign.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时生成通用签名,设置过期时间
 *
 * @author: dayun_wang
 * @date: 2018-12-14 10:47
 */
@Component
public class AuthorizationTask {

    public final static long TIME = 60 * 1000 * 60 * 20;

    @Autowired
    private RedisUtils redisUtils;

    @Scheduled(fixedDelay = TIME)
    public void reloadToken(){
        String authorization = this.getToken();
        while (StringUtils.isBlank(authorization)){
            try {
                Thread.sleep(1000);
                authorization = this.getToken();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        BaseUtils.addDebugLog("token值为："+authorization);
        redisUtils.set(Const.RedisKeyConfig.AUTHORIZATION+":authorization", authorization);
    }

    private String getToken(){
        JwtUtils jwtUtils = JwtUtils.getInstance();
        String token = JwtUtils.getToken("wangdayun");
        return token;
    }
}
