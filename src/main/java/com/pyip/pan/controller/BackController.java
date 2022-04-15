package com.pyip.pan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.Back;
import com.pyip.pan.service.IBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/backs")
@RestController
public class BackController {
    @Autowired
    private IBackService backService;

    @GetMapping("{currentPage}/{pageSize}")
    public JsonResult<IPage<Back>> getPage(@PathVariable Integer currentPage,
                                           @PathVariable Integer pageSize,
                                           Back back) {
        //System.out.println("book: "+book);
        IPage<Back> page = backService.getPage(currentPage, pageSize,back);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使最大页码值为当前页码值
        if (currentPage > page.getPages()){
            page = backService.getPage((int)page.getPages(), pageSize);
        }

        return new JsonResult<>(200,"查询成功",page);
    }
    @PostMapping
    public JsonResult<Boolean> save(@RequestBody Back back){
        if(backService.insertInto(back) == 1){
            return new JsonResult<>(200,"添加成功^_^",true);
        }
        return new JsonResult<>(500,"添加失败-_-!",false);
    }
}
