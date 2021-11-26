package com.lucian.lucian_blog.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class PostIndexVO {
    private Integer id;

    private String title;

    private String description;

    private List<String> categories;

    private List<String> tags;

    private String createdAt;

    private String updatedAt;
}
