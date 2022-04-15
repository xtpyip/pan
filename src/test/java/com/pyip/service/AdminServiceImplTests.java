package com.pyip.service;

import com.pyip.pan.PanApplication;
import com.pyip.pan.domin.Admin;
import com.pyip.pan.service.IAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PanApplication.class)
public class AdminServiceImplTests {
    @Autowired
    private IAdminService adminService;

    @Test
    void testById(){
        Admin admin = new Admin();
        admin.setId(1);
        adminService.getById(admin.getId());
    }
}
