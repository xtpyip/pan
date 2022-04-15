package com.pyip.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.Cart;
import com.pyip.pan.domin.Product;

public interface ICartService extends IService<Cart> {

    Boolean deleteByUidAndPid(Integer pid, Integer uid);

    //定义一个模糊查询
    public IPage<Cart> getPage(Integer currentPage, Integer pageSize, Cart cart);
    //定义一个模糊查询
    public IPage<Cart> getPage(Cart cart);

    IPage<Cart> getPage(int currentPage,int pageSize);

    Integer getByPidAndUid(Integer pid, Integer uid);
}
