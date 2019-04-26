package com.cloud.feign.service.serviceimpl;

import com.cloud.feign.mapper.SortMapper;
import com.cloud.feign.service.SortService;
import com.cloud.feign.service.UserFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类
 *
 * @author dayun_wang
 */
@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private UserFeignApi userFeignApi;

    @Autowired
    private SortMapper sortMapper;

    @Override
    public List getSortMessage() {
        return sortMapper.selectBySort();
    }

    @Override
    public List getTestMessage() {
        return userFeignApi.getTestMessage();
    }
}
