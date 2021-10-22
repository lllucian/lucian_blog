package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucian.lucian_blog.bean.enums.EnabledStatusEnum;
import com.lucian.lucian_blog.bean.enums.LockedStatusEnum;
import lombok.Data;

@TableName("users")
@Data
public class User extends BaseEntity{
    // id 主键 已在BaseEntity中设置
    // private Integer id;
    // 昵称
    private String nickName;
    // 密码
    private String password;
    // 邮箱
    private String email;
    // 上次登陆ip
    private String signLastIp;
    // 本次登陆ip
    private String signCurrentIp;
    // 是否启用
    private EnabledStatusEnum enabled;
    // 是否锁定
    private LockedStatusEnum locked;
}
