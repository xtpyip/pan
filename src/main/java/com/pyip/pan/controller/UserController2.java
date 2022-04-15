package com.pyip.pan.controller;

import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.User;
import com.pyip.pan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController2 {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    private JsonResult<Boolean> go(@PathParam("name") String name,
                                   @PathParam("password") String password){
        List<User> list = userService.list();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getName().equals(name)){
                return new JsonResult<>(300,"用户名重复",false);
            }
        }
        User user1 = new User();
        user1.setName(name);
        user1.setPassword(password);
        return new JsonResult<>(200,"成功了",userService.save(user1));
    }
    @PostMapping("/login")
    private JsonResult<User> goLogin(@PathParam("name") String name,
                                     @PathParam("password") String password,
                                     HttpSession session){
        List<User> list = userService.list();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                //System.out.println(user);
                session.setAttribute("uid",user.getId());
                return new JsonResult<>(200,"登陆成功",user);
            }
        }
        return new JsonResult<>(400,"用户名或密码错误",null);
    }

    @GetMapping("{id}")
    private JsonResult<User> goLogin(@PathVariable("id") Integer id){
        User result = userService.getById(id);
        if(result != null){
            return  new JsonResult<>(200,"查找信息成功",result);
        }
        return new JsonResult<>(4000,"信息同步失败，请稍候再试",null);
    }
}
