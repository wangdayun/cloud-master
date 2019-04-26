package com.cloud.feign.service;

import java.util.List;

/**
 * 分类
 *
 * @author dayun_wang
 */
public interface SortService {
    /**
     * 获取分类信息
     *
     * @return
     */
    List getSortMessage();

    /**
     * 获取测试信息
     *
     * @return
     */
    List getTestMessage();
}
