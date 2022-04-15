package com.pyip.pan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.AddressUser;
import com.pyip.pan.domin.Bank;
import com.pyip.pan.domin.Order;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IAddressUserService;
import com.pyip.pan.service.IBankService;
import com.pyip.pan.service.IOrderService;
import com.pyip.pan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAddressUserService addressUserService;
    @Autowired
    private IBankService bankService;
    @Autowired
    private IProductService productService;

    @GetMapping
    public JsonResult<List<Order>> getAll(){
        List<Order> orders = orderService.list();
        if (orders != null){
            return new JsonResult<>(200,"执行成功",orderService.list());
        }
        return new JsonResult<>(400,"执行失败",new ArrayList<Order>());
    }
    @PostMapping
    public JsonResult<Boolean> save(@RequestBody Order order) throws IOException {
        //注意这里模拟服务器超时故障
//        if (book.getName().equals("22")) throw new IOException();
//        boolean flag = bookService.save(book);
        boolean flag = orderService.save(order);
        if(flag){
            return new JsonResult<>(200,"添加成功^_^",true);
        }
        return new JsonResult<>(500,"添加失败-_-!",false);
    }
    @DeleteMapping("{id}")
    public JsonResult<Boolean> delete(@PathVariable Integer id){
        boolean flag = orderService.removeById(id);
        if(flag){
            return new JsonResult<>(200,"删除成功^_^",true);
        }
        return new JsonResult<>(600,"删除失败-_-!",false);
    }
    @PutMapping
    public JsonResult<Boolean> update(@RequestBody Order order) {
        boolean flag = orderService.updateById(order);
        if(flag){
            return new JsonResult<>(200,"更新成功^_^",true);
        }
        return new JsonResult<>(700,"更新失败-_-!",false);
    }
    @GetMapping("{id}")
    public JsonResult<Order> getById(@PathVariable Integer id) {
        Order result = orderService.getById(id);
        if(result != null){
            return new JsonResult<>(200,"查找成功^_^",result);
        }
        return new JsonResult<>(800,"查找失败-_-!",null);
    }
//    @GetMapping("{currentPage}/{pageSize}")
//    public JsonResult<IPage<Order>> getPage(@PathVariable Integer currentPage,
//                               @PathVariable Integer pageSize) {
//        IPage<Order> page = orderService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
//        if (currentPage > page.getPages()){
//            page = orderService.getPage((int)page.getPages(), pageSize);
//        }
//        return new JsonResult<>(200,"分页成功",page);
//    }
    @GetMapping("{currentPage}/{pageSize}")
    public JsonResult<IPage<Order>> getPage(@PathVariable Integer currentPage,
                                              @PathVariable Integer pageSize,
                                              Order order) {
        IPage<Order> page = orderService.getPage(currentPage, pageSize,order);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
        if (currentPage > page.getPages()){
            page = orderService.getPage((int)page.getPages(), pageSize);
        }

        return new JsonResult<>(200,"查询成功",page);
    }

    @GetMapping("/getAllOrderByUid/{uid}")
    public JsonResult<IPage<Order>> getAllOrderByUid(@PathVariable("uid")Integer uid){
        Order order = new Order();
        order.setUid(uid);
        IPage<Order> orders = orderService.getPage(order);

        return new JsonResult<>(200,"查询成功",orders);
    }
    @GetMapping("/buy/{pid}/{uid}")
    public JsonResult<Boolean> changePayStatue(@PathVariable Integer pid,
                                             @PathVariable Integer uid){
        Order order = new Order();
        Integer id = orderService.getByPidAndUid(pid,uid);
        order.setId(id);
        order.setPid(pid);
        order.setUid(uid);
        order.setPay(0);
        String address = "江西南昌青山湖区服务中心";
        List<AddressUser> byIdNotPrimaryKey = addressUserService.getByIdNotPrimaryKey(uid);
        for (int i = 0; i < byIdNotPrimaryKey.size(); i++) {
            AddressUser addressUser = byIdNotPrimaryKey.get(i);
            if(addressUser.getId() == uid){
                address = addressUser.getAddress();
            }
        }
        order.setAddress(address);
        Bank byUid = bankService.getByUid(uid);
        Product sellProduct = productService.getById(pid);
        double total = sellProduct.getPrice() * sellProduct.getArea();
        if(byUid.getMoney() > total){
            // 银行卡中的钱够
            Integer rows = bankService.updateByUid(uid,total);
            if(rows == 1){
                productService.removeById(pid);
                order.setPay(1);
                orderService.updateById(order);
                return new JsonResult<>(200,"成功",true);
            }
        }else{
            orderService.save(order);
            return new JsonResult<>(4010,"金额不足，支付失败",false);
        }
        return new JsonResult<>(4010,"支付失败",false);
    }

}
