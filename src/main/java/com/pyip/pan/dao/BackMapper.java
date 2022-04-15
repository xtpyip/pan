package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.Back;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BackMapper extends BaseMapper<Back> {

    @Insert("insert tb_back values(#{id},#{description})")
    public Integer insertInto(Back back);
}
