package com.pyip.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.Back;
import com.pyip.pan.domin.Back;

public interface IBackService extends IService<Back> {

    //定义一个模糊查询
    public IPage<Back> getPage(Integer currentPage, Integer pageSize, Back back);
    //定义一个模糊查询
    public IPage<Back> getPage(Back back);

    IPage<Back> getPage(int currentPage,int pageSize);

    Integer insertInto(Back back);
}
