package com.lucian.lucian_blog.bean.entity;

import lombok.Data;

@Data
public class PostTag extends BaseEntity {
    private Integer postId;

    private Integer tagId;
}
