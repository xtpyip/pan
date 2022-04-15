package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.AddressUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressUserMapper extends BaseMapper<AddressUser> {

    @Select("select * from tb_address_user where id = #{id}")
    List<AddressUser> getByIdNotPrimaryKey(Integer id);
}
