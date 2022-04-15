package com.pyip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyip.pan.PanApplication;
import com.pyip.pan.domin.AddressUser;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IAddressUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = PanApplication.class)
public class AddressUserServiceImplTests {
    @Autowired
    private IAddressUserService addressUserService;

    @Test
    void testById(){
        AddressUser addressUser = new AddressUser();
        addressUser.setId(1);
        List<AddressUser> byId = addressUserService.getById(addressUser);
        for (int i = 0; i < byId.size(); i++) {
            System.out.println(byId.get(i));
        }
    }
    @Test
    void testPage(){
        IPage<AddressUser> iPage = new Page<>(2,5);
        addressUserService.page(iPage);
        System.out.println(iPage.getCurrent());
        System.out.println(iPage.getSize());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getPages());
        System.out.println(iPage.getRecords());
    }
    @Test
    void testLike(){
        AddressUser addressUser = new AddressUser();
        addressUser.setId(1);
        addressUser.setAddress("1");
        addressUserService.getPage(addressUser);
    }

}
