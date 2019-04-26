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

    /**
     * 获取测试信息
     *
     * @return
     */
    List getTestMessage();

    /**
     * 获取分类信息
     *
     * @return
     */
    List getSortMessage();
}
