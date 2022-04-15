package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.CartMapper;
import com.pyip.pan.domin.Cart;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.ICartService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public Boolean deleteByUidAndPid(Integer pid, Integer uid) {
        return cartMapper.deleteByUid(pid,uid);
    }

    @Override
    public IPage<Cart> getPage(Integer currentPage, Integer pageSize, Cart cart) {
        LambdaQueryWrapper<Cart> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(""+cart.getPid()) && cart.getPid() != null,Cart::getPid,cart.getPid());

        IPage<Cart> page = new Page<>(currentPage, pageSize);
        return cartMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Cart> getPage(Cart cart) {
        LambdaQueryWrapper<Cart> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(""+cart.getPid()) && cart.getPid() != null,Cart::getPid,cart.getPid());

        IPage<Cart> page = new Page<>();
        return cartMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Cart> getPage(int currentPage, int pageSize) {
        IPage<Cart> page = new Page<>(currentPage, pageSize);
        return cartMapper.selectPage(page,null);
    }

    @Override
    public Integer getByPidAndUid(Integer pid, Integer uid) {
        return cartMapper.getByPidAndUid( pid,  uid);
    }
}
