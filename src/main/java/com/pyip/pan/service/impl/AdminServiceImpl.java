package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.AdminMapper;
import com.pyip.pan.domin.Admin;
import com.pyip.pan.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
}
