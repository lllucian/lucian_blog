package com.lucian.lucian_blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@TableName("users")
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
    private Boolean enabled;
    // 是否锁定
    private Boolean locked;
    // 角色
    private List<Role> roles;
}
