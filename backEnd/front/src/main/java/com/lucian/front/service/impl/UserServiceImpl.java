package com.lucian.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.common.bean.entity.User;
import com.lucian.front.dao.UserDao;
import com.lucian.front.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lingxiangdeng
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
