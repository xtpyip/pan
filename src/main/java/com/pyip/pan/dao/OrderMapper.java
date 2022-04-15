package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select id from tb_order where pid = #{pid} and uid=#{uid}")
    Integer getByPidAndUid(Integer pid, Integer uid);
}
