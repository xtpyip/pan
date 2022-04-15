package com.pyip.pan.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressUser {
    private String address;// '用户收货地址',
    private Integer id;// '用户id',
    private Integer flag;//'用户默认地址 0-默认 1-其他'
}
