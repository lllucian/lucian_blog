package com.lucian.lucian_blog.bean.bo;

import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.entity.Post;
import com.lucian.lucian_blog.bean.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
public class PostBO {
    private Post post;

    private List<Category> categories;

    private List<Tag> tags;
}
