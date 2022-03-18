package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("posts")
public class Post extends BaseEntity{
    private static final long serialVersionUID = 879616852919608771L;
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
    // 作者id
    private Integer userId;
    // 封面id
    private Integer uploadFileId;
}
