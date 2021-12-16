package com.lucian.lucian_blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.lucian_blog.bean.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, IService<User> {
     User getUserByNickName(String nickName);
}
