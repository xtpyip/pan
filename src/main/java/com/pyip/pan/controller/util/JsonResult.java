package com.pyip.pan.controller.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JsonResult<E> {
    private Integer code; //返回给前端的状态码
    private String message; //返回给前端的消息
    private E e;  //数据类型
}
