package com.pyip.pan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public JsonResult<List<Product>> getAll(){
        List<Product> products = productService.list();
        if (products != null){
            return new JsonResult<>(200,"执行成功",productService.list());
        }
        return new JsonResult<>(400,"执行失败",new ArrayList<Product>());
    }
    @PostMapping
    public JsonResult<Boolean> save(@RequestBody Product product) throws IOException {
        //注意这里模拟服务器超时故障
//        if (book.getName().equals("22")) throw new IOException();
//        boolean flag = bookService.save(book);
        boolean flag = productService.save(product);
        if(flag){
            return new JsonResult<>(200,"添加成功^_^",true);
        }
        return new JsonResult<>(500,"添加失败-_-!",false);
    }
    @DeleteMapping("{id}")
    public JsonResult<Boolean> delete(@PathVariable Integer id){
        boolean flag = productService.removeById(id);
        if(flag){
            return new JsonResult<>(200,"删除成功^_^",true);
        }
        return new JsonResult<>(600,"删除失败-_-!",false);
    }
    @PutMapping
    public JsonResult<Boolean> update(@RequestBody Product product) {
        boolean flag = productService.updateById(product);
        if(flag){
            return new JsonResult<>(200,"更新成功^_^",true);
        }
        return new JsonResult<>(700,"更新失败-_-!",false);
    }
    @GetMapping("{id}")
    public JsonResult<Product> getById(@PathVariable Integer id) {
        Product result = productService.getById(id);
        if(result != null){
            return new JsonResult<>(200,"查找成功^_^",result);
        }
        return new JsonResult<>(800,"查找失败-_-!",null);
    }
//    @GetMapping("{currentPage}/{pageSize}")
//    public JsonResult<IPage<Product>> getPage(@PathVariable Integer currentPage,
//                               @PathVariable Integer pageSize) {
//        IPage<Product> page = productService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
//        if (currentPage > page.getPages()){
//            page = productService.getPage((int)page.getPages(), pageSize);
//        }
//        return new JsonResult<>(200,"分页成功",page);
//    }
    @GetMapping("{currentPage}/{pageSize}")
    public JsonResult<IPage<Product>> getPage(@PathVariable Integer currentPage,
                     @PathVariable Integer pageSize,
                     Product product) {
        //System.out.println("book: "+book);
        IPage<Product> page = productService.getPage(currentPage, pageSize,product);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
        if (currentPage > page.getPages()){
            page = productService.getPage((int)page.getPages(), pageSize);
        }

        return new JsonResult<>(200,"查询成功",page);
    }
    @GetMapping("/hot")
    public JsonResult<List<Product>> getHot(){
        List<Product> list = productService.getListByHot();
        return new JsonResult<>(200,"热门商品",list);

    }


}
