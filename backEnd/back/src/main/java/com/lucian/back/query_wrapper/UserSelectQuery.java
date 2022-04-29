package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 构造用户select检索条件
 * @author lingxiangdeng
 */
@Getter
@Setter
public class UserSelectQuery {
    /**
     * 用户名
     */
    private String name;

    /**
     * 使用用户名模糊检索
     * @return like用户名的数据
     */
    public Wrapper<User> getQueryWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like("username", name);
        }
        return wrapper;
    }
}
