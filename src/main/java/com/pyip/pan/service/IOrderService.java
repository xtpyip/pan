package com.pyip.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.Order;

import java.util.List;

public interface IOrderService extends IService<Order> {
    //定义一个模糊查询
    public IPage<Order> getPage(Integer currentPage, Integer pageSize, Order order);
    //定义一个模糊查询
    public IPage<Order> getPage(Order order);

    IPage<Order> getPage(int currentPage,int pageSize);

    Integer getByPidAndUid(Integer pid, Integer uid);
}
