package com.lucian.back.query_wrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * @author denglingxiang
 * @date 2022/06/20 08:42
 **/
@Getter
@Setter
public class RoleSelectQuery {
    // 检索关键字
    private String query;

    public Wrapper<Role> getQueryWrapper(){
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(query)) {
            roleQueryWrapper.like("name", query);
        }
        if (StrUtil.isNotBlank(query)) {
            roleQueryWrapper.or().like("zh_name", query);
        }
        return roleQueryWrapper;
    }
}
