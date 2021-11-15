package com.lucian.lucian_blog.bean.bo;

import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class CategoryBO extends Category {
    private Category parentCategory;

    private List<Post> postList;
}
