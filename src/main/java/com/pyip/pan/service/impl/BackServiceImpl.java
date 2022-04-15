package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.BackMapper;
import com.pyip.pan.domin.Back;
import com.pyip.pan.domin.Back;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IBackService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackServiceImpl extends ServiceImpl<BackMapper, Back> implements IBackService {
    @Autowired
    private BackMapper backMapper;

    @Override
    public IPage<Back> getPage(Integer currentPage, Integer pageSize, Back back) {
        LambdaQueryWrapper<Back> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(back.getDescription()),Back::getDescription,back.getDescription());

        IPage<Back> page = new Page<>(currentPage, pageSize);
        return backMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Back> getPage(Back back) {
        LambdaQueryWrapper<Back> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(back.getDescription()),Back::getDescription,back.getDescription());

        IPage<Back> page = new Page<>();
        return backMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Back> getPage(int currentPage, int pageSize) {
        IPage<Back> page = new Page<>(currentPage, pageSize);
        return backMapper.selectPage(page,null);
    }

    @Override
    public Integer insertInto(Back back) {
        return backMapper.insertInto(back);
    }
}
