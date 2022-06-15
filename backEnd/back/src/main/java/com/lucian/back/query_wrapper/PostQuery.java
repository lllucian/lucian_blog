package com.lucian.back.query_wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Post;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Objects;

/**
 * @author lingxiangdeng
 */
@Data
public class PostQuery {

    private String title;

    private List<String> created;

    private List<String> updated;

    private List<String> categories;

    private List<String> tags;

    private Integer current = 1;

    private Integer size = 10;

    public QueryWrapper<Post> getQueryWrapper(){
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        if (Strings.isNotBlank(title)) {
            wrapper.like("p.title", title);
        }
        if (!Objects.isNull(created) && created.size() > 0) {
            wrapper.ge("p.created_at", created.get(0));
        }
        if (!Objects.isNull(created) && created.size() > 1) {
            wrapper.le("p.created_at", created.get(1));
        }
        if (!Objects.isNull(updated) && updated.size() > 0) {
            wrapper.ge("p.updated_at", updated.get(0));
        }
        if (!Objects.isNull(updated) && updated.size() > 1) {
            wrapper.le("p.updated_at", updated.get(1));
        }
        if (!Objects.isNull(categories) && categories.size() > 0) {
            wrapper.in("pc.category_id", categories);
        }
        if (!Objects.isNull(tags) && tags.size() > 0) {
            wrapper.in("pt.tag_id", tags);
        }
        wrapper.isNull("p.deleted_at");
        return wrapper;
    }
}
