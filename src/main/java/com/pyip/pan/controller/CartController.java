package com.pyip.pan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.*;
import com.pyip.pan.domin.Cart;
import com.pyip.pan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAddressUserService addressUserService;
    @Autowired
    private IBankService bankService;

    @RequestMapping("/add/{id}")
    public JsonResult<Boolean> addCart(@PathVariable Integer id,
                                       User user) {
        System.out.println(user);
        List<Cart> carts = cartService.list();
        String address = null;
        List<AddressUser> addresses = addressUserService.list();
        for (int i = 0; i < addresses.size(); i++) {
            AddressUser addressUser = addresses.get(i);
            if (addressUser.getId() == user.getId() && addressUser.getFlag() == 0) {
                address = addressUser.getAddress();
            }
            System.out.println("address" + address);
        }
        if (address == null)
            return new JsonResult<>(401, "地址无", false);
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            if (id == cart.getPid()) {
                if (user.getId() == cart.getUid()) {
                    return new JsonResult<>(400, "已加入过购物车", false);
                } else {
                    Cart cart1 = new Cart();
                    cart1.setPid(id);
                    cart1.setUid(user.getId());
                    cart1.setAddress(address);
                    return new JsonResult<>(200, "成功加入购物车", cartService.save(cart1));
                }
            }

        }
        Cart cart1 = new Cart();
        cart1.setPid(id);
        cart1.setUid(user.getId());
        cart1.setAddress(address);
        return new JsonResult<>(200, "成功加入购物车", cartService.save(cart1));
    }

    @RequestMapping("/delete/{id}")
    public JsonResult<Boolean> deleteCart(@PathVariable Integer id,
                                          User user) {
        System.out.println(user);
        List<Cart> carts = cartService.list();
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            if (id == cart.getPid()) {
                if (user.getId() == cart.getUid()) {
                    return new JsonResult<>(200, "成功删除购物车", cartService.deleteByUidAndPid(id, user.getId()));
                } else {
                    return new JsonResult<>(400, "未曾加入购物车", false);
                }
            }
        }
        return new JsonResult<>(400, "未曾加入购物车", false);
    }

    @GetMapping
    public JsonResult<List<Cart>> getAll() {
        List<Cart> carts = cartService.list();
        if (carts != null) {
            return new JsonResult<>(200, "执行成功", cartService.list());
        }
        return new JsonResult<>(400, "执行失败", new ArrayList<Cart>());
    }

    @PostMapping
    public JsonResult<Boolean> save(@RequestBody Cart cart) throws IOException {
        //注意这里模拟服务器超时故障
//        if (book.getName().equals("22")) throw new IOException();
//        boolean flag = bookService.save(book);
        boolean flag = cartService.save(cart);
        if (flag) {
            return new JsonResult<>(200, "添加成功^_^", true);
        }
        return new JsonResult<>(500, "添加失败-_-!", false);
    }

    @DeleteMapping("{id}")
    public JsonResult<Boolean> delete(@PathVariable Integer id) {
        boolean flag = cartService.removeById(id);
        if (flag) {
            return new JsonResult<>(200, "删除成功^_^", true);
        }
        return new JsonResult<>(600, "删除失败-_-!", false);
    }

    @PutMapping
    public JsonResult<Boolean> update(@RequestBody Cart cart) {
        boolean flag = cartService.updateById(cart);
        if (flag) {
            return new JsonResult<>(200, "更新成功^_^", true);
        }
        return new JsonResult<>(700, "更新失败-_-!", false);
    }

    @GetMapping("{id}")
    public JsonResult<Cart> getById(@PathVariable Integer id) {
        Cart result = cartService.getById(id);
        if (result != null) {
            return new JsonResult<>(200, "查找成功^_^", result);
        }
        return new JsonResult<>(800, "查找失败-_-!", null);
    }

    //    @GetMapping("{currentPage}/{pageSize}")
//    public JsonResult<IPage<Cart>> getPage(@PathVariable Integer currentPage,
//                               @PathVariable Integer pageSize) {
//        IPage<Cart> page = CartService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
//        if (currentPage > page.getPages()){
//            page = cartService.getPage((int)page.getPages(), pageSize);
//        }
//        return new JsonResult<>(200,"分页成功",page);
//    }
    @GetMapping("{currentPage}/{pageSize}")
    public JsonResult<IPage<Cart>> getPage(@PathVariable Integer currentPage,
                                           @PathVariable Integer pageSize,
                                           Cart cart) {
        //System.out.println("book: "+book);
        IPage<Cart> page = cartService.getPage(currentPage, pageSize, cart);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
        if (currentPage > page.getPages()) {
            page = cartService.getPage((int) page.getPages(), pageSize);
        }

        return new JsonResult<>(200, "查询成功", page);
    }

    @GetMapping("buy/{pid}/{uid}")
    public JsonResult<Boolean> buy(@PathVariable Integer pid,
                          @PathVariable Integer uid) {
        System.out.println("11");
        Order order = new Order();
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
        Integer id = cartService.getByPidAndUid( pid, uid);
        if(byUid.getMoney() > total){
            // 银行卡中的钱够
            Integer rows = bankService.updateByUid(uid,total);
            if(rows == 1){
                productService.removeById(pid);
                cartService.removeById(id);
                order.setPay(1);
                orderService.save(order);
                return new JsonResult<>(200,"成功",true);
            }
        }else{
            cartService.removeById(id);
            orderService.save(order);
            return new JsonResult<>(4010,"金额不足，支付失败",false);
        }
        return new JsonResult<>(4010,"支付失败",false);

    }

}
