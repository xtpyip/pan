package com.pyip.pan.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Integer id ;//'管理员id',
    private String name;//'管理员帐号',
    private String password ;// '管理员密码',
}
