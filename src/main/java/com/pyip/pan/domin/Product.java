package com.pyip.pan.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id ;//'楼盘id'
    private String title;//'楼盘标题'
    private Integer area ; //'楼盘面积'
    private String address;// '楼盘地址所在信息'
    private String image; // '图片路径'
    private Double price;// '楼盘每平方米多少钱',
    private String name ;	// '楼盘种类',
    private Integer num ;//'楼盘几居',
    private Integer status; //'楼盘状态  1：上架   2：下架 '
    private Integer priority; //优先级
}
