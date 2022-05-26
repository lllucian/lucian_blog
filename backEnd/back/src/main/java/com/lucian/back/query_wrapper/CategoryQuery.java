package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * @author lingxiangdeng
 */
@Getter
@Setter
public class CategoryQuery {
    private String name;

    private String slug;

    private Integer current = 1;

    private Integer size = 10;

    public Wrapper<Category> getQueryWrapper(){
        QueryWrapper<Category> categoryQueryQueryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(name)) {
            categoryQueryQueryWrapper.like("name", name);
        }
        if(Strings.isNotBlank(slug)) {
            categoryQueryQueryWrapper.like("slug", slug);
        }
        categoryQueryQueryWrapper.orderByAsc("sort");
        categoryQueryQueryWrapper.orderByDesc("created_at");
        return categoryQueryQueryWrapper;
    }
}
