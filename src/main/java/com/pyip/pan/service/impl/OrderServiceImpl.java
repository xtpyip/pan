package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.OrderMapper;
import com.pyip.pan.domin.Order;
import com.pyip.pan.service.IOrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Order> getPage(Integer currentPage, Integer pageSize, Order order) {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(""+order.getPid()) && order.getPid() != null,Order::getPid,order.getPid());
        lqw.like(Strings.isNotEmpty(""+order.getUid()) && order.getUid() != null,Order::getUid,order.getUid());
        lqw.like(Strings.isNotEmpty(""+order.getPay()) && order.getPay() != null,Order::getPay,order.getPay());
        lqw.like(Strings.isNotEmpty(order.getAddress()),Order::getAddress,order.getAddress());

        IPage<Order> page = new Page<>(currentPage,pageSize);
        return orderMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Order> getPage(Order order) {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(""+order.getPid()) && order.getPid() != null,Order::getPid,order.getPid());
        lqw.like(Strings.isNotEmpty(""+order.getUid()) && order.getUid() != null,Order::getUid,order.getUid());
        lqw.like(Strings.isNotEmpty(""+order.getPay()) && order.getPay() != null,Order::getPay,order.getPay());
        lqw.like(Strings.isNotEmpty(order.getAddress()),Order::getAddress,order.getAddress());

        IPage<Order> page = new Page<>();
        return orderMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Order> getPage(int currentPage, int pageSize) {
        IPage<Order> page = new Page<>(currentPage, pageSize);
        return orderMapper.selectPage(page,null);
    }

    @Override
    public Integer getByPidAndUid(Integer pid, Integer uid) {
        return orderMapper.getByPidAndUid(pid,uid);
    }


}
