package com.lucian.lucian_blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.lucian_blog.bean.bo.UserBO;
import com.lucian.lucian_blog.bean.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, IService<UserBO> {
     User getUserByNickName(String nickName);
}
