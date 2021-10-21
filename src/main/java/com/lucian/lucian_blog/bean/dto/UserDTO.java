package com.lucian.lucian_blog.bean.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO extends BaseDTO{
    //昵称
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
    private String enabledStatus;
    // 是否锁定
    private String lockedStatus;
    // 角色
    private List<RoleDTO> roles;
}
