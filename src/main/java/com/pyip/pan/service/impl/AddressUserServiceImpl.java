package com.pyip.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyip.pan.dao.AddressUserMapper;
import com.pyip.pan.domin.AddressUser;
import com.pyip.pan.domin.Product;
import com.pyip.pan.service.IAddressUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressUserServiceImpl extends ServiceImpl<AddressUserMapper,AddressUser> implements IAddressUserService {
    @Autowired
    private AddressUserMapper addressUserMapper;

    @Override
    public List<AddressUser> getById(AddressUser addressUser) {
        List<AddressUser> lists = addressUserMapper.selectList(null);
        ArrayList<AddressUser> result = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            AddressUser addressUser1 = lists.get(i);
            if(addressUser1.getId() == addressUser.getId()){
                result.add(addressUser1);
            }
        }
        return result;
    }

    @Override
    public IPage<AddressUser> getPage(Integer currentPage, Integer pageSize, AddressUser addressUser) {
        LambdaQueryWrapper<AddressUser> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(addressUser.getAddress()),AddressUser::getAddress,addressUser.getAddress());
        lqw.like(Strings.isNotEmpty(""+addressUser.getId()) && addressUser.getId() != null,AddressUser::getId,addressUser.getId());
        lqw.like(Strings.isNotEmpty(""+addressUser.getFlag()) && addressUser.getFlag() != null,AddressUser::getFlag,addressUser.getFlag());

        IPage<AddressUser> page = new Page<>(currentPage, pageSize);
        return addressUserMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<AddressUser> getPage(AddressUser addressUser) {
        LambdaQueryWrapper<AddressUser> lqw = new LambdaQueryWrapper<>();

        lqw.like(Strings.isNotEmpty(addressUser.getAddress()),AddressUser::getAddress,addressUser.getAddress());
        lqw.like(Strings.isNotEmpty(""+addressUser.getId()) && addressUser.getId() != null,AddressUser::getId,addressUser.getId());
        lqw.like(Strings.isNotEmpty(""+addressUser.getFlag()) && addressUser.getFlag() != null,AddressUser::getFlag,addressUser.getFlag());

        IPage<AddressUser> page = new Page<>();
        return addressUserMapper.selectPage(page,lqw);
    }

    @Override
    public IPage<AddressUser> getPage(int currentPage, int pageSize) {
        IPage<AddressUser> page = new Page<>(currentPage, pageSize);
        return addressUserMapper.selectPage(page,null);
    }

    @Override
    public boolean updateByIdAndAddress(AddressUser addressUser) {
        List<AddressUser> byId = getById(addressUser);
        for (int i = 0; i < byId.size(); i++) {
            if(byId.get(i).getAddress().equals(addressUser.getAddress()))
            {
                int update = addressUserMapper.update(addressUser, null);
                if(update < 1){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AddressUser> getByIdNotPrimaryKey(Integer id) {
        return addressUserMapper.getByIdNotPrimaryKey(id);
    }
}
