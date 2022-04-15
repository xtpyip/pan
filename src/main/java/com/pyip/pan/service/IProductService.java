package com.pyip.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.Product;

import java.util.List;

public interface IProductService extends IService<Product> {

    //定义一个模糊查询
    public IPage<Product> getPage(Integer currentPage, Integer pageSize, Product product);
    //定义一个模糊查询
    public IPage<Product> getPage(Product product);

    IPage<Product> getPage(int currentPage,int pageSize);

    List<Product> getListByHot();
}
