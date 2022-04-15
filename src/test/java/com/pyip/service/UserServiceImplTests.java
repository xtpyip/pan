package com.pyip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyip.pan.PanApplication;
import com.pyip.pan.domin.User;
import com.pyip.pan.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = PanApplication.class)
public class UserServiceImplTests {
    @Autowired
    private IUserService userService;

    @Test
    void testById(){
        System.out.println(userService.getById(7));
    }
    @Test
    void testSave(){
        User user = new User();
        user.setName("tom5");
        user.setPassword("123");
        user.setSex(1);
        user.setPhone("17712345678");
        boolean b = userService.save(user);
        System.out.println(b);
    }
    @Test
    void testDeleteById(){
        System.out.println(userService.removeById(3));
    }
    @Test
    void testUpdate(){
        User user = new User();
        user.setId(3);
        user.setPassword("aaaa");
        user.setName("tom2");
        System.out.println(userService.updateById(user));
    }
    @Test
    void testGetAll(){
        List<User> users = userService.list();
    }
    @Test
    void testPage(){
        //		见config.MPConfig中的MybatisPlusInterceptor()方法
//		通过添加拦截器来加limit
        IPage<User> iPage = new Page<>(2,5);
        userService.page(iPage);
        System.out.println(iPage.getCurrent());
        System.out.println(iPage.getSize());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getPages());
        System.out.println(iPage.getRecords());
    }
    @Test
    void testLike(){
        User user = new User();
        //user.setName("1");
        user.setSex(0);
        userService.getPage(user);
    }


}
