package com.pyip.pan.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id ;// '订单id',
    private Integer pid ;// '楼盘id',
    private Integer uid ;// '用户id',
    private String address ;//'用户收货地址',
    private Integer pay ;//'用户付款 0-未付 1-已付

}
