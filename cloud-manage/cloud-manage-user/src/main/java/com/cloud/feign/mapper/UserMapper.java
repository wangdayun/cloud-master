package com.cloud.feign.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 用户信息
 *
 * @author dayun_wang
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Select("select *from user")
    List<Map> selectByUser();
}
