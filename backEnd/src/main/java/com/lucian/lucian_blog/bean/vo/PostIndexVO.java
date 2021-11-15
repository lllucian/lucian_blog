package com.lucian.lucian_blog.bean.vo;

import lombok.Data;

@Data
public class PostIndexVO {
    private Integer id;

    private String title;

    private String description;

    private String categories;

    private String tags;

    private String createdAt;
}
