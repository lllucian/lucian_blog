package com.lucian.back.query_wrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuery {

    // 用户名
    private String username;

    // 邮箱
    private String email;

    //角色id
    private String roleId;

    // join条件
    private String joinConditions;

    private Integer current = 1;

    private Integer size = 10;

    public QueryWrapper<User> getQueryWrapper() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(username)) {
            userQueryWrapper.eq("users.username", username);
        }
        if (StrUtil.isNotBlank(email)) {
            userQueryWrapper.eq("users.email", email);
        }
        if (StrUtil.isNotBlank(roleId)) {
            userQueryWrapper.eq("roles.id", roleId);
        }
        userQueryWrapper.isNull("users.deleted_at");
        return userQueryWrapper;
    }
}