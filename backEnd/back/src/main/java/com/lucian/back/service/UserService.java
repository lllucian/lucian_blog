package com.lucian.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.common.bean.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lingxiangdeng
 */
public interface UserService extends UserDetailsService, IService<User> {
     User getUserByNickName(String nickName);
}
