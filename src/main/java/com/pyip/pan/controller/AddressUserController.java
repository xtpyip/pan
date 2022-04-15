package com.pyip.pan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.AddressUser;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IAddressUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressUserController {
    @Autowired
    private IAddressUserService addressUserService;

    @GetMapping
    public JsonResult<List<AddressUser>> getAll(){
        List<AddressUser> addressUsers = addressUserService.list();
        if (addressUsers != null){
            return new JsonResult<>(200,"执行成功",addressUserService.list());
        }
        return new JsonResult<>(400,"执行失败",new ArrayList<AddressUser>());
    }
    @PostMapping
    public JsonResult<Boolean> save(@RequestBody AddressUser addressUser) throws IOException {
        //注意这里模拟服务器超时故障
//        if (book.getName().equals("22")) throw new IOException();
//        boolean flag = bookService.save(book);
        boolean flag = addressUserService.save(addressUser);
        if(flag){
            return new JsonResult<>(200,"添加成功^_^",true);
        }
        return new JsonResult<>(500,"添加失败-_-!",false);
    }
    @DeleteMapping("{id}")
    public JsonResult<Boolean> delete(@PathVariable Integer id){
        boolean flag = addressUserService.removeById(id);
        if(flag){
            return new JsonResult<>(200,"删除成功^_^",true);
        }
        return new JsonResult<>(600,"删除失败-_-!",false);
    }
    @PutMapping
    public JsonResult<Boolean> update(@RequestBody AddressUser addressUser) {
        boolean flag = addressUserService.updateByIdAndAddress(addressUser);
        if(flag){
            return new JsonResult<>(200,"更新成功^_^",true);
        }
        return new JsonResult<>(700,"更新失败-_-!",false);
    }
    @GetMapping("{id}")
    public JsonResult<List<AddressUser>> getById(@PathVariable Integer id) {
        List<AddressUser> result = addressUserService.getByIdNotPrimaryKey(id);
        System.out.println("==========="+result);
        if(result != null){
            return new JsonResult<>(200,"查找成功^_^",result);
        }
        return new JsonResult<>(800,"查找失败-_-!",null);
    }
//    @GetMapping("{currentPage}/{pageSize}")
//    public JsonResult<IPage<AddressUser>> getPage(@PathVariable Integer currentPage,
//                               @PathVariable Integer pageSize) {
//        IPage<AddressUser> page = addressUserService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
//        if (currentPage > page.getPages()){
//            page = addressUserService.getPage((int)page.getPages(), pageSize);
//        }
//        return new JsonResult<>(200,"分页成功",page);
//    }
    @GetMapping("{currentPage}/{pageSize}")
    public JsonResult<IPage<AddressUser>> getPage(@PathVariable Integer currentPage,
                                              @PathVariable Integer pageSize,
                                              AddressUser addressUser) {
        IPage<AddressUser> page = addressUserService.getPage(currentPage, pageSize,addressUser);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
        if (currentPage > page.getPages()){
            page = addressUserService.getPage((int)page.getPages(), pageSize);
        }

        return new JsonResult<>(200,"查询成功",page);
    }

}
