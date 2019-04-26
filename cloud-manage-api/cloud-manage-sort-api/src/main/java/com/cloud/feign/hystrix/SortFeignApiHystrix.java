package com.cloud.feign.hystrix;

import com.cloud.feign.service.SortFeignApi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 熔断处理
 *
 * @author dayun_wang
 */
@Component
public class SortFeignApiHystrix implements SortFeignApi {
    @Override
    public List getSortMessage() {
        List list = new ArrayList();
        list.add("sort获取失败..");
        return list;
    }
}
