package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.ProductMapper;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public IPage<Product> getPage(Integer currentPage, Integer pageSize, Product product) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(product.getName()),Product::getName,product.getName());
        lqw.like(Strings.isNotEmpty(""+product.getArea()) && product.getArea() != null,Product::getArea,product.getArea());
        lqw.like(Strings.isNotEmpty(""+product.getStatus()) && product.getStatus() != null,Product::getStatus,product.getStatus());
        lqw.like(Strings.isNotEmpty(""+product.getNum()) && product.getNum() != null,Product::getNum,product.getNum());

        IPage<Product> page = new Page<>(currentPage, pageSize);
        return productMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Product> getPage(Product product) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(product.getName()),Product::getName,product.getName());
        lqw.like(Strings.isNotEmpty(""+product.getArea()) && product.getArea() != null,Product::getArea,product.getArea());
        lqw.like(Strings.isNotEmpty(""+product.getStatus()) && product.getStatus() != null,Product::getStatus,product.getStatus());
        lqw.like(Strings.isNotEmpty(""+product.getNum()) && product.getNum() != null,Product::getNum,product.getNum());


        IPage<Product> page = new Page<>();
        return productMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<Product> getPage(int currentPage, int pageSize) {
        IPage<Product> page = new Page<>(currentPage, pageSize);
        return productMapper.selectPage(page,null);
    }

    @Override
    public List<Product> getListByHot() {
        return productMapper.getHot();
    }
}
