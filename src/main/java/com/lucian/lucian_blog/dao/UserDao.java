package com.lucian.lucian_blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.lucian_blog.dto.SecurityDTO;
import com.lucian.lucian_blog.entity.User;

public interface UserDao extends BaseMapper<User> {
    SecurityDTO loadUserByUsername(String userName);
}
