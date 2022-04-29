package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Post;
import io.jsonwebtoken.lang.Collections;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Data
public class PostQuery {

    private String title;

    private List<String> created;

    private List<String> updated;

    private List<Integer> categories;

    private List<Integer> tags;

    private Integer current = 1;

    private Integer size = 10;

    public QueryWrapper<Post> getQueryWrapper(){
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        if (Strings.isNotBlank(title)) {
            wrapper.like("p.title", title);
        }
        if (!Collections.isEmpty(created)) {
            wrapper.ge("p.created_at", created.get(0));
        }
        if (!Collections.isEmpty(created) && created.size() > 1) {
            wrapper.le("p.created_at", created.get(1));
        }
        if (!Collections.isEmpty(updated)) {
            wrapper.ge("p.updated_at", updated.get(0));
        }
        if (!Collections.isEmpty(updated) && updated.size() > 1) {
            wrapper.le("p.updated_at", updated.get(1));
        }
        if (!Collections.isEmpty(categories)) {
            wrapper.in("pc.category_id", categories);
        }
        if (!Collections.isEmpty(tags)) {
            wrapper.in("pt.tag_id", tags);
        }
        return wrapper;
    }
}
