package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Tag;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * @author lingxiangdeng
 */
@Setter
@Getter
public class TagQuery {
    private String name;

    private String slug;

    private Integer current = 1;

    private Integer size = 10;

    public Wrapper<Tag> getQueryWrapper(){
        QueryWrapper<Tag> tagQueryQueryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(name)) {
            tagQueryQueryWrapper.like("name", name);
        }
        if(Strings.isNotBlank(slug)) {
            tagQueryQueryWrapper.like("slug", slug);
        }
        return tagQueryQueryWrapper;
    }
}
