package com.lucian.back.dao;

import com.lucian.common.bean.entity.Role;

import java.util.List;

public interface UserRoleDao {
    List<Role> getRoleListByUserId(Integer userId);
}
