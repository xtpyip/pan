package com.pyip.pan.controller;

import com.pyip.pan.controller.util.JsonResult;
import com.pyip.pan.domin.Bank;
import com.pyip.pan.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private IBankService bankService;

    @PostMapping
    public JsonResult<Boolean> get(@RequestBody Bank bank){
        System.out.println(bank);
        List<Bank> banks = bankService.list();
        for (int i = 0; i < banks.size(); i++) {
            Bank bank1 = banks.get(i);
            if(bank.getId() == bank1.getId() && bank.getPassword().equals(bank1.getPassword())){
                return new JsonResult<>(200,"绑定成功",true);
            }
        }
        return new JsonResult<>(400,"绑定失败",false);
    }
    @PostMapping("/add")
    public JsonResult<Boolean> add(@RequestBody Bank bank){
        System.out.println(bank);
        boolean flag = bankService.updateById(bank);
        if(flag)
            return new JsonResult<>(200,"绑定成功",true);
        return new JsonResult<>(400,"绑定失败",false);
    }
    @GetMapping("{uid}")
    public JsonResult<Bank> getBankByUid(@PathVariable("uid")Integer uid){
        Bank bank = bankService.getByUid(uid);
        if(bank != null){
            return new JsonResult<>(200,"银行卡信息找到了",bank);
        }
        return new JsonResult<>(4000,"无",null);
    }
    @GetMapping("/dropInfo/{uid}")
    public JsonResult<Bank> drop(@PathVariable("uid")Integer uid){
        try{
            Bank byUid = bankService.getByUid(uid);
            byUid.setUid(0);
//            System.out.println(byUid);
            boolean b = bankService.updateById(byUid);
//            System.out.println("======================"+b);
            if(b)
                return new JsonResult<>(200,"解绑成功",byUid);
        }catch(Exception e){
            return new JsonResult<>(4002,"服务器出错，请稍候再试",null);
        }
        return new JsonResult<>(4003,"未知错误，请稍候再试",null);
    }


}
