package com.lucian.lucian_blog.bean.entity;

import lombok.Data;

@Data
public class Tag {
    // 名称
    private String name;
    // 别名
    private String slug;
    // 排序
    private Integer sort;
}
