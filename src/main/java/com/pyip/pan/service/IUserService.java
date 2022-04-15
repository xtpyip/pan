package com.pyip.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.Product;
import com.pyip.pan.domin.User;

public interface IUserService extends IService<User> {
    //定义一个模糊查询
    public IPage<User> getPage(Integer currentPage, Integer pageSize, User user);
    //定义一个模糊查询
    public IPage<User> getPage(User user);

    IPage<User> getPage(int currentPage, Integer pageSize);
}
