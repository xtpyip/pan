package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Delete("delete from tb_cart where pid=#{pid} and uid= #{uid}")
    Boolean deleteByUid(Integer pid, Integer uid);

    @Select("select id from tb_cart where pid=#{pid} and uid = #{uid}")
    Integer getByPidAndUid(Integer pid, Integer uid);
}
