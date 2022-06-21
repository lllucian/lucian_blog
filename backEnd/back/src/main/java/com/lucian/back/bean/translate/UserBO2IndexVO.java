package com.lucian.back.bean.translate;

import cn.hutool.core.collection.CollUtil;
import com.lucian.back.bean.vo.UserIndexVO;
import com.lucian.common.bean.bo.UserBO;
import com.lucian.common.bean.entity.Role;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserBO2IndexVO {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "signCurrentIp", source = "user.signCurrentIp"),
            @Mapping(target = "signLastIp", source = "user.signLastIp"),
            @Mapping(target = "roles", ignore = true)
    })
    public abstract UserIndexVO translate(UserBO userBO);

    public abstract List<UserIndexVO> translate(List<UserBO> userBOS);

    @AfterMapping
    public void setRoles(List<UserBO> userBOS, @MappingTarget List<UserIndexVO> userIndexVOS){
        if (CollUtil.isEmpty(userBOS) || CollUtil.isEmpty(userIndexVOS)){
            return;
        }
        Map<String, List<String>> userRoles = userBOS.stream().filter(userBO -> CollUtil.isNotEmpty(userBO.getRoles())).collect(Collectors.toMap(UserBO::getId,
                userBO -> userBO.getRoles().stream().map(Role::getZhName).collect(Collectors.toList())));
        userIndexVOS.forEach(userIndexVO -> {
            List<String> roles = userRoles.get(userIndexVO.getId());
            userIndexVO.setRoles(roles);
        });
    }
}
