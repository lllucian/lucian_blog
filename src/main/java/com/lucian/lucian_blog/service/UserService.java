package com.lucian.lucian_blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, IService<User> {
}
