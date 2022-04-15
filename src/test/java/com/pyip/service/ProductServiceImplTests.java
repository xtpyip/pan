package com.pyip.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyip.pan.PanApplication;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.List;

@SpringBootTest(classes = PanApplication.class)
public class ProductServiceImplTests {
    @Autowired
    private IProductService productService;

    @Test
    void testById(){
        System.out.println(productService.getById(2));
    }
    @Test
    void testSave(){
        Product product = new Product();
        product.setAddress("江西吉安");
        product.setName("住宅");
        product.setTitle("精品保障");
        product.setArea(200);
        boolean b = productService.save(product);
        System.out.println(b);
    }
    @Test
    void testDeleteById(){
        System.out.println(productService.removeById(8));
    }
    @Test
    void testUpdate(){
        Product product = new Product();
        product.setId(9);
        product.setAddress("江西吉安aaa");
        product.setName("住宅");
        product.setTitle("精品保障aaa");
        product.setArea(200);
        System.out.println(productService.updateById(product));
    }
    @Test
    void testGetAll(){
        List<Product> books = productService.list();
    }
    @Test
    void testPage(){
        //		见config.MPConfig中的MybatisPlusInterceptor()方法
//		通过添加拦截器来加limit
        IPage<Product> iPage = new Page<>(2,5);
        productService.page(iPage);
        System.out.println(iPage.getCurrent());
        System.out.println(iPage.getSize());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getPages());
        System.out.println(iPage.getRecords());
    }
    @Test
    void testLike(){
        Product product = new Product();
        product.setName("住宅");
        //product.setArea(200);
        product.setStatus(1);
        productService.getPage(product);
    }

}
