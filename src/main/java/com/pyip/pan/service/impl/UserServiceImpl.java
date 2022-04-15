package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.UserMapper;
import com.pyip.pan.domin.Product;
import com.pyip.pan.domin.User;
import com.pyip.pan.service.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> getPage(Integer currentPage, Integer pageSize, User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(user.getName()),User::getName,user.getName());
        lqw.like(Strings.isNotEmpty(user.getPhone()),User::getPhone,user.getPhone());
        lqw.like(Strings.isNotEmpty(""+user.getSex()) && user.getSex() != null,User::getSex,user.getSex());
        lqw.like(Strings.isNotEmpty(""+user.getScore()) && user.getScore() != null,User::getScore,user.getScore());


        IPage<User> page = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<User> getPage(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(user.getName()),User::getName,user.getName());
        lqw.like(Strings.isNotEmpty(user.getPhone()),User::getPhone,user.getPhone());
        lqw.like(Strings.isNotEmpty(""+user.getSex()) && user.getSex() != null,User::getSex,user.getSex());
        lqw.like(Strings.isNotEmpty(""+user.getScore()) && user.getScore() != null,User::getScore,user.getScore());

        IPage<User> page = new Page<>();
        return userMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<User> getPage(int currentPage, Integer pageSize) {
        IPage<User> page = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(page,null);
    }
}
