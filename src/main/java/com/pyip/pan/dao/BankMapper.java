package com.pyip.pan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyip.pan.domin.Bank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BankMapper extends BaseMapper<Bank> {
    @Select(" select * from tb_bank where uid = #{uid}")
    Bank getByUid(Integer uid);

    @Update("update tb_bank set money = money - #{total} where uid = #{uid}")
    Integer updateByUid(Integer uid, double total);
}
