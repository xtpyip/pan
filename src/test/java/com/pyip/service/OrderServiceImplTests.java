package com.pyip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyip.pan.PanApplication;
import com.pyip.pan.domin.Order;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PanApplication.class)
public class OrderServiceImplTests {
    @Autowired
    private IOrderService orderService;

    @Test
    void testById(){
        orderService.getById(1);
    }

    @Test
    void testPage(){
        //		见config.MPConfig中的MybatisPlusInterceptor()方法
//		通过添加拦截器来加limit
        IPage<Order> iPage = new Page<>(2,5);
        orderService.page(iPage);
        System.out.println(iPage.getCurrent());
        System.out.println(iPage.getSize());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getPages());
        System.out.println(iPage.getRecords());
    }
    @Test
    void testLike(){
        Order order = new Order();
        order.setPay(1);
        order.setUid(1);
        order.setPid(3);
        IPage<Order> page = orderService.getPage(order);
        System.out.println(page.getRecords());
    }
}
