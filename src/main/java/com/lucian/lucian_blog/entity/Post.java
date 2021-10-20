package com.lucian.lucian_blog.entity;

import lombok.Data;

@Data
public class Post extends BaseEntity{
    // 大标题
    private String title;
    // 副标题
    private String description;
    // 内容
    private String content;
    // 浏览数
    private Integer viewCount;
    // 点赞数
    private Integer likes;
    // 排序
    private Integer sort;
    // 作者
    private User user;
}
