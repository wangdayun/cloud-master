package com.cloud.feign.hystrix;

import com.cloud.feign.service.UserFeignApi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 熔断处理
 *
 * @author dayun_wang
 */
@Component
public class UserFeignApiHystrix implements UserFeignApi {
    @Override
    public List getUserMessage() {
        List list = new ArrayList();
        list.add("user失败");
        return list;
    }

    @Override
    public List getTestMessage() {
        List list = new ArrayList();
        list.add("test失败");
        return list;
    }
}
