package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tags")
public class Tag extends BaseEntity{
    private static final long serialVersionUID = 5815153049744753397L;
    // 名称
    private String name;
    // 别名
    private String slug;
    // 排序
    private Integer sort;
    // 描述
    private String description;
}
