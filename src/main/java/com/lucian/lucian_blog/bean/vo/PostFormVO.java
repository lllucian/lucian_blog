package com.lucian.lucian_blog.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class PostFormVO{

    private Integer id;

    private String title;

    private String description;

    private String content;

    private Integer sort;

    private String updatedAt;

    private String createdAt;

    private List<Integer> categories;

    private List<Integer> tags;
}
