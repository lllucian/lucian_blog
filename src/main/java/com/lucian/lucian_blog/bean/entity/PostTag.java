package com.lucian.lucian_blog.bean.entity;

import lombok.Data;

@Data
public class PostTag extends BaseEntity {
    private static final long serialVersionUID = -2644995631199952193L;

    private Integer postId;

    private Integer tagId;
}
