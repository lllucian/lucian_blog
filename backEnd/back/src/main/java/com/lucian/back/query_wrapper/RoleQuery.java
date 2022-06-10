package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * @author denglingxiang
 * @date 2022/06/01 14:23
 **/
@Getter
@Setter
public class RoleQuery {

    private String name;

    private String zhName;

    private Integer current = 1;

    private Integer size = 10;

    public QueryWrapper<Role> getQueryWrapper(){
        QueryWrapper<Role> roleQueryQueryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(name)) {
            roleQueryQueryWrapper.like("name", "ROLE_" + name);
        }

        if(Strings.isNotBlank(zhName)) {
            roleQueryQueryWrapper.like("zh_name", zhName);
        }
        return roleQueryQueryWrapper;
    }
}
