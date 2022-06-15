package com.lucian.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.common.bean.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lingxiangdeng
 */
public interface UserService extends UserDetailsService, IService<User> {
     /**
      * 通过用户名查找用户
      * @param nickName 用户名
      * @return 用户
      */
     User getUserByNickName(String nickName);
}
