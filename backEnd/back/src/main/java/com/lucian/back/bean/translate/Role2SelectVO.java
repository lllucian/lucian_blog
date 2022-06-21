package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.RoleSelectDataVO;
import com.lucian.common.bean.entity.Role;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author denglingxiang
 * @date 2022/06/20 09:05
 **/
@Mapper(componentModel = "spring")
public abstract class Role2SelectVO {

    @Mappings({
            @Mapping(source = "id", target = "value"),
            @Mapping(target = "label", ignore = true)
    })
    public abstract RoleSelectDataVO translate(Role role);

    public abstract List<RoleSelectDataVO> translate(List<Role> roles);

    @AfterMapping
    public void setLabelValue(@MappingTarget List<RoleSelectDataVO> roleSelectDataVOS, List<Role> roles) {
        Map<String, String> roleMap = roles.stream().collect(Collectors.toMap(Role::getId, role -> String.format("%s(%s)", role.getZhName(), role.getName())));
        roleSelectDataVOS.stream().peek(role -> role.setLabel(roleMap.get(role.getValue())));
    }
}
