package com.lucian.lucian_blog.bean.bo;

import com.lucian.lucian_blog.bean.entity.Category;
import lombok.Data;

@Data
public class CategoryBO extends Category {
    private Category parentCategory;
}
