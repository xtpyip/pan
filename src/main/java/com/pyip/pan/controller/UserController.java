package com.pyip.pan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.User;
import com.pyip.pan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public JsonResult<List<User>> getAll(){
        List<User> users = userService.list();
        if (users != null){
            return new JsonResult<>(200,"执行成功",userService.list());
        }
        return new JsonResult<>(400,"执行失败",new ArrayList<User>());
    }
    @PostMapping
    public JsonResult<Boolean> save(@RequestBody User user) throws IOException {
        //注意这里模拟服务器超时故障
//        if (book.getName().equals("22")) throw new IOException();
//        boolean flag = bookService.save(book);
        boolean flag = userService.save(user);
        if(flag){
            return new JsonResult<>(200,"添加成功^_^",true);
        }
        return new JsonResult<>(500,"添加失败-_-!",false);
    }
    @DeleteMapping("{id}")
    public JsonResult<Boolean> delete(@PathVariable Integer id){
        boolean flag = userService.removeById(id);
        if(flag){
            return new JsonResult<>(200,"删除成功^_^",true);
        }
        return new JsonResult<>(600,"删除失败-_-!",false);
    }
    @PutMapping
    public JsonResult<Boolean> update(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        if(flag){
            return new JsonResult<>(200,"更新成功^_^",true);
        }
        return new JsonResult<>(700,"更新失败-_-!",false);
    }
    @GetMapping("{id}")
    public JsonResult<User> getById(@PathVariable Integer id) {
        User result = userService.getById(id);
        if(result != null){
            return new JsonResult<>(200,"查找成功^_^",result);
        }
        return new JsonResult<>(800,"查找失败-_-!",null);
    }
//    @GetMapping("{currentPage}/{pageSize}")
//    public JsonResult<IPage<User>> getPage(@PathVariable Integer currentPage,
//                               @PathVariable Integer pageSize) {
//        IPage<User> page = userService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
//        if (currentPage > page.getPages()){
//            page = userService.getPage((int)page.getPages(), pageSize);
//        }
//        return new JsonResult<>(200,"分页成功",page);
//    }
    @GetMapping("{currentPage}/{pageSize}")
    public JsonResult<IPage<User>> getPage(@PathVariable Integer currentPage,
                     @PathVariable Integer pageSize,
                     User user) {
        System.out.println("user: "+ user);
        IPage<User> page = userService.getPage(currentPage, pageSize,user);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
        if (currentPage > page.getPages()){
            page = userService.getPage((int)page.getPages(), pageSize);
        }

        return new JsonResult<>(200,"查询成功",page);
    }
    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024; //1m
    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
    }
    @RequestMapping("change_avatar")
    public JsonResult<User> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        //System.out.println("111");
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            return new JsonResult<>(6000,"头像上传不能为空",null);
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            return new JsonResult<>(6001,"不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件",null);
        }
// 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            return new JsonResult<>(6002,"不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES,null);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = "F:\\itcast\\pan\\src\\main\\resources\\static\\image";

        System.out.println(parent);
        File dir = new File(parent);
//        return new JsonResult<>(200,"不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES,null);

        // 保存的头像文件的文件名
//        System.out.println("================================="+session.getAttribute("uid"));
        String filename = UUID.randomUUID().toString() + session.getAttribute("uid") + ".png" ;
//
//        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            return new JsonResult<>(6004,"文件状态异常，可能文件已被移动或删除",null);
        } catch (IOException e) {
            // 抛出异常
            return new JsonResult<>(6005,"上传文件时读写错误，请稍后重尝试",null);
        }
//
//        // 头像路径
        String avatar = "../../image/" + filename;
//        // 从Session中获取uid和username
//        Integer uid = getUidFormSession(session);
//        String username = getUsernameFormSession(session);
//        // 将头像写入到数据库中
        System.out.println(avatar);
        List<User> list = userService.list();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getId() == (int)session.getAttribute("uid")){
                User user1 = new User();
                user1.setId((int)session.getAttribute("uid"));
                user1.setImg(avatar);
                userService.updateById(user1);
                return new JsonResult<>(200,"修改头像成功",user1);
            }
        }
        return new JsonResult<>(6006,"修改头像失败，请稍候再试",null);

    }


}
