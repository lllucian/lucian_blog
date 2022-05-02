package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Post;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * @author lingxiangdeng
 */
@Getter
@Setter
public class PostSelectQuery {
    private String title;

    public QueryWrapper<Post> getQueryWrapper(){
        QueryWrapper<Post> categoryQueryQueryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(title)) {
            categoryQueryQueryWrapper.like("title", title);
        }
        return categoryQueryQueryWrapper;
    }
}
