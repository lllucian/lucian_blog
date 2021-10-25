package com.lucian.lucian_blog.bean.bo;

import com.lucian.lucian_blog.bean.entity.Role;
import com.lucian.lucian_blog.bean.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserBO implements UserDetails {
    private Integer id;

    User user;

    private List<Role> roles;

    public UserBO() {
    }

    public UserBO(User user, List<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getName())));
        return list;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getNickName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getLocked().getStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled().getStatus();
    }
}
