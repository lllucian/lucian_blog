package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 分类表
 */
@Data
@TableName("categories")
public class Category extends BaseEntity{

    private static final long serialVersionUID = 2595420458030447858L;

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
