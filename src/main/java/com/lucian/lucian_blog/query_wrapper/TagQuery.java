package com.lucian.lucian_blog.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.lucian_blog.bean.entity.Tag;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Setter
@Getter
public class TagQuery {
    private String name;

    private String slug;

    private Integer currentPage = 1;

    private Integer limit = 10;

    public Wrapper<Tag> getQueryWrapper(){
        QueryWrapper<Tag> tagQueryQueryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(name)) tagQueryQueryWrapper.like("name", name);
        if(Strings.isNotBlank(slug)) tagQueryQueryWrapper.like("slug", slug);
        return tagQueryQueryWrapper;
    }
}
