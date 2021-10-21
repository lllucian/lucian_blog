package com.lucian.lucian_blog.dao;

import com.lucian.lucian_blog.bean.bo.RoleBO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    @Select("select * from roles" +
            " left join user_roles on roles.id = user_roles.role_id and user_roles.deleted_at is null " +
            " where user_roles.user_id = #{userId} and roles.deleted_at is null")
    List<RoleBO> queryListByUserId(String userId);
}
