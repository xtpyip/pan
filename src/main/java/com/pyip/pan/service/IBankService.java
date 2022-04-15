package com.pyip.pan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.Bank;

public interface IBankService extends IService<Bank> {
    Bank getByUid(Integer uid);

    Integer updateByUid(Integer uid, double total);

}
