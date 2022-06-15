package com.lucian.back.bean.translate;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.back.bean.vo.RoleIndexVO;
import com.lucian.common.bean.entity.Role;
import com.lucian.common.bean.entity.User;
import com.lucian.common.bean.entity.UserRole;
import com.lucian.common.dao.UserDao;
import com.lucian.common.dao.UserRoleDao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author denglingxiang
 * @date 2022/06/01 14:57
 **/
@Mapper(componentModel = "spring")
public abstract class Role2IndexVO {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    UserRoleDao userRoleDao;

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     * do 转 vo
     * @param roles do
     * @return vo
     */
    public abstract List<RoleIndexVO> translate(List<Role> roles);


    @AfterMapping
    public void setUserNumber(@MappingTarget List<RoleIndexVO> roleIndexVOS){
        if (Objects.isNull(roleIndexVOS) || roleIndexVOS.size() == 0){
            return;
        }
        Map<String, RoleIndexVO> indexVOMap = roleIndexVOS.stream().collect(Collectors.toMap(RoleIndexVO::getId, roleIndex -> roleIndex));
        Set<String> roleIds = indexVOMap.keySet();
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        List<UserRole> userRoles = userRoleDao.selectList(queryWrapper);
        if (Objects.isNull(userRoles) || userRoles.size() == 0) {
            return;
        }
        Map<String, ArrayList<String>> userIdCollect = userRoles.stream().collect(Collectors.toMap(UserRole::getRoleId, userRole -> new ArrayList<String>() {{
            add(userRole.getUserId());
        }}, (oldValue, newValue) -> {
            oldValue.addAll(newValue);
            return oldValue;
        }));
        userIdCollect.forEach((roleId, userIds) -> {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.in("id", userIds);
            Long count = userDao.selectCount(userQueryWrapper);
            indexVOMap.get(roleId).setAccountNumber(Math.toIntExact(count));
        });

    }
}
