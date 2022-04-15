package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.BankMapper;
import com.pyip.pan.domin.Bank;
import com.pyip.pan.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends ServiceImpl<BankMapper,Bank> implements IBankService {
    @Autowired
    private BankMapper bankMapper;
    @Override
    public Bank getByUid(Integer uid) {
        return bankMapper.getByUid(uid);
    }

    @Override
    public Integer updateByUid(Integer uid, double total) {
        return bankMapper.updateByUid(uid,total);
    }
}
