package com.lucian.back.bean.translate;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.back.bean.vo.UserFormVO;
import com.lucian.back.service.RoleService;
import com.lucian.back.service.UserRoleService;
import com.lucian.common.bean.entity.Role;
import com.lucian.common.bean.entity.User;
import com.lucian.common.bean.entity.UserRole;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class User2UserFormVO {

    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.username", target = "username"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.enabled", target = "enabled"),
            @Mapping(source = "user.locked", target = "locked"),
            @Mapping(target = "roleIds", ignore = true)
    })
    public abstract UserFormVO user2UserFormVO(User user);

    @AfterMapping
    protected void setRoleIds(User user, @MappingTarget UserFormVO userFormVO){
        UserRoleService userRoleService = SpringUtil.getBean(UserRoleService.class);
        List<String> roleIds = userRoleService.list((new QueryWrapper<UserRole>()).eq("user_id", user.getId()))
                .stream().map(UserRole::getRoleId).collect(Collectors.toList());
        RoleService roleService = SpringUtil.getBean(RoleService.class);
        List<String> selectedRoleIds = roleService.listByIds(roleIds).stream().map(Role::getId).collect(Collectors.toList());
        userFormVO.setRoleIds(selectedRoleIds);
    }
}
