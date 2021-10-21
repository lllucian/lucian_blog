package com.lucian.lucian_blog.bean.bo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserBO implements UserDetails {
    private Integer id;
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

    private List<RoleBO> roles;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getName())));
        return list;
    }

    @Override
    public String getUsername() {
        return nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
