package com.pyip.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyip.pan.domin.AddressUser;
import com.pyip.pan.domin.Product;
import org.apache.tomcat.jni.Address;

import java.util.List;

public interface IAddressUserService extends IService<AddressUser> {

    List<AddressUser> getById(AddressUser addressUser);

    //定义一个模糊查询
    public IPage<AddressUser> getPage(Integer currentPage, Integer pageSize, AddressUser addressUser);
    //定义一个模糊查询
    public IPage<AddressUser> getPage(AddressUser addressUser);

    IPage<AddressUser> getPage(int currentPage,int pageSize);

    boolean updateByIdAndAddress(AddressUser addressUser);

    List<AddressUser> getByIdNotPrimaryKey(Integer id);
}
