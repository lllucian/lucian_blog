package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.SysLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * @author lingxiangdeng
 */
@Getter
@Setter
public class SysLogQuery {
    private Integer userId;

    private String operation;

    private String method;

    private Integer current = 1;

    private Integer size = 10;

    public QueryWrapper<SysLog> getQueryWrapper(){
        QueryWrapper<SysLog> wrapper = new QueryWrapper<>();
        if (userId != null ) {
            wrapper.eq("user_id", userId);
        }
        if (StringUtils.hasText(operation)) {
            wrapper.like("operation", operation);
        }
        if (StringUtils.hasText(method)) {
            wrapper.like("method", method);
        }
        // id 倒序排序
        wrapper.orderByDesc("id");
        return wrapper;
    }
}
