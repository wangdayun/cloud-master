package com.cloud.feign.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 分类
 *
 * @author dayun_wang
 */
@Mapper
public interface SortMapper {

    /**
     * 查询分类
     *
     * @return
     */
    @Select("select *from sort where 1=1")
    List<Map> selectBySort();
}
