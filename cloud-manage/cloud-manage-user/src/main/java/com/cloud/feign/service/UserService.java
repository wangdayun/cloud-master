package com.cloud.feign.service;

import java.util.List;

/**
 * 用户信息
 *
 * @author dayun_wang
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @return
     */
    List getUserMessage();
}
