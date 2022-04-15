package com.pyip.pan.controller;

import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.Admin;
import com.pyip.pan.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @PostMapping
    public JsonResult<Admin> login(@PathParam("name")String name,
                                    @PathParam("password")String password){
        List<Admin> list = adminService.list();
        for (int i = 0; i < list.size(); i++) {
            Admin admin = list.get(i);
            if(name.equals(admin.getName()) && password.equals(admin.getPassword())){
                return new JsonResult<>(200,"找到了",admin);
            }
        }
        return new JsonResult<>(400,"error",null);
    }

}
