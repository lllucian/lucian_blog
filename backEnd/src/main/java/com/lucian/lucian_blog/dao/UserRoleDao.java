package com.lucian.lucian_blog.dao;

import com.lucian.lucian_blog.bean.entity.Role;

import java.util.List;

public interface UserRoleDao {
    List<Role> getRoleListByUserId(Integer userId);
}
