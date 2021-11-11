package com.lucian.lucian_blog.form_parm;

import lombok.Data;

@Data
public class CategoryParam {
    // 名称
    private String name;
    // 别名
    private String slug;
    // 父分类
    private Integer parentId;
    // 排序
    private Integer sort;
    // 描述
    private String description;
}
