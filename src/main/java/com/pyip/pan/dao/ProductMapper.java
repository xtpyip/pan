package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("select * from tb_product order by priority desc LIMIT 0,10;")
    List<Product> getHot();

}
