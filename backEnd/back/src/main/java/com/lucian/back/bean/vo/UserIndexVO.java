package com.lucian.back.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserIndexVO {
    // 用户id
    private String id;
    // 用户名
    private String username;
    // 邮箱
    private String email;
    // 上次登录的IP
    private String signLastIp;
    // 当前登录ip
    private String signCurrentIp;
    // 角色
    private List<String> roles;
}
