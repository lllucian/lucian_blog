package com.lucian.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.common.bean.entity.Role;
import com.lucian.common.bean.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends BaseMapper<UserRole> {
    List<Role> getRoleListByUserId(String userId);
}
