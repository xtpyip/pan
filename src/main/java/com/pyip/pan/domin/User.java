package com.pyip.pan.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id ;//'用户id',
    private String name  ;//'用户帐号',
    private String password;//'用户密码',
    private Integer sex; //'用户性别 0-男，1-女',
    private Integer age;// '用户年龄',
    private String phone ; //'用户电话',
    private Integer score; //‘信誉积分’
    private String img; // 用户头像
}
