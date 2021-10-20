package com.lucian.lucian_blog.entity;

import lombok.Data;

/**
 * 分类表
 */
@Data
public class Category extends BaseEntity{
    // 名称
    private String name;
    // 别名
    private String slug;
    // 父分类
    private Category parentCategory;
    // 排序
    private Integer sort;
    // 描述
    private String description;
}
