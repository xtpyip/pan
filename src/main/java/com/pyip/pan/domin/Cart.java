package com.pyip.pan.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
   private Integer id; //购物车id
   private Integer pid ;//'楼盘id',
   private Integer uid ;// '用户id',
   private String address ;// '用户收货地址'
}
