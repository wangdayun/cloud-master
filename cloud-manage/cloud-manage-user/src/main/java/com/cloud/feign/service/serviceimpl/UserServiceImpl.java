package com.cloud.feign.service.serviceimpl;

import com.cloud.feign.mapper.UserMapper;
import com.cloud.feign.service.UserFeignApi;
import com.cloud.feign.service.UserService;
import com.cloud.feign.service.SortFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息
 *
 * @author dayun_wang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SortFeignApi sortFeignApi;

    @Autowired
    private UserFeignApi userFeignApi;

    @Override
    public List getUserMessage() {
        return userMapper.selectByUser();
    }

    @Override
    public List getTestMessage() {
        return userMapper.selectByUser();
    }

    @Override
    public List getSortMessage() {
        return sortFeignApi.getSortMessage();
    }
}
