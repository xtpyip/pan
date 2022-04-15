package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
