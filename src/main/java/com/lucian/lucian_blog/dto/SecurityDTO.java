package com.lucian.lucian_blog.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SecurityDTO implements UserDetails {
    // 主键
    private Integer id;
    // 用户名
    private String nickName;
    // 密码
    private String password;
    // 是否启用
    private Boolean enabled;
    // 是否锁定
    private Boolean locked;
    // 角色
    private List<RoleDTO> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (roles != null) roles.forEach(role -> authorityList.add(new SimpleGrantedAuthority(role.getName())));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
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
