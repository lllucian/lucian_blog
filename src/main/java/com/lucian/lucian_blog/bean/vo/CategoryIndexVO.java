package com.lucian.lucian_blog.bean.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CategoryIndexVO {
    /**
     * 分类名
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 被关联的文章数量
     */
    private Integer postCount;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 父分类
     */
    private CategoryIndexVO parentCategory;
}
