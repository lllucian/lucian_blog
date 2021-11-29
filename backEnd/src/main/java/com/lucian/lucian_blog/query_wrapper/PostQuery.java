package com.lucian.lucian_blog.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.lucian_blog.bean.entity.Post;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Data
public class PostQuery {

    private String title;

    private Date createdStart;

    private Date createdEnd;

    private Date updatedStart;

    private Date updatedEnd;

    private String category;

    private String tag;

    private Integer currentPage = 1;

    private Integer limit = 10;

    public QueryWrapper<Post> getQueryWrapper(){
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        if (Strings.isNotBlank(title)) wrapper.like("p.title", title);
        if (createdStart != null) wrapper.le("p.created_at", createdStart);
        if (createdEnd != null) wrapper.ge("p.created_at", createdEnd);
        if (updatedStart != null) wrapper.le("p.updated_at", updatedStart);
        if (updatedEnd != null) wrapper.ge("p.updated_at", updatedEnd);
        if (Strings.isNotBlank(category)) wrapper.eq("ca.name", category);
        if (Strings.isNotBlank(tag)) wrapper.eq("t.name", tag);
        return wrapper;
    }
}
