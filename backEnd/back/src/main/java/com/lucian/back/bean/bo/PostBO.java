package com.lucian.back.bean.bo;

import com.lucian.common.bean.entity.Category;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Data
public class PostBO {
    private String id;

    private Post post;

    private List<Category> categories;

    private List<Tag> tags;
}
